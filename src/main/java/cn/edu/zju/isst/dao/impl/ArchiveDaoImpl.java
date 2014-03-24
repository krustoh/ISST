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

import cn.edu.zju.isst.common.SelectSQLBuilder;
import cn.edu.zju.isst.dao.ArchiveDao;
import cn.edu.zju.isst.dao.UserDao;
import cn.edu.zju.isst.entity.Archive;
import cn.edu.zju.isst.entity.UserSummary;

@Repository
public class ArchiveDaoImpl implements ArchiveDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private UserDao userDao;

    public class ArchiveSelectSQLBuilder extends SelectSQLBuilder<ArchiveSelectSQLBuilder> {
        public ArchiveSelectSQLBuilder(String fields) {
            super("archives a", fields);
        }
        
        public ArchiveSelectSQLBuilder published() {
            this.where("a.status=1");
            
            return this;
        }
    }
    
    @Override
    public List<Archive> findAll(int categoryId, int status, int pageSize, int page) {
        StringBuilder sb = new StringBuilder("SELECT id, category_id, user_id, title, description, updated_at FROM archives WHERE category_id=? AND status=? ORDER BY updated_at DESC, id DESC");
        
        if (pageSize > 0) {
            int offset = page > 0 ? ((page - 1) * pageSize) : 0;
            sb.append(" LIMIT ").append(offset).append(", ").append(pageSize);
        }
        
        final Map<Integer, UserSummary> userSummaryMap = new HashMap<Integer, UserSummary>(); 
        return jdbcTemplate.query(sb.toString(), new Object[] { categoryId, status }, new RowMapper<Archive>() {
            @Override
            public Archive mapRow(ResultSet rs, int rowNum) throws SQLException {
                Archive archive = new Archive();
                archive.setId(rs.getInt("id"));
                archive.setCategoryId(rs.getInt("category_id"));
                archive.setTitle(rs.getString("title"));
                archive.setDescription(rs.getString("description"));
                archive.setUpdatedAt(rs.getString("updated_at"));
                archive.setUserId(rs.getInt("user_id"));
                
                if (archive.getUserId() > 0) {
                    Integer userId = Integer.valueOf(archive.getUserId());
                    UserSummary summary = userSummaryMap.get(userId);
                    if (summary == null) {
                        userSummaryMap.put(userId, userDao.findUserSummary(archive.getUserId()));
                    }
                    archive.setUser(userSummaryMap.get(userId));
                }
                
                return archive;
            }
        });
    }

    @Override
    public Archive find(int id) {
        String sql = "SELECT * FROM archives WHERE id=?";
        
        List<Archive> list = jdbcTemplate.query(sql, new Object[] { id}, BeanPropertyRowMapper.newInstance(Archive.class));
        if (list.isEmpty()) {
            return null;
        }
        
        Archive archive = list.get(0);
        if (archive.getUserId() > 0) {
            archive.setUser(userDao.findUserSummary(archive.getUserId()));
        }
        
        return archive;
    }
    
    public ArchiveSelectSQLBuilder select(String fields) {
        return new ArchiveSelectSQLBuilder(fields);
    }
}