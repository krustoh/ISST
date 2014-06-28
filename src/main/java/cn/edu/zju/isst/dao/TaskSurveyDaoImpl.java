package cn.edu.zju.isst.dao;

import org.springframework.stereotype.Repository;

import cn.edu.zju.isst.common.PaginationList;
import cn.edu.zju.isst.common.SelectSQLBuilder;
import cn.edu.zju.isst.entity.TaskSurvey;
import cn.edu.zju.isst.entity.TaskSurveySearchCondition;

@Repository
public class TaskSurveyDaoImpl extends AbstractDao<TaskSurvey> implements TaskSurveyDao {

    @Override
    public TaskSurvey find(int taskId, int userId) {
        String sql = "SELECT * FROM task_survey WHERE task_id=? AND user_id=?";
        return query(sql, taskId, userId);
    }

    @Override
    public PaginationList<TaskSurvey> findAll(int taskId, TaskSurveySearchCondition condition,int pageSize, int page) {
        SelectSQLBuilder select = select()
                .where("task_id=:task_id").addParam("task_id", taskId)
                .orderBy("updated_at DESC");
        
        if (condition.getOptionId() > 0) {
            select.where("option_id=:option_id").addParam("option_id", condition.getOptionId());
        }
        
        if (pageSize > 0) {
            select.paging(page, pageSize);
        }
        
        return new PaginationList<TaskSurvey>(page, pageSize, this, select);
    }

}