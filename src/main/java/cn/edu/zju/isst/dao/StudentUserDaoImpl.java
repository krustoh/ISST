package cn.edu.zju.isst.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import cn.edu.zju.isst.common.PaginationList;
import cn.edu.zju.isst.common.SelectSQLBuilder;
import cn.edu.zju.isst.entity.PrivateProfile;
import cn.edu.zju.isst.entity.Student;
import cn.edu.zju.isst.entity.StudentUser;
import cn.edu.zju.isst.entity.User;
import cn.edu.zju.isst.entity.UserSearchCondition;
import cn.edu.zju.isst.entity.UserSummary;

@Repository
public class StudentUserDaoImpl implements StudentUserDao {
    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    private UserDao userDao;
    
    @Autowired
    private StudentDao studentDao;
    
    @Override
    public StudentUser find(int id) {
        SelectSQLBuilder select = select().where("s.id=?");
        List<StudentUser> list = jdbcTemplate.getJdbcOperations().query(select.toSQL(), getStudentUserRowMapper(), id);
        
        if (list.isEmpty()) {
            return null;
        }
        
        return list.get(0);
    }
    
    @Override
    public StudentUser find(String username) {
        SelectSQLBuilder select = select().where("s.username=?");
        List<StudentUser> list = jdbcTemplate.getJdbcOperations().query(select.toSQL(), getStudentUserRowMapper(), username);
        
        if (list.isEmpty()) {
            return null;
        }
        
        return list.get(0);
    }
    
    public List<StudentUser> findAll(Set<Integer> idset) {
        SelectSQLBuilder select = select().where("s.id IN (:idset)");
        
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("idset", idset);
        
        return jdbcTemplate.query(select.toSQL(), parameters, getStudentUserRowMapper());
    }
    
    public StudentUser findAlumnus(int id) {
        SelectSQLBuilder select = select().where("s.id=?");
        List<StudentUser> list = jdbcTemplate.getJdbcOperations().query(select.toSQL(), getAlumniRowMapper(), id);
        
        if (list.isEmpty()) {
            return null;
        }
        
        return list.get(0);
    }
    
