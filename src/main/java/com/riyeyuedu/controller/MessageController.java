package com.riyeyuedu.controller;

import com.riyeyuedu.entity.MessageEntity;
import com.riyeyuedu.entity.ResponseEntity;
import com.riyeyuedu.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
public class MessageController {
    private MessageService messageService;

    @Autowired
    public void setMessageService(MessageService messageService) {
        this.messageService = messageService;
    }

    @RequestMapping(value = "/addMessage", method = RequestMethod.POST)
    @CrossOrigin
    public ResponseEntity addMessage(@RequestBody MessageEntity messageEntity) {
        messageEntity.setTime(new Date().getTime());
        messageEntity.setIsRead(0);
        return new ResponseEntity(messageService.addMessage(messageEntity));
    }

    @RequestMapping(value = "/message/{uid}/{type}")
    @CrossOrigin
    public ResponseEntity getMessageByUid(@PathVariable int uid, @PathVariable int type) {
        Map<String, Object> map = new HashMap<>();
        map.put("uid", uid);
        map.put("type", type);
        return new ResponseEntity(messageService.getMessageByToId(map));
    }

    @RequestMapping(value = "/message/{mid}")
    @CrossOrigin
    public ResponseEntity getMessageByMid(@PathVariable Long mid) {
        return new ResponseEntity(messageService.getMessageByMid(mid));
    }

    @RequestMapping(value = "/message/delete/{mid}")
    @CrossOrigin
    public ResponseEntity deleteMessage(@PathVariable Long mid) {
        return new ResponseEntity(messageService.deleteMessage(mid));
    }

    @RequestMapping(value = "/unReadMessageNum/{uid}")
    @CrossOrigin
    public ResponseEntity getUnReadMessageNum(@PathVariable int uid) {
        return new ResponseEntity(messageService.getUnReadMessageNum(uid));
    }
}
