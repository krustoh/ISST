package cn.edu.zju.isst.api.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.edu.zju.isst.common.ApiResponse;
import cn.edu.zju.isst.common.Result;
import cn.edu.zju.isst.entity.StudentUser;
import cn.edu.zju.isst.form.TaskSurveyForm;
import cn.edu.zju.isst.identity.RequireUser;
import cn.edu.zju.isst.service.TaskSurveyOptionService;
import cn.edu.zju.isst.service.TaskSurveyService;

@RequireUser
@Controller("apiTaskSurveyController")
public class TaskSurveyController {
    @Autowired
    private TaskSurveyService taskSurveyService;
    
    @Autowired
    private TaskSurveyOptionService taskSurveyOptionSerivce;

    @RequestMapping("/tasks/{taskId}/survey/{id}")
    public @ResponseBody ApiResponse find(@PathVariable("taskId") int taskId, @PathVariable("id") int id) {
        return new ApiResponse(taskSurveyService.find(id));
    }
    
    @RequestMapping("/tasks/{taskId}/survey/options")
    public @ResponseBody ApiResponse list(@PathVariable("taskId") int taskId) {
        return new ApiResponse(taskSurveyOptionSerivce.findAll(taskId));
    }
    
    @RequestMapping(value = "/tasks/{taskId}/survey", method = RequestMethod.POST)
    public @ResponseBody ApiResponse save(
            @PathVariable("taskId") int taskId,
            @Valid TaskSurveyForm form,
            BindingResult result,
            HttpSession session) {
        StudentUser user = (StudentUser) session.getAttribute("user");
        form.setTaskId(taskId);
        form.setUserId(user.getId());
        
        if (result.hasErrors()) {
            return new ApiResponse(result);
        }
        
        Result res = form.validate();
        if (res.valid()) {
            res = taskSurveyService.save(form);
            if (res.valid()) {
                return new ApiResponse(res.getBody());
            }
        }
        
        return new ApiResponse(res);
    }
}