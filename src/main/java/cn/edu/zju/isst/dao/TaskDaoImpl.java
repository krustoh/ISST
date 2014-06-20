package cn.edu.zju.isst.dao;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import cn.edu.zju.isst.common.PaginationList;
import cn.edu.zju.isst.common.SelectSQLBuilder;
import cn.edu.zju.isst.entity.Task;
import cn.edu.zju.isst.entity.TaskSearchCondition;
import cn.edu.zju.isst.entity.TaskSurvey;

@Repository
public class TaskDaoImpl extends AbstractDao<Task> implements TaskDao {
    @Autowired
    private TaskSurveyDao taskSurveyDao;
    
    @Override
    public PaginationList<Task> findListForUser(int userId, int pageSize, int page) {
        SelectSQLBuilder select = select().paging(page, pageSize).where("status=:status").addParam("status", Task.STATUS_PUBLISHED);
        List<Task> items = queryAll(select);
        for (Task task : items) {
            if (task.getType() == Task.TYPE_SURVEY) {
                TaskSurvey taskSurvey = taskSurveyDao.find(task.getId(), userId);
                if (null != taskSurvey) {
                    task.setFinishId(taskSurvey.getId());
                }
            }
        }
        
        return new PaginationList<Task>(page, pageSize, items, this, select);
    }

    @Override
    public PaginationList<Task> findAll(TaskSearchCondition condition, int pageSize, int page) {
        SelectSQLBuilder  select = select().orderBy("updated_at DESC");
        
        if (condition.getStatus() >=0) {
            select.where("status=:status").addParam("status", condition.getStatus());
        }
        
        if (null != condition.getKeywords()) {
            String keywords = condition.getKeywords().trim();
            if (keywords.length() > 0) {
                for (String word : keywords.split(" ")) {
                    select.like("name", word);
                }
            }
        }
        
        if (pageSize > 0) {
            select.paging(page, pageSize);
        }
        
        return new PaginationList<Task>(page, pageSize, this, select);
    }

    @Override
    public int changeStatus(Set<Integer> idset, int status) {
        String sql = String.format("UPDATE %s SET status=:status WHERE %s IN (:idset)", table, primaryKey);
        
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("idset", idset);
        paramSource.addValue("status", status);
        
        return jdbcTemplate.update(sql, paramSource);
    }
}