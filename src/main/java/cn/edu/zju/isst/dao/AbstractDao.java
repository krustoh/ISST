package cn.edu.zju.isst.dao;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import cn.edu.zju.isst.dao.annotation.Column;
import cn.edu.zju.isst.dao.annotation.Entity;
import cn.edu.zju.isst.dao.annotation.ID;

public abstract class AbstractDao<T> implements Dao<T> {
    @Autowired
    protected JdbcTemplate jdbcTemplate;
    
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
                String getter = "get" + String.valueOf(cs);
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
        jdbcTemplate.update(new PreparedStatementCreator() {
            public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
                StringBuilder sql = new StringBuilder("INSERT INTO ");
                sql.append(table).append(" (");
                int i = 0;
                for (String column : fields.keySet()) {
                    if (i > 0) {
                        sql.append(", ");
                    }
                    sql.append(column);
                    i++;
                }
                sql.append(") VALUES (");
                while ((i--) > 0) {
                    sql.append("?");
                    if (i > 0) {
                        sql.append(", ");
                    }
                }
                sql.append(")");
                PreparedStatement ps = conn.prepareStatement(sql.toString(), new String[] { primaryKey });
                i = 1;
                for (String column : fields.keySet()) {
                    ps.setObject(i++, getFieldValue(entity, column));
                }
                
                return ps;
            }
        }, keyHolder);
        
        setFieldValue(entity, primaryKey, keyHolder.getKey().intValue());
    }
    
    public int update(T entity) {
        StringBuilder sql = new StringBuilder("UPDATE ");
        sql.append(table).append(" SET ");
        List<Object> values = new ArrayList<Object>();
        int i = 0;
        for (String column : fields.keySet()) {
            if (i > 0) {
                sql.append(", ");
            }
            sql.append(column).append("=?");
            values.add(getFieldValue(entity, column));
            i++;
        }
        sql.append(" WHERE ").append(primaryKey).append("=?");
        values.add(getFieldValue(entity, primaryKey));
        
        return jdbcTemplate.update(sql.toString(), values.toArray());
    }
    
    public int delete(T entity) {
        String sql = String.format("DELETE FROM %s WHERE %s=?", table, primaryKey);
        return jdbcTemplate.update(sql, new Object[] { getFieldValue(entity, primaryKey)});
    }
    
    public T find(int id) {
        return findBySql(String.format("SELECT * FROM %s WHERE %s=?", table, primaryKey), id);
    }
    
    public T findBySql(String sql) {
        return findBySql(sql, new Object[] {});
    }
    
    public T findBySql(String sql, Object...params) {
        List<T> list = findAllBySql(sql, params);
        
        if (list.isEmpty()) {
            return null;
        }
        
        return list.get(0);
    }
    
    public List<T> findAllBySql(String sql) {
        return findAllBySql(sql, new Object[] {});
    }
    
    public List<T> findAllBySql(String sql, Object...params) {
        return jdbcTemplate.query(sql, params, getRowMapper());
    }
    
    public boolean exists(int id) {
        String sql = String.format("SELECT COUNT(%s) FROM %s WHERE %s=?", primaryKey, table, primaryKey);
        return jdbcTemplate.queryForObject(sql, new Object[] { id }, Integer.class) > 0 ? true : false;
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
            rowMapper = new RowMapper<T>() {
                @Override
                public T mapRow(ResultSet rs, int rowNum) throws SQLException {
                    T entity = null;
                    try {
                        entity = entityClass.newInstance();
                        ResultSetMetaData metaData = rs.getMetaData();
                        int n = metaData.getColumnCount();
                        for (int i = 1; i <= n; i++) {
                            String columnName = metaData.getColumnName(i);
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
                        onFind(entity);
                    } catch (InstantiationException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                    return entity;
                }
            };
        }
        
        return rowMapper;
    }
    
    protected void onFind(T entity) {
    }
}