package cn.edu.zju.isst.dao;

import cn.edu.zju.isst.entity.Student;

public interface StudentDao extends Dao<Student> {
    public boolean checkUsername(String username);
    public boolean checkUsername(String username, int id);
    public String changePassword(int id, String password);
}