package com.mpirv.actionmonitor.controller;

import com.mpirv.actionmonitor.model.ChatMessage;
import com.mpirv.actionmonitor.service.MessageService;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
public class ChatController {
    private final MessageService messageService;

    public ChatController(MessageService messageService) {
        this.messageService = messageService;
    }

    @MessageMapping("/chat.send")
    @SendTo("/topic/public")
    @PostMapping("/message")
    public ChatMessage sendMessage(@RequestBody @NotNull final ChatMessage chatMessage) {
        log.info("Message sent by - " + chatMessage.getSender());
        messageService.insert(chatMessage.getContent());
        return chatMessage;
    }

    @MessageMapping("/chat.newUser")
    @SendTo("/topic/public")
    public ChatMessage newUser(@RequestBody @NotNull final ChatMessage chatMessage, @NotNull SimpMessageHeaderAccessor headerAccessor) {
        headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
        log.info("User - " + chatMessage.getSender() + " has connected");
        return chatMessage;
    }

    @GetMapping("/all")
    public List<String> allMessages(){
        log.info("Retrieve all messages from the Database!");
        return messageService.findAll();
    }
}

