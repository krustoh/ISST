package cn.edu.zju.isst.service;

import java.util.Date;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.zju.isst.common.PaginationList;
import cn.edu.zju.isst.common.Result;
import cn.edu.zju.isst.dao.TaskDao;
import cn.edu.zju.isst.entity.Task;
import cn.edu.zju.isst.entity.TaskSearchCondition;
import cn.edu.zju.isst.form.TaskForm;

@Service
public class TaskServiceImpl implements TaskService {
    @Autowired
    private TaskDao taskDao;
    
    @Override
    public PaginationList<Task> findListForUser(int userId, int pageSize, int page) {
        return taskDao.findListForUser(userId, pageSize, page);
    }

    @Override
    public Task find(int id) {
        return taskDao.find(id);
    }

    @Override
    public Result save(TaskForm form) {
        Task task = null;
        if (form.getId() > 0) {
            task = taskDao.find(form.getId());
            form.bind(task);
        } else {
            task = form.build();
        }
        
        task.setUpdatedAt(new Date());
        taskDao.save(task);
        
        return new Result(task);
    }

    @Override
    public PaginationList<Task> findList(TaskSearchCondition condition, int pageSize, int page) {
        return taskDao.findAll(condition, pageSize, page);
    }

    @Override
    public int delete(Task task) {
        return taskDao.delete(task);
    }

    @Override
    public int delete(Set<Integer> idset) {
        return taskDao.delete(idset);
    }

    @Override
    public int publish(Set<Integer> idset) {
        return taskDao.changeStatus(idset, Task.STATUS_PUBLISHED);
    }

    @Override
    public int hide(Set<Integer> idset) {
        return taskDao.changeStatus(idset, Task.STATUS_HIDDEN);
    }

}