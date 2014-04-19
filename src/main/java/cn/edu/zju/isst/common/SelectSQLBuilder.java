package cn.edu.zju.isst.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SelectSQLBuilder {
    private int likeParamNameCount = 0;
    
    private String table;
    private StringBuilder fields = new StringBuilder();
    private StringBuilder where = new StringBuilder();
    private Map<String, Object> params; 
    private List<Joint> joints;
    private String orderBy;
    private int count;
    private int offset;
    
    enum JointType {
        INNER_JOIN("INNER JOIN"), LEFT_JOIN("LEFT JOIN"), RIGHT_JOIN("RIGHT JOIN");
        private String jointType;
        JointType(String jointType) {
            this.jointType = jointType;
        }
        public String toString() {
            return jointType;
        }
    }
    
    class Joint {
        public final JointType type;
        public final String table;
        public final String condition;
        
        Joint(JointType type, String table, String condition) {
            this.type = type;
            this.table = table;
            this.condition = condition;
        }
    }
    
    public SelectSQLBuilder() {
        
    }
    
    public SelectSQLBuilder(String table) {
        this.from(table, "*");
    }
    
    public SelectSQLBuilder(String table, String fields) {
        this.from(table, fields);
    }
    
    public SelectSQLBuilder from(String table, String fields) {
        this.table = table;
        select(fields);
        
        return this;
    }
    
    public SelectSQLBuilder leftJoin(String table, String condition) {
        join(JointType.LEFT_JOIN, table, condition);
        
        return this;
    }
    
    public SelectSQLBuilder leftJoin(String table, String condition, String fields) {
        select(fields);
        
        return leftJoin(table, condition);
    }
    
    public SelectSQLBuilder rigntJoin(String table, String condition) {
        join(JointType.RIGHT_JOIN, table, condition);
        
        return this;
    }
    
    public SelectSQLBuilder rigntJoin(String table, String condition, String fields) {
        select(fields);
        
        return rigntJoin(table, condition);
    }
    
    public SelectSQLBuilder innerJoin(String table, String condition) {
        join(JointType.INNER_JOIN, table, condition);
        
        return this;
    }
    
    public SelectSQLBuilder innerJoin(String table, String condition, String fields) {
        select(fields);
        
        return innerJoin(table, condition);
    }
    
    private void join(JointType jointType, String table, String condition) {
        if (null == joints) {
            joints = new ArrayList<Joint>();
        }
        
        joints.add(new Joint(jointType, table, condition));
    }
    
    public SelectSQLBuilder select(String fields) {
        if (null != fields) {
            if (this.fields.length() > 0) {
                this.fields.append(", ");
            }

            this.fields.append(fields);
        }
        
        return this;
    }
    
    public SelectSQLBuilder where(String condition) {
        if (where.length() > 0) {
            where.append(" AND ");
        }
        
        where.append(condition);
        
        return this;
    }
    
    public SelectSQLBuilder like(String column, String value) {
        String paramName = parseParamName(column);
        where(String.format("%s LIKE :%s", column, paramName));
        addlikeParam(paramName, value);
        
        return this;
    }
    
    public SelectSQLBuilder like(String value, String... columns) {
        StringBuilder sb = new StringBuilder();
        for (String column : columns) {
            if (sb.length() > 0) {
                sb.append(" OR ");
            }
            String paramName = parseParamName(column + "_" + (likeParamNameCount++));
            sb.append(String.format("%s LIKE :%s", column, paramName));
            addlikeParam(paramName, value);
        }
        where(String.format("(%s)", sb.toString()));
        
        return this;
    }
    
    private void addlikeParam(String column, String value) {
        value = value.replaceAll("\\\\","\\\\\\\\")
                .replaceAll("_","\\\\_")
                .replaceAll("%", "\\\\%");
        addParam(column, "%" + value + "%");
    }
    
    private String parseParamName(String column) {
        return column.replace('.', '_');
    }
    
    public SelectSQLBuilder addParam(String column, Object value) {
        if (null == params) {
            params = new HashMap<String, Object>();
        }
        
        params.put(column, value);
        
        return this;
    }
    
    public Map<String, Object> getParams() {
        return params;
    }
    
    public SelectSQLBuilder orderBy(String orderBy) {
        this.orderBy = orderBy;
        
        return this;
    }
    
    public SelectSQLBuilder limit(int count, int offset) {
        this.count = count;
        this.offset = offset;
        
        return this;
    }
    
    public SelectSQLBuilder limit(int count) {
        this.count = count;
        
        return this;
    }
    
    public SelectSQLBuilder paging(int page, int pageSize) {
        limit(pageSize, (page - 1) * pageSize);
        
        return this;
    }
    
    public String toSQL() {
        return parseSQL(false);
    }
    
    public String toCountSQL() {
        return parseSQL(true);
    }
    
    private String parseSQL(boolean isCountSQL) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ").append(isCountSQL ? "COUNT(*)" : fields).append(" FROM ").append(table);
        
        if (null != joints) {
            for (Joint joint : joints) {
                sb.append(" ").append(joint.type).append(" ").append(joint.table).append(" ON ").append(joint.condition);
            }
        }
        
        if (where.length() > 0) {
            sb.append(" WHERE ").append(where.toString());
        }
        
        if (null != orderBy) {
            sb.append(" ORDER BY ").append(orderBy);
        }
        
        if (!isCountSQL) {
            if (count > 0 || offset > 0) {
                sb.append(" LIMIT ").append(offset).append(", ").append(count);
            }
        }
        
        return sb.toString();
    }
    
    public String toString() {
        return toSQL();
    }
    
    public static SelectSQLBuilder selectTable(String table, String fields) {
        return new SelectSQLBuilder(table, fields);
    }
}