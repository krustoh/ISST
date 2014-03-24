package cn.edu.zju.isst.entity;

public class Category {
    private int id;
    private String alias;
    private String name;
    private int parent_id;
    private int root_id;
    private int priority;
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getAlias() {
        return alias;
    }
    
    public void setAlias(String alias) {
        this.alias = alias;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public int getParentId() {
        return parent_id;
    }
    
    public void setParentId(int parent_id) {
        this.parent_id = parent_id;
    }
    
    public int getRootId() {
        return root_id;
    }
    
    public void setRootId(int root_id) {
        this.root_id = root_id;
    }
    
    public int getPriority() {
        return priority;
    }
    
    public void setPriority(int priority) {
        this.priority = priority;
    }
}
