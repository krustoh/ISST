package cn.edu.zju.isst.dao;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Date;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import cn.edu.zju.isst.common.SelectSQLBuilder;
import cn.edu.zju.isst.dao.annotation.Column;
import cn.edu.zju.isst.dao.annotation.Entity;
import cn.edu.zju.isst.dao.annotation.ID;

public abstract class AbstractDao<T> implements Dao<T> {
    @Autowired
    protected NamedParameterJdbcTemplate jdbcTemplate;
    
    protected final Class<T> entityClass;
    protected final String table;
    protected final String primaryKey;
    protected final Map<String, Field> fields = new HashMap<String, Field>();
    protected final Map<String, Method> fieldGetters = new HashMap<String, Method>();
    protected final Map<String, Method> fieldSetters = new HashMap<String, Method>();
    
    private RowMapper<T> rowMapper;
    
    @SuppressWarnings({ "unchecked" })
    public  AbstractDao() {
        Type t = (ParameterizedType) getClass().getGenericSuperclass();
        ParameterizedType pt = (ParameterizedType) t;
        entityClass = (Class<T>) pt.getActualTypeArguments()[0];
        Entity entity = (Entity) entityClass.getAnnotation(Entity.class);
        table = entity.value();
        
        String pk = null;
        for (Field field : entityClass.getDeclaredFields()) {
            Column column = field.getAnnotation(Column.class);
            if (null != column) {
                String columnName = column.value().isEmpty() ? field.getName() : column.value();
                fields.put(columnName, field);
                
                ID id = field.getAnnotation(ID.class);
                if (null != id) {
                    pk = columnName;
                }
                
                char[] cs = field.getName().toCharArray();
                cs[0] = Character.toUpperCase(cs[0]);
                String getter = (field.getType() == boolean.class || field.getType() == Boolean.class ? "is" : "get") + String.valueOf(cs);
                try {
                    fieldGetters.put(columnName, entityClass.getMethod(getter, new Class<?>[] {}));
                } catch (SecurityException e) {
                    e.printStackTrace();
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                }
                
                String setter = "set" + String.valueOf(cs);
                try {
                    fieldSetters.put(columnName, entityClass.getMethod(setter, parseClassTypeForSetter(field.getType())));
                } catch (SecurityException e) {
                    e.printStackTrace();
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                }
            }
        }
        
        primaryKey = pk;
    }
    
    private Class<?> parseClassTypeForSetter(Class<?> c) {
        if (c.isPrimitive() || c == Date.class) {
            return c;
        } else if (c.isEnum()) {
            return int.class;
        } else {
            return String.class;
        }
    }
    
    public void insert(final T entity) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        for (String column : fields.keySet()) {
            paramSource.addValue(column, getFieldValue(entity, column));
        }
        
        StringBuilder sql = new StringBuilder("INSERT INTO ");
        StringBuilder values = new StringBuilder();
        sql.append(table).append(" (");
        int i = 0;
        for (String column : fields.keySet()) {
            if (i > 0) {
                sql.append(", ");
                values.append(", ");
            }
            sql.append(column);
            values.append(":").append(column);
            i++;
        }
        
        sql.append(") VALUES (").append(values).append(")");
        jdbcTemplate.update(sql.toString(), paramSource, keyHolder, new String[] { primaryKey });
        
