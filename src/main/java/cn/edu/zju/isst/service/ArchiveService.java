package cn.edu.zju.isst.service;

import cn.edu.zju.isst.common.PaginationList;
import cn.edu.zju.isst.entity.Archive;
import cn.edu.zju.isst.entity.Category;
import cn.edu.zju.isst.form.ArchiveForm;

public interface ArchiveService {
    public Archive save(ArchiveForm form);
    public Archive find(int id);
    public PaginationList<Archive> findAll(String categoryAlias, String keywords, int pageSize, int page);
    public PaginationList<Archive> findAll(Category category, String keywords, int pageSize, int page);
}