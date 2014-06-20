package cn.edu.zju.isst.service;

import java.util.List;

import cn.edu.zju.isst.entity.TaskSurveyOption;

public interface TaskSurveyOptionService {
    public List<TaskSurveyOption> findAll(int taskId);
}