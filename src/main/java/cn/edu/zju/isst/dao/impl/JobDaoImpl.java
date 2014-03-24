package cn.edu.zju.isst.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import cn.edu.zju.isst.dao.JobDao;
import cn.edu.zju.isst.dao.UserDao;
import cn.edu.zju.isst.entity.Job;
import cn.edu.zju.isst.entity.UserSummary;

@Repository
public class JobDaoImpl implements JobDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private UserDao userDao;

    @Override
    public Job find(int id) {
        String sql = "SELECT * FROM jobs WHERE id=?";
        
        List<Job> list = jdbcTemplate.query(sql, new Object[] { id}, BeanPropertyRowMapper.newInstance(Job.class));
        if (list.isEmpty()) {
            return null;
        }
        
        Job job = list.get(0);
        if (job.getUserId() > 0) {
            job.setUser(userDao.findUserSummary(job.getUserId()));
        }
        
        return job;
    }

    @Override
    public List<Job> findAll(int categoryId, int status, int pageSize, int page) {
        StringBuilder sb = new StringBuilder("SELECT id, category_id, user_id, city_id, title, company, position, updated_at FROM jobs WHERE category_id=? AND status=? ORDER BY updated_at DESC, id DESC");
        
        if (pageSize > 0) {
            int offset = page > 0 ? ((page - 1) * pageSize) : 0;
            sb.append(" LIMIT ").append(offset).append(", ").append(pageSize);
        }
        
        final Map<Integer, UserSummary> userSummaryMap = new HashMap<Integer, UserSummary>(); 
        return jdbcTemplate.query(sb.toString(), new Object[] { categoryId, status }, new RowMapper<Job>() {
            @Override
            public Job mapRow(ResultSet rs, int rowNum) throws SQLException {
                Job job = new Job();
                job.setId(rs.getInt("id"));
                job.setCategoryId(rs.getInt("category_id"));
                job.setTitle(rs.getString("title"));
                job.setCompany(rs.getString("company"));
                job.setPosition(rs.getString("position"));
                job.setCityId(rs.getInt("city_id"));
                job.setUpdatedAt(rs.getString("updated_at"));
                job.setUserId(rs.getInt("user_id"));
                
                if (job.getUserId() > 0) {
                    Integer userId = Integer.valueOf(job.getUserId());
                    UserSummary summary = userSummaryMap.get(userId);
                    if (summary == null) {
                        userSummaryMap.put(userId, userDao.findUserSummary(job.getUserId()));
                    }
                    job.setUser(userSummaryMap.get(userId));
                }
                
                return job;
            }
        });
    }
}