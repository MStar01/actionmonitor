package com.mpirv.actionmonitor.service;

import com.mpirv.actionmonitor.model.ChatMessage;
import com.mpirv.actionmonitor.model.Message;
import com.mpirv.actionmonitor.repo.MessageRepository;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Transactional(rollbackFor = Exception.class)
@Slf4j
public class MessageService {
    private MessageRepository messageRepository;

    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }
    public void insert(@NotNull String content) {
        Message message = new Message();
        message.setTime(new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss").format(Calendar.getInstance().getTime()));
        message.setMessage(content);
        messageRepository.save(message);
    }
    public List<String> findAll() {
        return StreamSupport
                .stream(messageRepository.findAll().spliterator(), false)
                .map((message) -> message.toString())
                .collect(Collectors.toList());
    }
}
