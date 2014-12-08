package cn.edu.zju.isst.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.edu.zju.isst.entity.TaskSurveyOption;

@Repository
public class TaskSurveyOptionDaoImpl extends AbstractDao<TaskSurveyOption> implements TaskSurveyOptionDao {

    @Override
    public List<TaskSurveyOption> findAll(int taskId) {
        String sql = "SELECT * FROM task_survey_options WHERE task_id=? ORDER BY id ASC";
        return queryAll(sql, taskId);
    }

}