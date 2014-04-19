package cn.edu.zju.isst.entity;

import cn.edu.zju.isst.dao.annotation.Column;
import cn.edu.zju.isst.dao.annotation.Entity;
import cn.edu.zju.isst.dao.annotation.ID;

@Entity("categories")
public class Category {
    @ID
    @Column
    private int id;
    
    @Column
    private String alias;
    
    @Column
    private String name;
    
    @Column("parent_id")
    private int parentId;
    
    @Column("root_id")
    private int rootId;
    
    @Column
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
        return parentId;
    }
    
    public void setParentId(int parentId) {
        this.parentId = parentId;
    }
    
    public int getRootId() {
        return rootId;
    }
    
    public void setRootId(int rootId) {
        this.rootId = rootId;
    }
    
    public int getPriority() {
        return priority;
    }
    
    public void setPriority(int priority) {
        this.priority = priority;
    }
    
    public String toString() {
        return name;
    }
}