    @Override
    public UserSummary findUserSummary(int id) {
        SelectSQLBuilder select = select().where("s.id=?");
        List<UserSummary> list = jdbcTemplate.getJdbcOperations().query(select.toSQL(), new Integer[] { id }, new RowMapper<UserSummary>() {
            @Override
            public UserSummary mapRow(ResultSet rs, int rowNum) throws SQLException {
                UserSummary summary = new UserSummary();
                summary.setId(rs.getInt("id"));
                summary.setUsername(rs.getString("username"));
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
    
    public PaginationList<StudentUser> findAll(UserSearchCondition condition, int pageSize, int page) {
        SelectSQLBuilder select = select();
        
        parseSearchCondition(condition, select);
        
        if (pageSize > 0) {
            select.paging(page, pageSize);
        }
        
        select.orderBy("s.username ASC");
        
        return new PaginationList<StudentUser>(page, pageSize, count(select), jdbcTemplate.query(select.toSQL(), select.getParams(), getStudentUserRowMapper()));
    }

    @Override
    public PaginationList<StudentUser> findAlumni(UserSearchCondition condition, int pageSize, int page) {
        SelectSQLBuilder select = select();
        
        parseSearchCondition(condition, select);
        
        if (pageSize > 0) {
            select.paging(page, pageSize);
        }
        
        select.orderBy("s.username ASC");
        
        return new PaginationList<StudentUser>(page, pageSize, this, select);
    }

    @Override
    public List<StudentUser> queryAll(SelectSQLBuilder select) {
        return jdbcTemplate.query(select.toSQL(), select.getParams(), getAlumniRowMapper());
    }

    @Override
    public int count(SelectSQLBuilder select) {
        return jdbcTemplate.queryForObject(select.toCountSQL(), select.getParams(), Integer.class);
    }
    
    private void parseSearchCondition(UserSearchCondition condition, SelectSQLBuilder select) {
        if (null != condition.getGrade() && condition.getGrade().length() > 0 && !condition.getGrade().equals("0")) {
            select.where("c.start_year=:grade").addParam("grade", condition.getGrade());
        }
        
        if (condition.getClassId() > 0) {
            select.where("s.class_id=:classId").addParam("classId", condition.getClassId());
        } else if (condition.getClassName() != null && condition.getClassName().length() > 0) {
            select.where("c.name=:className").addParam("className", condition.getClassName());
        }
        
        if (condition.getCityId() > 0) {
            select.where("u.city_id=:cityId").addParam("cityId", condition.getCityId());
        }
        
        if (condition.getGender() > 0) {
            select.where("s.sexual=:sexual").addParam("sexual", condition.getGender() == 1 ? "男" : "女");
        }
        
        if (null != condition.getMajor()) {
        	String major =condition.getMajor().trim();
        	if(!major.isEmpty()) {
        		select.where("s.major=:major").addParam("major", condition.getMajor());
        	}
        }
        
        if (null != condition.getName()) {
            String name = condition.getName().trim();
            if (!name.isEmpty()) {
                select.like("s.name", name);
            }
        }
        
        if (null != condition.getCompany()) {
            String company = condition.getCompany().trim();
            if (!company.isEmpty()) {
                select.like("u.company", company);
            }
        }
        
        if (null != condition.getPosition()) {
            String position = condition.getPosition().trim();
            if (!position.isEmpty()) {
                select.like("u.position", position);
            }
        }
    }
    
    private RowMapper<StudentUser> getStudentUserRowMapper() {
        return new RowMapper<StudentUser>() {
            @Override
            public StudentUser mapRow(ResultSet rs, int rowNum) throws SQLException {
                StudentUser su = new StudentUser();
                commonRowMap(su, rs);
                
                su.setEmail(rs.getString("email"));
                su.setPhone(rs.getString("tel"));
                su.setQq(rs.getString("qq"));
                su.setCompany(rs.getString("company"));
                su.setPosition(rs.getString("position"));
                
                return su;
            }
        };
    }
    
    private SelectSQLBuilder select() {
        return SelectSQLBuilder.selectTable("students s", "s.id, s.class_id, s.username, s.name, s.password, s.sexual, s.email, s.tel, s.major")
        .leftJoin("users u", "u.id=s.id", "u.qq, u.signature, u.company, u.position, u.city_id, u.city_principal, u.private_phone, u.private_email, u.private_qq, u.private_company, u.private_position")
        .leftJoin("classes c", "c.id=s.class_id", "c.name class_name, c.start_year grade")
        .leftJoin("cities ci", "ci.id=u.city_id", "ci.name city_name");
    }
    
    private RowMapper<StudentUser> getAlumniRowMapper() {
        return new RowMapper<StudentUser>() {
            @Override
            public StudentUser mapRow(ResultSet rs, int rowNum) throws SQLException {
                StudentUser su = new StudentUser();
                commonRowMap(su, rs);
                parsePrivateProfile(su, rs);
                
                return su;
            }
        };
    }
    
    private void commonRowMap(StudentUser su, ResultSet rs) throws SQLException {
        su.setId(rs.getInt("id"));
        su.setUsername(rs.getString("username"));
        su.setName(rs.getString("name"));
        su.setPassword(rs.getString("password"));
        su.setSignature(rs.getString("signature"));
        su.setGender(rs.getString("sexual").equals("男") ? 1 : 2);
        su.setGrade(rs.getInt("grade"));
        su.setClassId(rs.getInt("class_id"));
        su.setClassName(rs.getString("class_name"));
        su.setMajor(rs.getString("major"));
        su.setCityId(rs.getInt("city_id"));
        su.setCityName(rs.getString("city_name"));
        su.setCityPrincipal(rs.getBoolean("city_principal"));
        su.setPrivateCompany(rs.getBoolean("private_company"));
        su.setPrivateEmail(rs.getBoolean("private_email"));
        su.setPrivatePhone(rs.getBoolean("private_phone"));
        su.setPrivatePosition(rs.getBoolean("private_position"));
        su.setPrivateQQ(rs.getBoolean("private_qq"));
    }
    
    private void parsePrivateProfile(PrivateProfile profile, ResultSet rs) throws SQLException {
        profile.setEmail(rs.getBoolean("private_email") ? "" : rs.getString("email"));
        profile.setPhone(rs.getBoolean("private_phone") ? "" : rs.getString("tel"));
        profile.setQq(rs.getBoolean("private_qq") ? "" : rs.getString("qq"));
        profile.setCompany(rs.getBoolean("private_company") ? "" : rs.getString("company"));
        profile.setPosition(rs.getBoolean("private_position") ? "" : rs.getString("position"));
    }

    @Override
    public void save(StudentUser studentUser) {
        if (studentUser.getId() > 0) {
            Student student = studentDao.find(studentUser.getId());
            if (null == student) {
                student = studentUser.toStudent();
                studentDao.insert(student);
            } else {
                studentUser.bind(student);
                studentDao.update(student);
            }
            
            User user = userDao.find(studentUser.getId());
            if (null == user) {
                user = studentUser.toUser();
                userDao.insert(user);
            } else {
                studentUser.bind(user);
                userDao.update(user);
            }
        } else {
            Student student = studentUser.toStudent();
            studentDao.insert(student);
            
            User user = studentUser.toUser();
            user.setId(student.getId());
            userDao.insert(user);
        }
    }

    @Override
    public PaginationList<StudentUser> findActivityParticipants(int activityId, int pageSize, int page) {
       SelectSQLBuilder select = select().rigntJoin("activity_participants ap", "ap.user_id=s.id")
               .where("ap.activity_id=:activity_id").addParam("activity_id", activityId)
               .orderBy("ap.created_at DESC");
       
       if (pageSize > 0) {
           select.paging(page, pageSize);
       }
       
       List<StudentUser> items = jdbcTemplate.query(select.toSQL(), select.getParams(), getAlumniRowMapper());
       
        return new PaginationList<StudentUser>(page, pageSize, items, this, select);
    }
}