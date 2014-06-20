package cn.edu.zju.isst.service;

import java.util.Set;

import cn.edu.zju.isst.common.PaginationList;
import cn.edu.zju.isst.common.Result;
import cn.edu.zju.isst.entity.Task;
import cn.edu.zju.isst.entity.TaskSearchCondition;
import cn.edu.zju.isst.form.TaskForm;

public interface TaskService {
    public Task find(int id);
    public int delete(Task task);
    public int delete(Set<Integer> idset);
    public int publish(Set<Integer> idset);
    public int hide(Set<Integer> idset);
    public Result save(TaskForm form);
    public PaginationList<Task> findList(TaskSearchCondition condition, int pageSize, int page);
    public PaginationList<Task> findListForUser(int userId, int pageSize, int page);
}