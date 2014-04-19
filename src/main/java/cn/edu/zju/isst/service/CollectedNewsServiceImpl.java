package cn.edu.zju.isst.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.zju.isst.collection.CSTCollectionSource;
import cn.edu.zju.isst.collection.CollectionEntity;
import cn.edu.zju.isst.common.PaginationList;
import cn.edu.zju.isst.dao.ArchiveDao;
import cn.edu.zju.isst.dao.CollectedNewsDao;
import cn.edu.zju.isst.dao.JobDao;
import cn.edu.zju.isst.entity.Archive;
import cn.edu.zju.isst.entity.CollectedNews;
import cn.edu.zju.isst.entity.Job;

@Service
public class CollectedNewsServiceImpl implements CollectedNewsService {
    @Autowired
    private CollectedNewsDao collectedNewsDao;
    @Autowired
    private ArchiveDao archiveDao;
    @Autowired
    private JobDao jobDao;
    
    public List<CollectedNews> collectForArchive(int categoryId) {
        return collect(categoryId, new String[]{
                "重要通知", "学生思政", "新闻中心", "教务信息", "合作科研", "招生信息"
            });
    }

    
    @Override
    public List<CollectedNews> collectForJob(int categoryId) {
        return collect(categoryId, new String[]{
                "实习就业"
            });
    }

    private List<CollectedNews> collect(int categoryId, String[] allowedTypes) {
        List<CollectedNews> list = new ArrayList<CollectedNews>();
        Date createdAt = new Date();
        
        List<CollectionEntity> entities = CSTCollectionSource.collectList(allowedTypes);
        
        for (CollectionEntity entity : entities) {
            if (!collectedNewsDao.hasCollected(entity.getId())) {
                CollectedNews collectedNews = new CollectedNews(entity);
                collectedNews.setCreatedAt(createdAt);
                collectedNews.setCategoryId(categoryId);
                collectedNewsDao.insert(collectedNews);
                
                list.add(collectedNews);
            }
        }
        
        return list;
    }
    
    @Override
    public CollectedNews collectDetail(int id) {
        CollectedNews news = collectedNewsDao.find(id);
        if (null != news) {
            CollectionEntity entity = new CollectionEntity();
            entity.setId(news.getSourceId());
            entity.setUrl(news.getUrl());
            entity.setTitle(news.getTitle());
            
            CSTCollectionSource.parseDetail(entity);
            news.setContent(entity.getContent());
            news.setPostTime(entity.getPostTime());
            
            collectedNewsDao.save(news);
        }
        return news;
    }

    @Override
    public int publishForArchive(Set<Integer> idset) {
        List<CollectedNews> collectedNews = collectedNewsDao.findAll(idset);
        int count = 0;
        for (CollectedNews news : collectedNews) {
            if (news.getPostId() == 0) {
                Archive archive = new Archive();
                archive.setTitle(news.getTitle());
                archive.setContent(news.getContent());
                archive.setUpdatedAt(news.getPostTime());
                archive.setStatus(Archive.STATUS_PUBLISHED);
                archive.setDescriptionFromContent(news.getContent());
                archive.setCategoryId(news.getCategoryId());
                archiveDao.insert(archive);
                
                news.setPostId(archive.getId());
                collectedNewsDao.update(news);
                
                count++;
            }
        }
        
        return count;
    }

    @Override
    public int publishForJob(Set<Integer> idset) {
        List<CollectedNews> collectedNews = collectedNewsDao.findAll(idset);
        int count = 0;
        for (CollectedNews news : collectedNews) {
            if (news.getPostId() == 0) {
                Job job = new Job();
                job.setTitle(news.getTitle());
                job.setContent(news.getContent());
                job.setUpdatedAt(news.getPostTime());
                job.setStatus(Archive.STATUS_PUBLISHED);
                job.setDescriptionFromContent(news.getContent());
                job.setCategoryId(news.getCategoryId());
                //job.setCompany();
                //job.setPosition();
                //job.setCityId();
                jobDao.insert(job);
                
                news.setPostId(job.getId());
                collectedNewsDao.update(news);
                
                count++;
            }
        }
        
        return count;
    }

    @Override
    public CollectedNews find(int id) {
        return collectedNewsDao.find(id);
    }

    @Override
    public PaginationList<CollectedNews> findAll(int categoryId, int published, int pageSize, int page) {
        return collectedNewsDao.findAll(categoryId, published, pageSize, page);
    }

    @Override
    public List<CollectedNews> findAll(Set<Integer> idset) {
        return collectedNewsDao.findAll(idset);
    }

    @Override
    public int delete(CollectedNews news) {
        return collectedNewsDao.delete(news);
    }

    @Override
    public int delete(Set<Integer> idset) {
        return collectedNewsDao.delete(idset);
    }

    @Override
    public void save(CollectedNews news) {
        collectedNewsDao.save(news);
    }
}