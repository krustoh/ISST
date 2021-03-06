package cn.edu.zju.isst.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import cn.edu.zju.isst.common.WebUtils;
import cn.edu.zju.isst.entity.StudentUser;
import cn.edu.zju.isst.entity.UserSearchCondition;
import cn.edu.zju.isst.form.AlumniForm;
import cn.edu.zju.isst.form.UserLoginForm;
import cn.edu.zju.isst.form.UserPasswordForm;
import cn.edu.zju.isst.identity.RequireUser;
import cn.edu.zju.isst.identity.UserIdentity;
import cn.edu.zju.isst.service.CityService;
import cn.edu.zju.isst.service.UserService;

@Controller("webUserController")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private CityService cityService;

    @RequestMapping(value = "/login.html", method = RequestMethod.GET)
    public String login(Model model, HttpServletRequest request) {
    	StudentUser user = (StudentUser) request.getSession().getAttribute("user");
    	if (null != user && user.getId() > 0) {
            return WebUtils.redirectUrl("/index.html");
    	} else {
    		model.addAttribute("userLoginForm", new UserLoginForm());
            return "login";
    	}
    }

    @RequestMapping(value = "/login.html", method = RequestMethod.POST)
    public String login(
    		@Valid UserLoginForm form, 
    		BindingResult result, 
    		Model model, 
    		HttpServletRequest request,
            HttpServletResponse response) {
        if (!result.hasErrors() && null != userService.login(request, response, form, result)) {
        	return WebUtils.redirectUrl("/index.html");
        } else {
            model.addAttribute("userLoginForm", form);
            WebUtils.addErrorFlashMessage(result);
            return "login";
        }
    }

    @RequireUser
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        userService.logout(request, response);
    	return WebUtils.redirectUrl("/login.html");
    }

    @RequireUser
    @RequestMapping("/alumni.html")
    public String list(
    		Model model, 
    		UserSearchCondition condition,
            @RequestParam(value = "page", required = false, defaultValue = "1") int page) {
        model.addAttribute("classes", userService.findAllClasses());
        model.addAttribute("majors", userService.findAllMajors());
        model.addAttribute("cities", cityService.findAllForSelect());
        model.addAttribute("condition", condition);
        model.addAttribute("users", userService.findAll(condition, 10, page));
        
        return "users/list";
    }

    @RequireUser
    @RequestMapping("/alumni/{id}.html")
    public String view(
            Model model,
            @PathVariable("id") int id) {
        model.addAttribute("alumni",userService.findAlumnus(id));
        return "users/view";
    }

    @RequireUser
    @RequestMapping(value = "/users/edit.html", method = RequestMethod.GET)
    public String edit(Model model, HttpSession session) {
        StudentUser user = (StudentUser) session.getAttribute("user");
        model.addAttribute("alumniForm", new AlumniForm(user));
        model.addAttribute("classes", userService.findAllClasses());
        model.addAttribute("majors", userService.findAllMajors());
        model.addAttribute("cities", cityService.findAllForSelect());
        return "users/form";
    }

    @RequireUser
    @RequestMapping(value = "/users/edit.html", method = RequestMethod.POST)
    public String saveEdit(@Valid AlumniForm form, BindingResult result, Model model, HttpSession session) {
        StudentUser user = (StudentUser) session.getAttribute("user");
        form.setId(user.getId());
        if (result.hasErrors()) {
            model.addAttribute("classes", userService.findAllClasses());
            model.addAttribute("majors", userService.findAllMajors());
            model.addAttribute("cities", cityService.findAllForSelect());
            model.addAttribute("alumniForm", form);
            return "users/form";
        }
        
        userService.save(form.bind(user));
        WebUtils.addSuccessFlashMessage("保存成功");
        
        return WebUtils.redirectUrl("/alumni/" + user.getId() + ".html");
    }
    
    @RequireUser
    @RequestMapping(value = "/users/password.html", method = RequestMethod.GET)
    public String changePassword(Model model ) {
        model.addAttribute("userPasswordForm", new UserPasswordForm());
        return "users/password";
    }
    
    @RequireUser
    @RequestMapping(value = "/users/password.html", method = RequestMethod.POST)
    public String saveChangePassword(
            HttpSession session, Model model, 
            HttpServletRequest request, 
            HttpServletResponse response,
            @Valid UserPasswordForm form, 
            BindingResult result) {
        boolean hasErrors = false;
        if (result.hasErrors()) {
            hasErrors = true;
        } else {
            if (!form.getNewPassword().equals(form.getConfirmPassword())) {
                hasErrors = true;
                result.rejectValue("confirmPassword", "confirmPassword.not_conformity", "确认密码与新密码不一致");
            }
        }
        
        StudentUser user = (StudentUser) session.getAttribute("user");
        if (!StudentUser.validatePassword(user.getPassword(), form.getOldPassword())) {
            hasErrors = true;
            result.rejectValue("oldPassword", "oldPassword.invalid", "原密码错误");
        }
        
        if (hasErrors) {
            form.setConfirmPassword(null);
            form.setNewPassword(null);
            form.setOldPassword(null);
            model.addAttribute("userPasswordForm", form);
            return "users/password";
        } else {
            userService.changePassword(user.getId(), form.getNewPassword());
            WebUtils.addSuccessFlashMessage("密码修改成功，请重新登录");
            return logout(request, response);
        }
    }
    
    public static void main(String[] args) {
        long timestamp = System.currentTimeMillis() / 1000;
        System.out.println(timestamp);
        System.out.println(UserIdentity.encryptToken(timestamp, "21351075", "025359"));
        System.out.println(UserIdentity.encryptToken(timestamp, "679", "$1$AI2oKqG9$Kv8CCo.UzSXQsBPRbKfVq/"));
    }
}