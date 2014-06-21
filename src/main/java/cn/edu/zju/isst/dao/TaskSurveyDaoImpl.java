package cn.edu.zju.isst.dao;

import org.springframework.stereotype.Repository;

import cn.edu.zju.isst.common.PaginationList;
import cn.edu.zju.isst.common.SelectSQLBuilder;
import cn.edu.zju.isst.entity.TaskSurvey;

@Repository
public class TaskSurveyDaoImpl extends AbstractDao<TaskSurvey> implements TaskSurveyDao {

    @Override
    public TaskSurvey find(int taskId, int userId) {
        String sql = "SELECT * FROM task_survey WHERE task_id=? AND user_id=?";
        return query(sql, taskId, userId);
    }

    @Override
    public PaginationList<TaskSurvey> findAll(int taskId, int pageSize, int page) {
        SelectSQLBuilder select = select()
                .where("task_id=:task_id").addParam("task_id", taskId)
                .orderBy("updated_at DESC");
        
        if (pageSize > 0) {
            select.paging(page, pageSize);
        }
        
        return new PaginationList<TaskSurvey>(page, pageSize, this, select);
    }

}