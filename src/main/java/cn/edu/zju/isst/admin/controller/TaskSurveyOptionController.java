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
import cn.edu.zju.isst.entity.TaskSurveyOption;
import cn.edu.zju.isst.form.TaskSurveyOptionForm;
import cn.edu.zju.isst.identity.RequireAdministrator;
import cn.edu.zju.isst.service.TaskService;
import cn.edu.zju.isst.service.TaskSurveyOptionService;

@RequireAdministrator
@Controller("adminTaskSurveyOptionController")
public class TaskSurveyOptionController {
    @Autowired
    private TaskService taskService;
    
    @Autowired
    private TaskSurveyOptionService taskSurveyOptionService;

    @RequestMapping("/tasks/{taskId}/surveys/options.html")
    public String surveyOptionsList(Model model, @PathVariable("taskId") int taskId) {
        model.addAttribute("task", taskService.find(taskId));
        model.addAttribute("options", taskSurveyOptionService.findAll(taskId));
        
        return "tasks/survey/options/list";
    }
    
    @RequestMapping(value = "/tasks/{taskId}/surveys/options/add.html", method = RequestMethod.GET)
    public String surveyOptionAdd(Model model,  @PathVariable("taskId") int taskId) {
        TaskSurveyOptionForm form = new TaskSurveyOptionForm();
        model.addAttribute("taskSurveyOptionForm", form);
        model.addAttribute("task", taskService.find(taskId));
        
        return "tasks/survey/options/form";
    }
    
    @RequestMapping(value = "/tasks/{taskId}/surveys/options/add.html", method = RequestMethod.POST)
    public String saveSurveyOptionAdd(
            Model model,  
            @PathVariable("taskId") int taskId,
            @Valid TaskSurveyOptionForm form,
            BindingResult result) {
        if (result.hasErrors()) {
            model.addAttribute("taskSurveyOptionForm", form);
            model.addAttribute("task", taskService.find(taskId));
            return "tasks/form";
        }
        
        return saveSurveyOption(form);
    }

    @RequestMapping(value = "/tasks/{taskId}/surveys/options/{id}.html", method = RequestMethod.GET)
    public String surveyOptionEdit(
            Model model,  
            @PathVariable("taskId") int taskId, 
            @PathVariable("id") int id) {
        TaskSurveyOptionForm form = new TaskSurveyOptionForm(taskSurveyOptionService.find(id));
        model.addAttribute("taskSurveyOptionForm", form);
        model.addAttribute("task", taskService.find(taskId));

        return "tasks/survey/options/form";
    }

    @RequestMapping(value = "/tasks/{taskId}/surveys/options/{id}.html", method = RequestMethod.POST)
    public String saveSurveyOptionEdit(
            Model model,  
            @PathVariable("taskId") int taskId, 
            @PathVariable("id") int id,
            @Valid TaskSurveyOptionForm form,
            BindingResult result) {
        form.setId(id);
        if (result.hasErrors()) {
            model.addAttribute("taskSurveyOptionForm", form);
            model.addAttribute("task", taskService.find(taskId));
        }
        
        return saveSurveyOption(form);
    }
    
    private String saveSurveyOption(TaskSurveyOptionForm form) {
        taskSurveyOptionService.save(form);
        WebUtils.addSuccessFlashMessage(String.format("成功保存：<i>%</i>", form.getLabel()));
        
        return WebUtils.redirectUrl("/tasks/"+form.getTaskId()+"/surveys/options.html");
    }
    
    @RequestMapping(value = "/tasks/{taskId}/surveys/options/delete")
    public String delete(
            @RequestParam("id[]") String[] ids,
            @PathVariable("taskId") int taskId,
            @RequestParam(value = "confirm", required = false, defaultValue = "0") int confirm,
            Model model) {
        Set<Integer> idset = new HashSet<Integer>();
        for (String id : ids) {
            idset.add(Integer.valueOf(id));
        }
        
        if (confirm == 0) {
            model.addAttribute("navigationActiveKey", "tasks");
            model.addAttribute("cancelUrl", WebUtils.createUrl("/tasks/"+taskId+"/surveys/options.html"));
            return "confirm/delete";
        } else {
            if (idset.size() == 1) {
                TaskSurveyOption option = taskSurveyOptionService.find(Integer.valueOf(ids[0]).intValue());
                if (null != option) {
                    taskSurveyOptionService.delete(option);
                    WebUtils.addSuccessFlashMessage(String.format("成功删除：<i>%s</i>", option.getLabel()));
                } else {
                    WebUtils.addErrorFlashMessage("选项不存在或已被删除");
                }
            } else {
                int count = taskSurveyOptionService.delete(idset);
                WebUtils.addSuccessFlashMessage(String.format("成功删除 <i>%d</i> 条选项", count));
            }
            
            return WebUtils.redirectUrl("/tasks/"+taskId+"/surveys/options.html");
        }
    }
}