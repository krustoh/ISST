package cn.edu.zju.isst.api.controller;

import cn.edu.zju.isst.common.ApiResponse;
import cn.edu.zju.isst.entity.Message;
import cn.edu.zju.isst.entity.MessagePushBind;
import cn.edu.zju.isst.identity.RequireUser;
import cn.edu.zju.isst.service.MessagePushBindService;
import cn.edu.zju.isst.service.MessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;

import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;


@Controller("apiMessagePushBindController")
public class MessagePushBindController {
    private final Logger log = LoggerFactory.getLogger(MessagePushBindController.class);


    @Autowired
    private MessagePushBindService messagePushBindService;

    @RequestMapping(value = "/messages/binds", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void create(@RequestBody MessagePushBind bind) {
        log.debug("REST request to insert BindInfo : {}", bind);
        if (!messagePushBindService.isBinded(bind))
            messagePushBindService.create(bind);

    }


    @RequestMapping(value = "/messages/binds/{stuid}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public
    @ResponseBody
    List<MessagePushBind> list(@PathVariable("stuid") String studentId) {
        return messagePushBindService.getBindinfo(studentId);
    }
}