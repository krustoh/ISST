package cn.edu.zju.isst.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.zju.isst.common.Result;
import cn.edu.zju.isst.dao.TaskSurveyOptionDao;
import cn.edu.zju.isst.entity.TaskSurveyOption;
import cn.edu.zju.isst.form.TaskSurveyOptionForm;

@Service
public class TaskSurveyOptionServiceImpl implements TaskSurveyOptionService {
    @Autowired
    private TaskSurveyOptionDao taskSurveyOptionDao;

    @Override
    public List<TaskSurveyOption> findAll(int taskId) {
        return taskSurveyOptionDao.findAll(taskId);
    }

    @Override
    public TaskSurveyOption find(int id) {
        return taskSurveyOptionDao.find(id);
    }

    @Override
    public Result save(TaskSurveyOptionForm form) {
        TaskSurveyOption option;
        if (form.getId() > 0) {
            option = taskSurveyOptionDao.find(form.getId());
            form.bind(option);
        } else {
            option = form.build();
        }
        
        taskSurveyOptionDao.save(option);
        
        return new Result(option);
    }

    @Override
    public int delete(Set<Integer> idset) {
        return taskSurveyOptionDao.delete(idset);
    }

    @Override
    public int delete(TaskSurveyOption option) {
        return taskSurveyOptionDao.delete(option);
    }

}