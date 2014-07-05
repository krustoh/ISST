package cn.edu.zju.isst.web.controller;

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

import cn.edu.zju.isst.common.Result;
import cn.edu.zju.isst.common.WebUtils;
import cn.edu.zju.isst.entity.StudentUser;
import cn.edu.zju.isst.entity.Task;
import cn.edu.zju.isst.entity.TaskSurvey;
import cn.edu.zju.isst.form.TaskSurveyForm;
import cn.edu.zju.isst.identity.RequireUser;
import cn.edu.zju.isst.service.TaskService;
import cn.edu.zju.isst.service.TaskSurveyOptionService;
import cn.edu.zju.isst.service.TaskSurveyService;

@RequireUser
@Controller("webTaskController")
public class TaskController {
    @Autowired
    private TaskService taskService;

    @Autowired
    private TaskSurveyOptionService taskSurveyOptionService;

    @Autowired
    private TaskSurveyService taskSurveyService;

    @RequestMapping("/tasks.html")
    public String list(Model model, HttpSession session,
            @RequestParam(value = "page", required = false, defaultValue = "1") int page) {
        StudentUser user = (StudentUser) session.getAttribute("user");
        model.addAttribute("tasks", taskService.findListForUser(user.getId(), 20, page));

        return "tasks/list";
    }

    @RequestMapping(value = "/tasks/{id}/survey.html", method = RequestMethod.GET)
    public String view(@PathVariable("id") int id, Model model, HttpSession session) {
        StudentUser user = (StudentUser) session.getAttribute("user");
        model.addAttribute("task", taskService.find(id));
        model.addAttribute("options", taskSurveyOptionService.findAll(id));
        
        TaskSurvey taskSurvey = taskSurveyService.find(id, user.getId());
        TaskSurveyForm form = null;
        if (null != taskSurvey) {
            form = new TaskSurveyForm(taskSurvey);
        } else {
            form = new TaskSurveyForm();
        }
        model.addAttribute("taskSurveyForm", form);
        
        return "tasks/survey";
    }

    @RequestMapping(value = "/tasks/{taskId}/survey.html", method = RequestMethod.POST)
    public String saveSurvey(
            @Valid TaskSurveyForm form, 
            BindingResult result, 
            @PathVariable("taskId") int taskId, 
            Model model,
            HttpSession session) {
        StudentUser user = (StudentUser) session.getAttribute("user");
        
        form.setTaskId(taskId);
        form.setUserId(user.getId());

        boolean error = result.hasErrors();

        Task task = taskService.find(form.getTaskId());
        
        if (!error) {
            Result res = taskSurveyService.save(form);
            if (!res.valid()) {
                WebUtils.addErrorFlashMessage(res);
                error = true;
            }
        }
        
        
        if (error) {
            model.addAttribute("task", task);
            model.addAttribute("options", taskSurveyOptionService.findAll(taskId));
            model.addAttribute("taskSurveyForm", form);
            return "tasks/survey";
        }

        WebUtils.addSuccessFlashMessage(String.format("成功提交任务：<i>%s</i>", task.getName()));
        return WebUtils.redirectUrl("/tasks.html");
    }
}
