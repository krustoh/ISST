package cn.edu.zju.isst.form;

import cn.edu.zju.isst.entity.Administrator;
import org.hibernate.validator.constraints.NotBlank;

public class AdministratorForm {
    private int id;

    @NotBlank(message = "用户名不能为空")
    private String username;

    @NotBlank(message = "密码不能为空")
    private String password;

    private int roles;

    public AdministratorForm() {

    }

    public AdministratorForm(Administrator administrator) {
        id = administrator.getId();
        username = administrator.getUsername();
        roles = administrator.getRoles();
    }

    public Administrator build() {
        Administrator administrator = new Administrator();
        return bind(administrator);
    }

    public Administrator bind(Administrator administrator) {
        administrator.setId(id);
        administrator.setUsername(username);
        if (null != password && password.length() > 0) {
            administrator.setPassword(Administrator.encryptPassword(password));
        } else {
            administrator.setPassword(null);
        }

        administrator.setRoles(roles);

        return administrator;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRoles() {
        return roles;
    }

    public void setRoles(int roles) {
        this.roles = roles;
    }
}