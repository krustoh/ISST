package cn.edu.zju.isst.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.zju.isst.dao.ArchiveDao;
import cn.edu.zju.isst.entity.Archive;

@Service
public class ArchiveServiceImpl implements ArchiveService {
    @Autowired
    private ArchiveDao archiveDao;

    @Override
    public Archive find(int id) {
        return archiveDao.find(id);
    }
}