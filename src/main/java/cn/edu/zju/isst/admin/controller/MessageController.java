package cn.edu.zju.isst.admin.controller;

import java.util.HashSet;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import cn.edu.zju.isst.common.WebUtils;
import cn.edu.zju.isst.entity.Message;
import cn.edu.zju.isst.form.MessageForm;
import cn.edu.zju.isst.service.MessageService;

@Controller("adminMessageController")
public class MessageController {
    @Autowired
    private MessageService messageService;
    
    @RequestMapping(value = "/messages.html", method = RequestMethod.GET)
    public String list(Model model,
            @RequestParam(value = "status", required = false, defaultValue = "-1") int status,
            @RequestParam(value = "page", required = false, defaultValue = "1") int page) {
        model.addAttribute("messages", messageService.findAll(status, 10, page));
        return "messages/list";
    }
    
    @RequestMapping(value = "/messages/add.html", method = RequestMethod.GET)
    public String add(Model model) {
        MessageForm form = new MessageForm();
        model.addAttribute("messageForm", form);
        
        return "messages/form";
    }
    
    @RequestMapping(value = "/messages/add.html", method = RequestMethod.POST)
    public String saveAdd(
            @Valid MessageForm form, 
            BindingResult result, 
            Model model) {
        if (result.hasErrors()) {
            model.addAttribute("messageForm", form);
            return "messages/form";
        } else {
            Message message = form.build();
            messageService.save(message);
            if (messageService.push(message) > 0) {
                WebUtils.addSuccessFlashMessage(String.format("成功推送 <i>%s</i>", message));
            } else {
                WebUtils.addErrorFlashMessage(String.format("推送失败 <i>%s</i>", message));
            }
            return WebUtils.redirectUrl("/messages.html");
        }
    }
    
    @RequestMapping(value = "/messages/delete")
    public String delete(
            @RequestParam("id[]") String[] ids,
            @RequestParam(value = "confirm", required = false, defaultValue = "0") int confirm,
            Model model) {
        Set<Integer> idset = new HashSet<Integer>();
        for (String id : ids) {
            idset.add(Integer.valueOf(id));
        }
        
        if (confirm == 0) {
            model.addAttribute("entities", messageService.findAll(idset));
            model.addAttribute("navigationActiveKey", "message");
            model.addAttribute("cancelUrl", WebUtils.createUrl("/messages.html"));
            return "confirm/delete";
        } else {
            if (idset.size() == 1) {
                Message message = messageService.find(Integer.valueOf(ids[0]).intValue());
                if (null != message) {
                    messageService.delete(message);
                    WebUtils.addSuccessFlashMessage(String.format("成功删除：<i>%s</i>", message));
                } else {
                    WebUtils.addErrorFlashMessage("消息不存在或已被删除");
                }
            } else {
                int count = messageService.delete(idset);
                WebUtils.addSuccessFlashMessage(String.format("成功删除 <i>%d</i> 条消息", count));
            }
            
            return WebUtils.redirectUrl("/messages.html");
        }
    }
    
    @RequestMapping(value = "/message/push")
    public String push(
            @RequestParam("id[]") String[] ids) {
        Set<Integer> idset = new HashSet<Integer>();
        for (String id : ids) {
            idset.add(Integer.valueOf(id));
        }
        
        if (idset.isEmpty()) {
            WebUtils.addErrorFlashMessage("无消息可推送");
        } else if (idset.size() == 1) {
            Message message = messageService.find(Integer.valueOf(ids[0]).intValue());
            if (null != message) {
                if (messageService.push(message) > 0) {
                    WebUtils.addSuccessFlashMessage(String.format("成功推送 <i>%s</i>", message));
                } else {
                    WebUtils.addErrorFlashMessage(String.format("推送失败 <i>%s</i>", message));
                }
            } else {
                WebUtils.addErrorFlashMessage("无消息可推送");
            }
        } else {
            int count = messageService.push(idset);
            WebUtils.addSuccessFlashMessage(String.format("成功推送 <i>%d</i> 条消息", count));
        }
        
        return WebUtils.redirectUrl("/messages.html");
    }
}