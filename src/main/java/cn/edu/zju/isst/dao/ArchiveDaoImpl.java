package cn.edu.zju.isst.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cn.edu.zju.isst.common.PaginationList;
import cn.edu.zju.isst.common.SelectSQLBuilder;
import cn.edu.zju.isst.entity.Archive;

@Repository
public class ArchiveDaoImpl extends AbstractDao<Archive> implements ArchiveDao {
    @Autowired
    private UserDao userDao;
    
    @Override
    public PaginationList<Archive> findAll(int categoryId, String keywords, int status, int pageSize, int page) {
        SelectSQLBuilder select = select("id, category_id, user_id, title, description, updated_at, status")
                .where("category_id=:category_id")
                .addParam("category_id", categoryId)
                .orderBy("updated_at DESC, id DESC");
        
        if (status >= 0) {
            select.where("status=:status").addParam("status", status);
        }
        
        if (null != keywords) {
            keywords = keywords.trim();
            if (keywords.length() > 0) {
                select.like("title", keywords);
            }
        }
        
        if (pageSize > 0) {
            select.paging(page, pageSize);
        }
        
        return new PaginationList<Archive>(page, pageSize, this, select);
    }
    
    @Override
    protected void onFind(Archive entity) {
        if (entity.getUserId() > 0) {
            entity.setUser(userDao.findUserSummary(entity.getUserId()));
        }
    }
}