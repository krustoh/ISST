package cn.edu.zju.isst.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import cn.edu.zju.isst.common.PaginationList;
import cn.edu.zju.isst.common.SelectSQLBuilder;
import cn.edu.zju.isst.entity.City;
import cn.edu.zju.isst.entity.Klass;
import cn.edu.zju.isst.entity.Major;
import cn.edu.zju.isst.entity.PrivateProfile;
import cn.edu.zju.isst.entity.User;
import cn.edu.zju.isst.entity.UserSearchCondition;
import cn.edu.zju.isst.entity.UserSummary;

@Repository
public class UserDaoImpl extends AbstractDao<User> implements UserDao {
    @Autowired
    private CityDao cityDao;

    @Override
    public User find(String username) {
        return query("SELECT * FROM users WHERE username=?", username);
    }

    @Override
    public void updateLoginLocation(User user, double longitude, double latitude) {
        String sql = "REPLACE INTO user_locations SET user_id=?, longitude=?, latitude=?, login_time=?";
        jdbcTemplate.getJdbcOperations().update(sql, new Object[] { user.getId(), longitude, latitude, new Timestamp(System.currentTimeMillis()) });
    }

    @Override
    public void synchronizeUsers() {
        int offset = 0;
        int count =  1000;
        int total = jdbcTemplate.getJdbcOperations().queryForObject("SELECT COUNT(id) FROM students", Integer.class);
        while (offset < total) {
            String sql = new StringBuilder("SELECT ss.id, ss.username, ss.name, ss.password, ss.class_id, ss.email, ss.tel, ss.sexual, cmf.id as major_id, cs.start_year FROM students ss LEFT JOIN classes cs ON cs.id=ss.class_id LEFT JOIN class_major_fields cmf on cmf.name=ss.major ORDER BY ss.id ASC LIMIT ")
                .append(offset).append(", ").append(count).toString();
            jdbcTemplate.query(sql, new RowCallbackHandler() {
                @Override
                public void processRow(ResultSet rs) throws SQLException {
                    String s = "REPLACE INTO users SET id=?, username=?, name=?, password=?, class_id=?, major_id=?, grade=?, email=?, phone=?, gender=?, qq='', company='', position='', signature=''";
                    jdbcTemplate.getJdbcOperations().update(s, new Object[] {
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

    @Override
    protected void onFind(User user) {
        isCityPrincipal(user);
    }
    
    @Override
    public UserSummary findUserSummary(int id) {
        String sql = "SELECT * FROM users WHERE id=?";
        List<UserSummary> list = jdbcTemplate.getJdbcOperations().query(sql, new Integer[] { id }, new RowMapper<UserSummary>() {
            @Override
            public UserSummary mapRow(ResultSet rs, int rowNum) throws SQLException {
                UserSummary summary = new UserSummary();
                summary.setId(rs.getInt("id"));
                summary.setName(rs.getString("name"));
                parsePrivateProfile(summary, rs);
                return summary;
            }
        });
        
        if (list.isEmpty()) {
            return null;
        }
        
        return list.get(0);
    }
    
    public List<User> findAlumni(UserSearchCondition condition, int pageSize, int page) {
        SelectSQLBuilder select = select("*");
        
        parseSearchCondition(condition, select);
        
        if (pageSize > 0) {
            select.paging(page, pageSize);
        }
        
        select.orderBy("username ASC");
        
        return queryAll(select.toSQL(), select.getParams(), getAlumniRowMapper());
    }
    
    public User findAlumnus(int id) {
        return find(id, getAlumniRowMapper());
    }
    
    private RowMapper<User> getAlumniRowMapper() {
        return new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setName(rs.getString("name"));
                user.setSignature(rs.getString("signature"));
                user.setGender(rs.getInt("gender"));
                user.setClassId(rs.getInt("class_id"));
                user.setMajorId(rs.getInt("major_id"));
                user.setCityId(rs.getInt("city_id"));
                parsePrivateProfile(user, rs);
                return user;
            }
        };
    }
    
    @Override
    public PaginationList<User> findAll(UserSearchCondition condition, int pageSize, int page) {
        SelectSQLBuilder select = select("*");
        
        parseSearchCondition(condition, select);
        
        if (pageSize > 0) {
            select.paging(page, pageSize);
        }
        
        select.orderBy("username ASC");
        
        return new PaginationList<User>(page, pageSize, this, select);
    }
    
    @Override
    public List<Klass> findAllClasses() {
        return queryAll("SELECT id, name FROM classes ORDER BY name ASC", ParameterizedBeanPropertyRowMapper.newInstance(Klass.class));
    }

    @Override
    public List<Major> findAllMajors() {
        return queryAll("SELECT id, name FROM class_major_fields ORDER BY name ASC", ParameterizedBeanPropertyRowMapper.newInstance(Major.class));
    }
    
    private void parseSearchCondition(UserSearchCondition condition, SelectSQLBuilder select) {
        if (condition.getGrade() > 0) {
            select.where("grade=:grade").addParam("grade", condition.getGrade());
        }
        
        if (condition.getClassId() > 0) {
            select.where("class_id=:classId").addParam("classId", condition.getClassId());
        }
        
        if (condition.getMajorId() > 0) {
            select.where("major_id=:majorId").addParam("majorId", condition.getMajorId());
        }
        
        if (condition.getCityId() > 0) {
            select.where("city_id=:cityId").addParam("cityId", condition.getCityId());
        }
        
        if (condition.getGeneder() > 0) {
            select.where("geneder=:geneder").addParam("geneder", condition.getGeneder());
        }
        
        if (null != condition.getName()) {
            String name = condition.getName().trim();
            if (!name.isEmpty()) {
                select.like("name", name);
            }
        }
        
        if (null != condition.getCompany()) {
            String company = condition.getCompany().trim();
            if (!company.isEmpty()) {
                select.like("company", company);
            }
        }
    }
    
    private void isCityPrincipal(User user) {
        if (user.getCityId() > 0) {
            City city = cityDao.find(user.getCityId());
            if (null != city && city.getUserId() == user.getId()) {
                user.setCityPrincipal(true);
            }
        }
    }
    
    private void parsePrivateProfile(PrivateProfile profile, ResultSet rs) throws SQLException {
        profile.setEmail(rs.getBoolean("private_email") ? "" : rs.getString("email"));
        profile.setPhone(rs.getBoolean("private_phone") ? "" : rs.getString("phone"));
        profile.setQq(rs.getBoolean("private_qq") ? "" : rs.getString("qq"));
        profile.setCompany(rs.getBoolean("private_company") ? "" : rs.getString("company"));
        profile.setPosition(rs.getBoolean("private_position") ? "" : rs.getString("position"));
    }

    @Override
    public boolean checkUsername(String username, int id) {
        SelectSQLBuilder select = select().where("username=:username").addParam("username", username);
        if (id > 0) {
            select.where("id<>:id").addParam("id", id);
        }
        
        return count(select) > 0;
    }

    @Override
    public boolean checkUsername(String username) {
        return checkUsername(username, 0);
    }

    @Override
    public Klass findClass(int id) {
        return query("SELECT id, name FROM classes WHERE id=?", ParameterizedBeanPropertyRowMapper.newInstance(Klass.class), id);
    }

    @Override
    public Major findMajor(int id) {
        return query("SELECT id, name FROM class_major_fields WHERE id=?", ParameterizedBeanPropertyRowMapper.newInstance(Major.class), id);
    }
}