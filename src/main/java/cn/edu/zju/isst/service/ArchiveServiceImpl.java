package cn.edu.zju.isst.service;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.zju.isst.common.PaginationList;
import cn.edu.zju.isst.dao.ArchiveDao;
import cn.edu.zju.isst.entity.Archive;
import cn.edu.zju.isst.entity.ArchiveSearchCondition;

@Service
public class ArchiveServiceImpl implements ArchiveService {
    @Autowired
    private ArchiveDao archiveDao;

    @Override
    public Archive find(int id) {
        return archiveDao.find(id);
    }
    
    @Override
    public PaginationList<Archive> findAll(int categoryId, int pageSize, int page) {
        return archiveDao.findAll(categoryId, Archive.STATUS_PUBLISHED, null, pageSize, page);
    }
    
    @Override
    public PaginationList<Archive> findAll(int categoryId, String keywords, int pageSize, int page) {
        return archiveDao.findAll(categoryId, Archive.STATUS_PUBLISHED, keywords, pageSize, page);
    }

    @Override
    public PaginationList<Archive> findAll(int categoryId, ArchiveSearchCondition condition, int pageSize, int page) {
        return archiveDao.findAll(categoryId, condition.getStatus(), condition.getKeywords(), pageSize, page);
    }

    @Override
    public Archive save(Archive archive) {
        archive.setUpdatedAt(new Date());System.out.println(new Date().getTime());System.out.println(new Date());
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