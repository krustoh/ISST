package cn.edu.zju.isst.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.zju.isst.common.PaginationList;
import cn.edu.zju.isst.common.Result;
import cn.edu.zju.isst.dao.TaskDao;
import cn.edu.zju.isst.dao.TaskSurveyDao;
import cn.edu.zju.isst.entity.Task;
import cn.edu.zju.isst.entity.TaskSurvey;
import cn.edu.zju.isst.entity.TaskSurveySearchCondition;
import cn.edu.zju.isst.form.TaskSurveyForm;

@Service
public class TaskSurveyServiceImpl implements TaskSurveyService {
    @Autowired
    private TaskDao taskDao;
    
    @Autowired
    private TaskSurveyDao taskSurveyDao;
    
    @Override
    public TaskSurvey find(int id) {
        return taskSurveyDao.find(id);
    }

    @Override
    public Result save(TaskSurveyForm form) {
        Result result = form.validate();
        if (!result.valid()) {
            return result;
        }
        
        Task task = taskDao.find(form.getTaskId());
        if (task.getStartTime().getTime() > System.currentTimeMillis() || task.getExpireTime().getTime() < System.currentTimeMillis()) {
            return new Result(46, "任务未开始");
        }
        
        TaskSurvey taskSurvey = null;
        if (form.getId() > 0) {
            taskSurvey = taskSurveyDao.find(form.getId());
        } else {
            taskSurvey = taskSurveyDao.find(form.getTaskId(), form.getUserId());
            if (null != taskSurvey) {
                form.bind(taskSurvey);
            } else {
                taskSurvey = form.build();
            }
        }
        
        taskSurvey.setCreatedAt(new Date());
        if (taskSurvey.getOptionId() > 0) {
            taskSurvey.setOptionOther("");
        }
        
        taskSurveyDao.save(taskSurvey);
        
        result.setBody(taskSurvey);
        
        return result;
    }

    @Override
    public PaginationList<TaskSurvey> findAll(int taskId, TaskSurveySearchCondition condition, int pageSize, int page) {
        return taskSurveyDao.findAll(taskId, condition, pageSize, page);
    }

    @Override
    public TaskSurvey find(int taskId, int userId) {
        return taskSurveyDao.find(taskId, userId);
    }
}