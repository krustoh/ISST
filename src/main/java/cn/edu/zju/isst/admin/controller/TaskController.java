package cn.edu.zju.isst.admin.controller;

import java.util.HashSet;
import java.util.Set;

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
import cn.edu.zju.isst.entity.Task;
import cn.edu.zju.isst.entity.TaskSearchCondition;
import cn.edu.zju.isst.form.TaskForm;
import cn.edu.zju.isst.service.TaskService;
import cn.edu.zju.isst.service.TaskSurveyOptionService;
import cn.edu.zju.isst.service.TaskSurveyService;

@Controller("adminTaskController")
public class TaskController {
    @Autowired
    private TaskService taskService;
    
    @Autowired
    private TaskSurveyService taskSurveyService;
    
    @Autowired
    private TaskSurveyOptionService taskSurveyOptionService;
    
    @RequestMapping("/tasks.html")
    public String list(
            TaskSearchCondition condition,
            Model model, 
            @RequestParam(value = "page", required = false, defaultValue = "1") int page) {
        model.addAttribute("taskSearchCondition", condition);
        model.addAttribute("tasks", taskService.findList(condition, 20, page));
        
        return "tasks/list";
    }
    
    @RequestMapping(value = "/tasks/add.html", method = RequestMethod.GET)
    public String add(Model model) {
        model.addAttribute("taskForm", new TaskForm());
        
        return "tasks/form";
    }

    @RequestMapping(value = "/tasks/add.html", method = RequestMethod.POST)
    public String saveAdd(@Valid TaskForm form, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return add(model);
        }

        return save(form);
    }
    
    @RequestMapping(value = "/tasks/{id}.html", method = RequestMethod.GET)
    public String edit(@PathVariable("id") int id, Model model) {
        Task task = taskService.find(id);
        model.addAttribute("taskForm", new TaskForm(task));
        
        return "tasks/form";
    }
    
    @RequestMapping(value = "/tasks/{id}.html", method = RequestMethod.GET)
    public String saveEdit(@Valid TaskForm form, BindingResult result, @PathVariable("id") int id, Model model) {
        if (result.hasErrors()) {
            return edit(id, model);
        }
        
        return save(form);
    }
    
    private String save(TaskForm form) {
        taskService.save(form);
        WebUtils.addSuccessFlashMessage(String.format("成功保存：%s", form.getName()));
        
        return WebUtils.redirectUrl("/tasks.html");
    }
    
    @RequestMapping(value = "/tasks/delete")
    public String delete(
            @RequestParam("id[]") String[] ids,
            @RequestParam(value = "confirm", required = false, defaultValue = "0") int confirm,
            Model model) {
        Set<Integer> idset = new HashSet<Integer>();
        for (String id : ids) {
            idset.add(Integer.valueOf(id));
        }
        
        if (confirm == 0) {
            model.addAttribute("navigationActiveKey", "tasks");
            model.addAttribute("cancelUrl", WebUtils.createUrl("/tasks.html"));
            return "confirm/delete";
        } else {
            if (idset.size() == 1) {
                Task task = taskService.find(Integer.valueOf(ids[0]).intValue());
                if (null != task) {
                    taskService.delete(task);
                    WebUtils.addSuccessFlashMessage(String.format("成功删除：<i>%s</i>", task.getName()));
                } else {
                    WebUtils.addErrorFlashMessage("任务不存在或已被删除");
                }
            } else {
                int count = taskService.delete(idset);
                WebUtils.addSuccessFlashMessage(String.format("成功删除 <i>%d</i> 条任务", count));
            }
            
            return WebUtils.redirectUrl("/tasks.html");
        }
    }
    
    @RequestMapping(value = "/tasks/publish")
    public String publish(@RequestParam("id[]") String[] ids) {
        Set<Integer> idset = new HashSet<Integer>();
        for (String id : ids) {
            idset.add(Integer.valueOf(id));
        }
        
        int count = 0;
        if (!idset.isEmpty()) {
            count = taskService.publish(idset);
        }
        
        WebUtils.addSuccessFlashMessage(String.format("成功发布 <i>%d</i> 条任务", count));
        return WebUtils.redirectUrl("/tasks.html");
    }
    
    @RequestMapping(value = "/tasks/hide")
    public String hide(@RequestParam("id[]") String[] ids) {
        Set<Integer> idset = new HashSet<Integer>();
        for (String id : ids) {
            idset.add(Integer.valueOf(id));
        }
        
        int count = 0;
        if (!idset.isEmpty()) {
            count = taskService.hide(idset);
        }
        
        WebUtils.addSuccessFlashMessage(String.format("成功隐藏 <i>%d</i> 条任务", count));
        return WebUtils.redirectUrl("/tasks.html");
    }
    
    @RequestMapping("/tasks/{taskId}/surveys.html")
    public String surveyList(
            Model model,
            @PathVariable("taskId") int taskId, 
            @RequestParam(value = "page", required = false, defaultValue = "1") int page) {
        model.addAttribute("task", taskService.find(taskId));
        model.addAttribute("surveys", taskSurveyService.findAll(taskId, 20, page));
        model.addAttribute("options", taskSurveyOptionService.findAll(taskId));
        
        return "tasks/survey";
    }
}