package cn.edu.zju.isst.service;

import java.util.List;
import java.util.Set;

import cn.edu.zju.isst.common.PaginationList;
import cn.edu.zju.isst.entity.Archive;
import cn.edu.zju.isst.entity.Category;

public interface ArchiveService {
    public Archive save(Archive archive);
    public Archive find(int id);
    public List<Archive> findAll(Set<Integer> ids);
    public PaginationList<Archive> findAll(String categoryAlias, String keywords, int pageSize, int page);
    public PaginationList<Archive> findAll(Category category, String keywords, int pageSize, int page);
    public int delete(Set<Integer> ids);
    public void delete(Archive archive);
}