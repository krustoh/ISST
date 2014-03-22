package cn.edu.zju.isst.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import cn.edu.zju.isst.dao.UserDao;
import cn.edu.zju.isst.entity.User;

@Repository
public class UserDaoImpl implements UserDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public User find(int id) {
        String sql = "SELECT * FROM users WHERE id=?";
        List<User> list = jdbcTemplate.query(sql, new Integer[] { id }, BeanPropertyRowMapper.newInstance(User.class));
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public User find(String username) {
        String sql = "SELECT * FROM users WHERE username=?";
        List<User> list = jdbcTemplate.query(sql, new String[] { username }, BeanPropertyRowMapper.newInstance(User.class));
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public void updateLoginLocation(User user, double longitude, double latitude) {
        String sql = "REPLACE INTO user_locations SET user_id=?, longitude=?, latitude=?, login_time=?";
        jdbcTemplate.update(sql, new Object[] { user.getId(), longitude, latitude, new Timestamp(System.currentTimeMillis()) });
    }

    @Override
    public void synchronizeUsers() {
        int offset = 0;
        int count =  1000;
        int total = jdbcTemplate.queryForObject("SELECT COUNT(id) FROM students", Integer.class);
        while (offset < total) {
            String sql = new StringBuilder("SELECT ss.id, ss.username, ss.name, ss.password, ss.class_id, ss.email, ss.tel, ss.sexual, cs.major_id, cs.start_year FROM students ss LEFT JOIN classes cs ON cs.id=ss.class_id ORDER BY ss.id ASC LIMIT ")
                .append(offset).append(", ").append(count).toString();
            jdbcTemplate.query(sql, new RowCallbackHandler() {
                @Override
                public void processRow(ResultSet rs) throws SQLException {
                    String s = "REPLACE INTO users SET id=?, username=?, name=?, password=?, class_id=?, major_id=?, grade=?, email=?, phone=?, gender=?, qq='', company='', position='', signature=''";
                    jdbcTemplate.update(s, new Object[] {
                            rs.getLong("id"),
                            rs.getString("username"),
                            rs.getString("name"),
                            rs.getString("password"),
                            rs.getLong("class_id"),
                            rs.getLong("major_id"),
                            rs.getInt("start_year"),
                            rs.getString("email"),
                            rs.getString("tel"),
                            rs.getString("sexual").equals("女") ? 2 : 1
                    });
                }
            });
            offset += count;
        }
    }
    
    public static void main(String[] args) {
        System.out.println(Integer.valueOf("2014年"));
    }
}