package cn.edu.zju.isst.dao;

import java.util.List;

import cn.edu.zju.isst.entity.TaskSurveyOption;

public interface TaskSurveyOptionDao extends Dao<TaskSurveyOption>{
    public List<TaskSurveyOption> findAll(int taskId);
}