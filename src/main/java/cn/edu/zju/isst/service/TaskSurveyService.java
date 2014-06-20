package cn.edu.zju.isst.service;

import cn.edu.zju.isst.common.PaginationList;
import cn.edu.zju.isst.common.Result;
import cn.edu.zju.isst.entity.TaskSurvey;
import cn.edu.zju.isst.form.TaskSurveyForm;

public interface TaskSurveyService {
    public TaskSurvey find(int id);
    public Result save(TaskSurveyForm form);
    public PaginationList<TaskSurvey> findAll(int taskId, int pageSize, int page);
}