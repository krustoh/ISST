package cn.edu.zju.isst.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cn.edu.zju.isst.common.PaginationList;
import cn.edu.zju.isst.common.SelectSQLBuilder;
import cn.edu.zju.isst.entity.Job;

@Repository
public class JobDaoImpl extends AbstractDao<Job> implements JobDao {
    @Autowired
    private UserDao userDao;

    @Override
    public PaginationList<Job> findAll(int categoryId, String keywords, int status, int pageSize, int page) {
        SelectSQLBuilder select = select("id, category_id, user_id, city_id, title, company, position, updated_at")
                .where("category_id=:category_id AND status=:status")
                .addParam("category_id", categoryId).addParam("status", status)
                .orderBy("updated_at DESC, id DESC");
        
        if (null != keywords) {
            keywords = keywords.trim();
            if (keywords.length() > 0) {
                select.like("title", keywords).like("company", keywords).like("position", keywords);
            }
        }
        
        if (pageSize > 0) {
            select.paging(page, pageSize);
        }
        
        return new PaginationList<Job>(page, pageSize, this, select);
    }
    
    @Override
    protected void onFind(Job entity) {
        if (entity.getUserId() > 0) {
            entity.setUser(userDao.findUserSummary(entity.getUserId()));
        }
    }
}