package cn.edu.zju.isst.dao;

import java.util.List;
import java.util.Set;

import cn.edu.zju.isst.common.PaginationList;
import cn.edu.zju.isst.entity.StudentUser;
import cn.edu.zju.isst.entity.UserSearchCondition;
import cn.edu.zju.isst.entity.UserSummary;

public interface StudentUserDao extends DaoPagingable<StudentUser> {
    public StudentUser find(String username);
    public StudentUser find(int id);
    public StudentUser findAlumnus(int id);
    public List<StudentUser> findAll(Set<Integer> idset);
    public UserSummary findUserSummary(int id);
    public PaginationList<StudentUser> findAlumni(UserSearchCondition condition, int pageSize, int page);
    public PaginationList<StudentUser> findAll(UserSearchCondition condition, int pageSize, int page);
    public void save(StudentUser studentUser);
}