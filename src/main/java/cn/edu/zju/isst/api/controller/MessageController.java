package cn.edu.zju.isst.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.edu.zju.isst.common.ApiResponse;
import cn.edu.zju.isst.entity.Message;
import cn.edu.zju.isst.identity.RequireUser;
import cn.edu.zju.isst.service.MessageService;

@RequireUser
@Controller("apiMessageController")
public class MessageController {
    @Autowired
    private MessageService messageService;
    
    @RequestMapping("/messages")
    public @ResponseBody ApiResponse list(
            @RequestParam(value = "page", required = false, defaultValue = "1") int page,
            @RequestParam(value = "pageSize", required = false, defaultValue = "20") int pageSize
            ) {
        return new ApiResponse(messageService.findAll(Message.STATUS_PUSHED, pageSize, page).getItems());
    }
}