package cn.edu.zju.isst.admin.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.edu.zju.isst.common.WebUtils;
import cn.edu.zju.isst.entity.Administrator;
import cn.edu.zju.isst.form.AdministratorLoginForm;
import cn.edu.zju.isst.form.AdministratorPasswordForm;
import cn.edu.zju.isst.identity.RequireAdministrator;
import cn.edu.zju.isst.service.AdministratorService;

@Controller
public class AdministratorController {
    @Autowired
    private AdministratorService administratorService;

    @RequestMapping(value = "/login.html", method = RequestMethod.GET)
    public String login(Model model, HttpServletRequest request) {
        Administrator administrator = (Administrator) request.getSession().getAttribute("administrator");
        if (null != administrator && administrator.getId() > 0) {
            return WebUtils.redirectAdminUrl("index.html");
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
            return WebUtils.redirectAdminUrl("index.html");
        } else {
            model.addAttribute("administratorLoginForm", form);
            WebUtils.addErrorFlashMessage(result);
            return "login";
        }
    }
    
    @RequestMapping(value = "/password.html", method = RequestMethod.GET)
    public String changePassword(Model model) {
        model.addAttribute("administratorPasswordForm", new AdministratorPasswordForm());
        return "administrators/password";
    }

    @RequireAdministrator(Administrator.ADMIN_ALUMNI)
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
                result.addError(new ObjectError("confirmPassword", "确认密码与新密码不一致"));
            }
        }
        
        Administrator administrator = (Administrator) session.getAttribute("administrator");
        
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
        return "redirect:login.html";
    }
}