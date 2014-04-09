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

import cn.edu.zju.isst.common.FlashMessage;
import cn.edu.zju.isst.entity.UserSearchCondition;
import cn.edu.zju.isst.form.UserLoginForm;
import cn.edu.zju.isst.identity.RequireUser;
import cn.edu.zju.isst.identity.UserIdentity;
import cn.edu.zju.isst.service.UserService;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login.html", method = RequestMethod.GET)
    public String login(Model model) {
        model.addAttribute("userLoginForm", new UserLoginForm());
        return "login";
    }

    @RequestMapping(value = "/login.html", method = RequestMethod.POST)
    public String login(@Valid UserLoginForm form, BindingResult result, Model model, HttpServletRequest request,
            HttpServletResponse response) {
        if (!result.hasErrors() && null != userService.login(request, response, form, result)) {
            return "redirect:archives/categories/campus.html";
        } else {
            model.addAttribute("userLoginForm", form);
            model.addAttribute("flash_message", FlashMessage.error(result));
            return "login";
        }
    }

    @RequireUser
    @RequestMapping(value = "/logout.html", method = RequestMethod.GET)
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        userService.logout(request, response);
        return "redirect:login.html";
    }

    @RequestMapping("/classes.html")
    public String findAllClasses(Model model) {
        model.addAttribute(userService.findAllClasses());
        return "classes";
    }

    @RequestMapping("/majors.html")
    public String findAllMajors(Model model) {
        model.addAttribute(userService.findAllMajors());
        return "majors";
    }

    @RequestMapping("/alumni.html")
    public String list(Model model, UserSearchCondition condition,
            @RequestParam(value = "page", required = false, defaultValue = "1") int page,
            @RequestParam(value = "pageSize", required = false, defaultValue = "20") int pageSize) {
        model.addAttribute(userService.findAlumni(condition, pageSize, page));
        return "alumni";
    }

    @RequestMapping("/alumni/{id}.html")
    public String find(
            Model model,
            @PathVariable("id") int id) {
        model.addAttribute(userService.findAlumnus(id));
        return "alumni/{id}";
    }

    public static void main(String[] args) {
        long timestamp = System.currentTimeMillis() / 1000;
        System.out.println(timestamp);
        System.out.println(UserIdentity.encryptToken(timestamp, "21351075", "111111"));
        System.out.println(UserIdentity.encryptToken(timestamp, "679", "$1$AI2oKqG9$Kv8CCo.UzSXQsBPRbKfVq/"));
    }
}