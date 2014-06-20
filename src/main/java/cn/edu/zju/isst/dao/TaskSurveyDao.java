package cn.edu.zju.isst.dao;

import cn.edu.zju.isst.common.PaginationList;
import cn.edu.zju.isst.entity.TaskSurvey;

public interface TaskSurveyDao extends Dao<TaskSurvey> {
    public TaskSurvey find(int taskId, int userId);
    public PaginationList<TaskSurvey> findAll(int taskId, int pageSize, int page);
}