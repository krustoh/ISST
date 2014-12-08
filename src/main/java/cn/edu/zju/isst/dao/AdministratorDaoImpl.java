package cn.edu.zju.isst.dao;

import cn.edu.zju.isst.common.PaginationList;
import cn.edu.zju.isst.common.SelectSQLBuilder;
import org.springframework.stereotype.Repository;

import cn.edu.zju.isst.entity.Administrator;

@Repository
public class AdministratorDaoImpl extends AbstractDao<Administrator> implements AdministratorDao {
    
    @Override
    public Administrator find(String username) {
        return query("SELECT * FROM admins WHERE username=?", username);
    }

    @Override
    public void changePassword(int id, String password) {
        String sql = String.format("UPDATE %s SET password=? WHERE %s=?", table, primaryKey);
        jdbcTemplate.getJdbcOperations().update(sql, Administrator.encryptPassword(password), id);
    }

    @Override
    public PaginationList<Administrator> findAll(int pageSize, int page) {
        SelectSQLBuilder select = select().orderBy("id DESC");

        if (pageSize > 0) {
            select.paging(page, pageSize);
        }

        return new PaginationList<Administrator>(page, pageSize, this, select);
    }
}