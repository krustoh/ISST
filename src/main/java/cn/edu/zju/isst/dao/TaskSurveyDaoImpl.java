package cn.edu.zju.isst.dao;

import org.springframework.stereotype.Repository;

import cn.edu.zju.isst.entity.TaskSurvey;

@Repository
public class TaskSurveyDaoImpl extends AbstractDao<TaskSurvey> implements TaskSurveyDao {

    @Override
    public TaskSurvey find(int taskId, int userId) {
        String sql = "SELECT * FROM task_survey WHERE task_id=? AND user_id=?";
        return query(sql, taskId, userId);
    }

}