package cn.edu.zju.isst.service;

import java.util.Date;
import java.util.List;
import java.util.Set;

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
        return findAll(category, Archive.STATUS_PUBLISHED, keywords, pageSize, page);
    }
    
    @Override
    public PaginationList<Archive> findAll(String categoryAlias, int status, String keywords, int pageSize, int page) {
        Category category = categoryDao.find(categoryAlias);
        return findAll(category, status, keywords, pageSize, page);
    }

    @Override
    public PaginationList<Archive> findAll(Category category, int status, String keywords, int pageSize, int page) {
        if (null != category) {
            return archiveDao.findAll(category.getId(), keywords, status, pageSize, page);
        } else {
            return new PaginationList<Archive>(page, pageSize);
        }
    }

    @Override
    public Archive save(Archive archive) {
        archive.setUpdatedAt(new Date());
        archive.setDescriptionFromContent(archive.getContent());
        archiveDao.save(archive);
        
        return archive;
    }

    @Override
    public int delete(Set<Integer> ids) {
        return archiveDao.delete(ids);
    }

    @Override
    public void delete(Archive archive) {
        archiveDao.delete(archive);
    }

    @Override
    public List<Archive> findAll(Set<Integer> idset) {
        return archiveDao.findAll(idset);
    }

    @Override
    public int publish(Set<Integer> idset) {
        return archiveDao.changeStatus(idset, Archive.STATUS_PUBLISHED);
    }

    @Override
    public int hide(Set<Integer> idset) {
        return archiveDao.changeStatus(idset, Archive.STATUS_HIDDEN);
    }
}