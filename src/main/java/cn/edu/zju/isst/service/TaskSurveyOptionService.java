package cn.edu.zju.isst.service;

import java.util.List;
import java.util.Set;

import cn.edu.zju.isst.common.Result;
import cn.edu.zju.isst.entity.TaskSurveyOption;
import cn.edu.zju.isst.form.TaskSurveyOptionForm;

public interface TaskSurveyOptionService {
    public List<TaskSurveyOption> findAll(int taskId);
    public TaskSurveyOption find(int id);
    public Result save(TaskSurveyOptionForm form);
    public int delete(Set<Integer> idset);
    public int delete(TaskSurveyOption option);
}