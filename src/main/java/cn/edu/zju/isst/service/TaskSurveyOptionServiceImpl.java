package cn.edu.zju.isst.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.zju.isst.dao.TaskSurveyOptionDao;
import cn.edu.zju.isst.entity.TaskSurveyOption;

@Service
public class TaskSurveyOptionServiceImpl implements TaskSurveyOptionService {
    @Autowired
    private TaskSurveyOptionDao taskSurveyOptionDao;

    @Override
    public List<TaskSurveyOption> findAll(int taskId) {
        return taskSurveyOptionDao.findAll(taskId);
    }

}