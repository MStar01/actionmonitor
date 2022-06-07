package com.mpirv.actionmonitor.controller;

import com.mpirv.actionmonitor.model.Message;
import com.mpirv.actionmonitor.service.MessageService;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
public class MessageController {
    private MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @MessageMapping("/test")
    @SendTo("/topic/messages")
    @PostMapping("/message")
    public String addMessage(@RequestBody @NonNull Message message) {
        if (message.getMessage() != null && message.getMessage().length() > 0) {
            log.info("Message Sent: " + message.getMessage());
            return messageService.insert(message);
        }
        return "";
    }

    @GetMapping("/all")
    public List<String> allMessages(){
        log.info("Retrieve all messages!");
        return messageService.findAll();
    }

}
