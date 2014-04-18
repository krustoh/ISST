package cn.edu.zju.isst.dao;

import java.sql.ResultSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cn.edu.zju.isst.common.PaginationList;
import cn.edu.zju.isst.common.SelectSQLBuilder;
import cn.edu.zju.isst.entity.JobComment;

@Repository
public class JobCommentDaoImpl extends AbstractDao<JobComment> implements JobCommentDao {
    @Autowired
    private StudentUserDao studentUserDao;

    @Override
    public PaginationList<JobComment> findAll(int jobId, int pageSize, int page) {
        SelectSQLBuilder select = select("*").where("job_id=:jobId").addParam("jobId", jobId);
        
        if (pageSize > 0) {
            select.paging(page, pageSize);
        }
        
        select.orderBy("created_at DESC");
        
        return new PaginationList<JobComment>(page, pageSize, this, select);
    }
    
    @Override
    protected void onFind(JobComment entity, ResultSet rs, int rowNum) {
        if (entity.getUserId() > 0) {
            entity.setUser(studentUserDao.findUserSummary(entity.getUserId()));
        }
    }
}