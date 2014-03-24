package cn.edu.zju.isst.common;

import java.util.ArrayList;
import java.util.List;

public class SelectSQLBuilder<T extends SelectSQLBuilder<T>> {
    private String table;
    private StringBuilder fields = new StringBuilder();
    private StringBuilder where = new StringBuilder();
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
    
    @SuppressWarnings("unchecked")
    public T from(String table, String fields) {
        this.table = table;
        select(fields);
        
        return (T) this;
    }
    
    @SuppressWarnings("unchecked")
    public T leftJoin(String table, String condition) {
        join(JointType.LEFT_JOIN, table, condition);
        
        return (T) this;
    }
    
    @SuppressWarnings("unchecked")
    public T rigntJoin(String table, String condition) {
        join(JointType.RIGHT_JOIN, table, condition);
        
        return (T) this;
    }
    
    @SuppressWarnings("unchecked")
    public T innerJoin(String table, String condition) {
        join(JointType.INNER_JOIN, table, condition);
        
        return (T) this;
    }
    
    private void join(JointType jointType, String table, String condition) {
        if (null == joints) {
            joints = new ArrayList<Joint>();
        }
        
        joints.add(new Joint(jointType, table, condition));
    }
    
    @SuppressWarnings("unchecked")
    public T select(String fields) {
        if (this.fields.length() > 0) {
            this.fields.append(", ");
        }

        this.fields.append(fields);
        
        return (T) this;
    }
    
    @SuppressWarnings("unchecked")
    public T where(String condition) {
        if (where.length() > 0) {
            where.append(" AND ");
        }
        
        where.append(condition);
        
        return (T) this;
    }
    
    @SuppressWarnings("unchecked")
    public T orderBy(String orderBy) {
        this.orderBy = orderBy;
        
        return (T) this;
    }
    
    @SuppressWarnings("unchecked")
    public T limit(int count, int offset) {
        this.count = count;
        this.offset = offset;
        
        return (T) this;
    }
    
    @SuppressWarnings("unchecked")
    public T limit(int count) {
        this.count = count;
        
        return (T) this;
    }
    
    @SuppressWarnings("unchecked")
    public T paging(int page, int pageSize) {
        limit(pageSize, (page - 1) * pageSize);
        
        return (T) this;
    }
    
    public String toSQL() {
        return parseSQL(false);
    }
    
    public String toCOUNTSQL() {
        return parseSQL(true);
    }
    
    private String parseSQL(boolean isCountSQL) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ").append(isCountSQL ? "COUNT(*)" : fields).append(" FROM ").append(table);
        
        if (null != joints) {
            for (Joint joint : joints) {
                sb.append(" ").append(joint.type).append(joint.table).append(" ON ").append(joint.condition);
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
    
    @SuppressWarnings("rawtypes")
    public static SelectSQLBuilder selectTable(String table, String fields) {
        return new SelectSQLBuilder(table, fields);
    }
}