package cn.edu.zju.isst.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
import cn.edu.zju.isst.form.UserLoginForm;
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
    @RequestMapping(value = "/logout.html", method = RequestMethod.GET)
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

    public static void main(String[] args) {
        long timestamp = System.currentTimeMillis() / 1000;
        System.out.println(timestamp);
        System.out.println(UserIdentity.encryptToken(timestamp, "21351075", "111111"));
        System.out.println(UserIdentity.encryptToken(timestamp, "679", "$1$AI2oKqG9$Kv8CCo.UzSXQsBPRbKfVq/"));
    }
}