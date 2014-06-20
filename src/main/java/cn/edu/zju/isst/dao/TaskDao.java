package cn.edu.zju.isst.dao;

import java.util.Set;

import cn.edu.zju.isst.common.PaginationList;
import cn.edu.zju.isst.entity.Task;
import cn.edu.zju.isst.entity.TaskSearchCondition;

public interface TaskDao extends Dao<Task> {
    public PaginationList<Task> findAll(TaskSearchCondition condition, int pageSize, int page);
    public PaginationList<Task> findListForUser(int userId, int pageSize, int page);
    public int changeStatus(Set<Integer> idset, int status);
}