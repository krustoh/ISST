package cn.edu.zju.isst.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.zju.isst.dao.ArchiveDao;
import cn.edu.zju.isst.dao.CategoryDao;
import cn.edu.zju.isst.entity.Archive;
import cn.edu.zju.isst.entity.Category;
import cn.edu.zju.isst.service.ArchiveService;

@Service
public class ArchiveServiceImpl implements ArchiveService {
    @Autowired
    private ArchiveDao archiveDao;
    @Autowired
    private CategoryDao categoryDao;
    
    @Override
    public List<Archive> findAll(String categoryAlias, int pageSize, int page) {
        Category category = categoryDao.find(categoryAlias);
        if (null != category) {
            return archiveDao.findAll(category.getId(), Archive.STATUS_PUBLISHED, pageSize, page);
        } else {
            return new ArrayList<Archive>();
        }
    }

    @Override
    public Archive find(int id) {
        return archiveDao.find(id);
    }
}
