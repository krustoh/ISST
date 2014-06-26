package cn.edu.zju.isst.admin.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import cn.edu.zju.isst.form.AdministratorForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.edu.zju.isst.common.WebUtils;
import cn.edu.zju.isst.entity.Administrator;
import cn.edu.zju.isst.form.AdministratorLoginForm;
import cn.edu.zju.isst.form.AdministratorPasswordForm;
import cn.edu.zju.isst.identity.RequireAdministrator;
import cn.edu.zju.isst.service.AdministratorService;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashSet;
import java.util.Set;

@Controller
public class AdministratorController {
    @Autowired
    private AdministratorService administratorService;

    @RequestMapping(value = "/login.html", method = RequestMethod.GET)
    public String login(Model model, HttpServletRequest request) {
        Administrator administrator = (Administrator) request.getSession().getAttribute("administrator");
        if (null != administrator && administrator.getId() > 0) {
            return WebUtils.redirectUrl("/index.html");
        } else {
            model.addAttribute("administratorLoginForm", new AdministratorLoginForm());
            return "login";
        }
    }

    @RequestMapping(value = "/login.html", method = RequestMethod.POST)
    public String loginSubmit(
            @Valid AdministratorLoginForm form, 
            BindingResult result, 
            Model model, 
            HttpServletRequest request,
            HttpSession session,
            HttpServletResponse response) {
        if (!result.hasErrors() && administratorService.login(request, response, form, result)) {
            return WebUtils.redirectUrl("/index.html");
        } else {
            model.addAttribute("administratorLoginForm", form);
            WebUtils.addErrorFlashMessage(result);
            return "login";
        }
    }

    @RequireAdministrator
    @RequestMapping(value = "/password.html", method = RequestMethod.GET)
    public String changePassword(Model model) {
        model.addAttribute("administratorPasswordForm", new AdministratorPasswordForm());
        return "administrators/password";
    }

    @RequireAdministrator
    @RequestMapping(value = "/password.html", method = RequestMethod.POST)
    public String saveChangePassword(
            @Valid AdministratorPasswordForm form, 
            BindingResult result, 
            Model model, 
            HttpSession session,
            HttpServletRequest request, 
            HttpServletResponse response) {
        boolean hasErrors = false;
        if (result.hasErrors()) {
            hasErrors = true;
        } else {
            if (!form.getNewPassword().equals(form.getConfirmPassword())) {
                hasErrors = true;
                result.rejectValue("confirmPassword", "confirmPassword.not_conformity", "确认密码与新密码不一致");
            }
        }
        
        Administrator administrator = (Administrator) session.getAttribute("administrator");
        if (!Administrator.validatePassword(administrator.getPassword(), form.getOldPassword())) {
            hasErrors = true;
            result.rejectValue("oldPassword", "oldPassword.invalid", "原密码错误");
        }
        
        if (hasErrors) {
            form.setConfirmPassword(null);
            form.setNewPassword(null);
            form.setOldPassword(null);
            model.addAttribute("administratorPasswordForm", form);
            return "administrators/password";
        } else {
            administratorService.changePassword(administrator.getId(), form.getNewPassword());
            WebUtils.addSuccessFlashMessage("密码修改成功，请重新登录");
            return logout(request, response);
        }
    }

    @RequireAdministrator
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        administratorService.logout(request, response);
    	return WebUtils.redirectUrl("/login.html");
    }

    @RequireAdministrator(Administrator.SUPER)
    @RequestMapping(value = "/administrators.html", method = RequestMethod.GET)
    public String list(Model model, @RequestParam(value = "page", required = false, defaultValue = "1") int page) {
        model.addAttribute("administrators", administratorService.findAll(20, page));

        return "administrators/list";
    }

    @RequireAdministrator(Administrator.SUPER)
    @RequestMapping(value = "/administrators/add.html", method = RequestMethod.GET)
    public String add(Model model) {
        model.addAttribute("administratorForm", new AdministratorForm());

        return "administrators/form";
    }

    @RequireAdministrator(Administrator.SUPER)
    @RequestMapping(value = "/administrators/add.html", method = RequestMethod.POST)
    public String saveAdd(@Valid AdministratorForm form, BindingResult result, Model model) {
        if (!result.hasErrors()) {
            if (null == form.getPassword() || form.getPassword().length() == 0) {
                result.rejectValue("password", "password.blank", "密码不能为空");
            }
        }

        if (result.hasErrors()) {
            model.addAttribute("administratorForm", form);
            return "administrators/form";
        }
        
        return save(form);
    }

    @RequireAdministrator(Administrator.SUPER)
    @RequestMapping(value = "/administrators/{id}.html", method = RequestMethod.GET)
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("administratorForm", new AdministratorForm(administratorService.find(id)));

        return "administrators/form";
    }

    @RequireAdministrator(Administrator.SUPER)
    @RequestMapping(value = "/administrators/{id}.html", method = RequestMethod.POST)
    public String saveEdit(@Valid AdministratorForm form, BindingResult result, Model model, @PathVariable("id") int id) {
        form.setId(id);
        if (result.hasErrors()) {
            model.addAttribute("administratorForm", form);
            return "administrators/form";
        }

        return save(form);
    }

    private String save(AdministratorForm form) {
        administratorService.save(form);
        WebUtils.addSuccessFlashMessage(String.format("成功保存：<i>%s</i>", form.getUsername()));

        return WebUtils.redirectUrl("/administrators.html");
    }

    @RequireAdministrator(Administrator.SUPER)
    @RequestMapping(value = "/administrators/delete")
     public String delete(
            @RequestParam("id[]") String[] ids,
            @RequestParam(value = "confirm", required = false, defaultValue = "0") int confirm,
            Model model) {
        Set<Integer> idset = new HashSet<Integer>();
        for (String id : ids) {
            if (!"1".equals(id)) {
                idset.add(Integer.valueOf(id));
            }
        }

        if (confirm == 0) {
            model.addAttribute("navigationActiveKey", "administrators");
            model.addAttribute("cancelUrl", WebUtils.createUrl("/administrators.html"));
            return "confirm/delete";
        } else {
            if (idset.size() == 1) {
                Administrator administrator = administratorService.find(Integer.valueOf(ids[0]).intValue());
                if (null != administrator) {
                    administratorService.delete(administrator);
                    WebUtils.addSuccessFlashMessage(String.format("成功删除：<i>%s</i>", administrator.getUsername()));
                } else {
                    WebUtils.addErrorFlashMessage("管理员不存在或已被删除");
                }
            } else {
                int count = administratorService.delete(idset);
                WebUtils.addSuccessFlashMessage(String.format("成功删除 <i>%d</i> 个管理员", count));
            }

            return WebUtils.redirectUrl("/administrators.html");
        }
    }
}