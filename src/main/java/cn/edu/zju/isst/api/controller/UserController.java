package cn.edu.zju.isst.api.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.edu.zju.isst.common.ApiResponse;
import cn.edu.zju.isst.entity.User;
import cn.edu.zju.isst.entity.UserSearchCondition;
import cn.edu.zju.isst.form.ApiUserLoginForm;
import cn.edu.zju.isst.form.ApiUserLoginUpdateForm;
import cn.edu.zju.isst.identity.UserIdentity;
import cn.edu.zju.isst.service.UserService;

@Controller("apiUserController")
public class UserController {
    @Autowired
    private UserService userService;
    
    @RequestMapping(value = "/login")
    public @ResponseBody ApiResponse login(@Valid ApiUserLoginForm form, BindingResult result, HttpServletRequest request, HttpServletResponse response) {
        if (Math.abs(System.currentTimeMillis()/1000 - form.getTimestamp()) > 3600) {
            return new ApiResponse(12, "认证失效");
        }
        
        if (!UserIdentity.encryptToken(form.getTimestamp(), form.getUsername(), form.getPassword()).equals(form.getToken())) {
            return new ApiResponse(13, "认证失败");
        }
        
        User user = null;
        if (!result.hasErrors() && (null != (user = userService.login(request, response, form, result)))) {
            userService.updateLoginLocation(user, form.getLongitude(), form.getLatitude());
            return new ApiResponse(user);
        } else {
            return new ApiResponse(result);
        }
    }
    
    @RequestMapping(value = "/login/update")
    public @ResponseBody ApiResponse updateLogin(ApiUserLoginUpdateForm form, HttpServletRequest request, HttpServletResponse response) {
        if (Math.abs(System.currentTimeMillis()/1000 - form.getTimestamp()) > 3600) {
            return new ApiResponse(12, "认证失效");
        }
        
        User user = userService.find(form.getUserId());
        if (null == user) {
            return new ApiResponse(10, "用户不存在");
        }
        
        if (!UserIdentity.encryptToken(form.getTimestamp(), form.getUserId(), user.getPassword()).equals(form.getToken())) {
            return new ApiResponse(13, "认证失败");
        }
        
        UserIdentity.login(request, response, user, false);
        userService.updateLoginLocation(user, form.getLongitude(), form.getLatitude());
        
        return new ApiResponse(user);
    }
    
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public @ResponseBody ApiResponse logout(HttpServletRequest request, HttpServletResponse response) {
        User user = userService.logout(request, response);
        return new ApiResponse(user);
    }
    
    @RequestMapping("/classes")
    public @ResponseBody ApiResponse findAllClasses() {
        return new ApiResponse(userService.findAllClasses());
    }
    
    @RequestMapping("/majors")
    public @ResponseBody ApiResponse findAllMajors() {
        return new ApiResponse(userService.findAllMajors());
    }
    
    @RequestMapping("/alumni")
    public @ResponseBody ApiResponse list(
            UserSearchCondition condition,
            @RequestParam(value = "page", required = false, defaultValue = "1") int page,
            @RequestParam(value = "pageSize", required = false, defaultValue = "20") int pageSize) {
        return new ApiResponse(userService.findAlumni(condition, pageSize, page));
    }
    
    @RequestMapping("/alumni/{id}")
    public @ResponseBody ApiResponse find(@PathVariable("id") int id) {
        return new ApiResponse(userService.findAlumnus(id));
    }
    
    public static void main(String[] args) {
        long timestamp = System.currentTimeMillis() / 1000;
        System.out.println(timestamp);
        System.out.println(UserIdentity.encryptToken(timestamp, "21351075", "111111"));
        System.out.println(UserIdentity.encryptToken(timestamp, "679", "$1$AI2oKqG9$Kv8CCo.UzSXQsBPRbKfVq/"));
    }
}