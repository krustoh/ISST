package cn.edu.zju.isst.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.zju.isst.common.PaginationList;
import cn.edu.zju.isst.dao.ArchiveDao;
import cn.edu.zju.isst.dao.CategoryDao;
import cn.edu.zju.isst.entity.Archive;
import cn.edu.zju.isst.entity.Category;

@Service
public class ArchiveServiceImpl implements ArchiveService {
    @Autowired
    private ArchiveDao archiveDao;
    @Autowired
    private CategoryDao categoryDao;

    @Override
    public Archive find(int id) {
        return archiveDao.find(id);
    }
    
    @Override
    public PaginationList<Archive> findAll(String categoryAlias, String keywords, int pageSize, int page) {
        Category category = categoryDao.find(categoryAlias);
        if (null != category) {
            return archiveDao.findAll(category.getId(), keywords, Archive.STATUS_PUBLISHED, pageSize, page);
        } else {
            return new PaginationList<Archive>(page, pageSize);
        }
    }
}