        Number keyValue = keyHolder.getKey();
        if (null != keyValue) {
            setFieldValue(entity, primaryKey, keyValue.intValue());
        }
    }
    
    public int update(T entity) {
        StringBuilder sql = new StringBuilder("UPDATE ");
        sql.append(table).append(" SET ");

        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        for (String column : fields.keySet()) {
            paramSource.addValue(column, getFieldValue(entity, column));
        }
        
        int i = 0;
        for (String column : fields.keySet()) {
            if (i > 0) {
                sql.append(", ");
            }
            sql.append(column).append("=:").append(column);
            paramSource.addValue(column, getFieldValue(entity, column));
            i++;
        }
        sql.append(" WHERE ").append(primaryKey).append("=:__primaryKey__");
        paramSource.addValue("__primaryKey__", getFieldValue(entity, primaryKey));
        
        return jdbcTemplate.update(sql.toString(), paramSource);
    }
    
    public void save(T entity) {
        Integer id = (Integer) getFieldValue(entity, primaryKey);
        if (null != id && id > 0) {
            update(entity);
        } else {
            insert(entity);
        }
    }
    
    public int delete(T entity) {
        String sql = String.format("DELETE FROM %s WHERE %s=?", table, primaryKey);
        return jdbcTemplate.getJdbcOperations().update(sql, new Object[] { getFieldValue(entity, primaryKey)});
    }
    
    public int delete(Set<Integer> idset) {
        String sql = String.format("DELETE FROM %s WHERE %s IN (:idset)", table, primaryKey);
        
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("idset", idset);
        
        return jdbcTemplate.update(sql, parameters);
    }
    
    public T find(int id) {
        return query(String.format("SELECT * FROM %s WHERE %s=?", table, primaryKey), id);
    }
    
    public T find(int id, RowMapper<T> rowMapper) {
        return query(String.format("SELECT * FROM %s WHERE %s=?", table, primaryKey), rowMapper, id);
    }
    
    public List<T> findAll(Set<Integer> idset) {
        String sql = String.format("SELECT * FROM %s WHERE %s IN (:idset)", table, primaryKey);
        
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("idset", idset);
        
        return jdbcTemplate.query(sql, parameters, getRowMapper());
    }
    
    public T query(String sql) {
        return query(sql, new Object[] {});
    }
    
    public T query(String sql, Object...params) {
        List<T> list = queryAll(sql, params);
        
        if (list.isEmpty()) {
            return null;
        }
        
        return list.get(0);
    }
    
    public <E>E query(String sql, RowMapper<E> rowMapper) {
        List<E> list = queryAll(sql, rowMapper, new Object[] {});
        
        if (list.isEmpty()) {
            return null;
        }
        
        return list.get(0);
    }
    
    public <E>E query(String sql, RowMapper<E> rowMapper, Object...params) {
        List<E> list = queryAll(sql, rowMapper, params);
        
        if (list.isEmpty()) {
            return null;
        }
        
        return list.get(0);
    }
    
    public T query(String sql, Map<String, Object> params) {
        List<T> list = queryAll(sql, params);
        
        if (list.isEmpty()) {
            return null;
        }
        
        return list.get(0);
    }
    
    public T query(SelectSQLBuilder select) {
        List<T> list = queryAll(select);
        
        if (list.isEmpty()) {
            return null;
        }
        
        return list.get(0);
    }
    
    public List<T> queryAll(String sql) {
        return queryAll(sql, new Object[] {});
    }
    
    public List<T> queryAll(String sql, Object...params) {
        return jdbcTemplate.getJdbcOperations().query(sql, params, getRowMapper());
    }
    
    public <E>List<E> queryAll(String sql, RowMapper<E> rowMapper) {
        return jdbcTemplate.getJdbcOperations().query(sql, new Object[] {}, rowMapper);
    }
    
    public <E>List<E> queryAll(String sql, RowMapper<E> rowMapper, Object...params) {
        return jdbcTemplate.getJdbcOperations().query(sql, params, rowMapper);
    }
    
    public List<T> queryAll(String sql, Map<String, Object> params) {
        return jdbcTemplate.query(sql, params, getRowMapper());
    }
    
    public List<T> queryAll(String sql, Map<String, Object> params, RowMapper<T> rowMapper) {
        return jdbcTemplate.query(sql, params, rowMapper);
    }
    
    public List<T> queryAll(SelectSQLBuilder select) {
        return jdbcTemplate.query(select.toSQL(), select.getParams(), getRowMapper());
    }
    
    public boolean exists(int id) {
        String sql = String.format("SELECT COUNT(%s) FROM %s WHERE %s=?", primaryKey, table, primaryKey);
        return jdbcTemplate.getJdbcOperations().queryForObject(sql, new Object[] { id }, Integer.class) > 0 ? true : false;
    }
    
    protected Object getFieldValue(T entity, String column) {
        try {
            return fieldGetters.get(column).invoke(entity, new Object[] {});
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    protected void setFieldValue(T entity, String column, Object value) {
        try {
            fieldSetters.get(column).invoke(entity, value);
        } catch (InvalidDataAccessApiUsageException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
    
    protected RowMapper<T> getRowMapper() {
        if (null == rowMapper) {
            rowMapper = newRowMapper();
        }
        
        return rowMapper;
    }
    
    protected RowMapper<T> newRowMapper() {
        return new RowMapper<T>() {
            @Override
            public T mapRow(ResultSet rs, int rowNum) throws SQLException {
                T entity = null;
                try {
                    entity = entityClass.newInstance();
                    ResultSetMetaData metaData = rs.getMetaData();
                    int n = metaData.getColumnCount();
                    for (int i = 1; i <= n; i++) {
                        String columnName = metaData.getColumnLabel(i);
                        if (null != fields.get(columnName)) {
                            if (null != rs.getObject(columnName)) {
                                Class<?> type = fields.get(columnName).getType();
                                if (type == int.class || type == Integer.class) {
                                    setFieldValue(entity, columnName, rs.getInt(columnName));
                                } else if (type == short.class) {
                                    setFieldValue(entity, columnName, rs.getShort(columnName));
                                } else if (type == long.class || type == Long.class) {
                                    setFieldValue(entity, columnName, rs.getLong(columnName));
                                } else if (type == double.class || type == Double.class) {
                                    setFieldValue(entity, columnName, rs.getDouble(columnName));
                                } else if (type == float.class || type == Float.class) {
                                    setFieldValue(entity, columnName, rs.getFloat(columnName));
                                } else if (type == boolean.class || type == Boolean.class) {
                                    setFieldValue(entity, columnName, rs.getBoolean(columnName));
                                } else if (type == Date.class) {
                                    setFieldValue(entity, columnName, new Date(rs.getTimestamp(columnName).getTime()));
                                } else {
                                    setFieldValue(entity, columnName, rs.getString(columnName));
                                }
                            }
                        } else {
                            onFindMissColumn(entity, columnName, rs, rowNum);
                        }
                    }
                    onFind(entity, rs, rowNum);
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                return entity;
            }
        };
    }
    
    protected void onFind(T entity, ResultSet rs, int rowNum) throws SQLException {
    }
    
    protected void onFindMissColumn(T entity, String column, ResultSet rs, int rowNum) throws SQLException {
    }
    
    public SelectSQLBuilder select() {
        return select("*");
    }
    
    public SelectSQLBuilder select(String field) {
        return SelectSQLBuilder.selectTable(table, field);
    }
    
    public SelectSQLBuilder select(String field, String alias) {
        return SelectSQLBuilder.selectTable(table + " " + alias, field);
    }
    
    public int count(SelectSQLBuilder select) {
        return jdbcTemplate.queryForObject(select.toCountSQL(), select.getParams(), Integer.class);
    }
}