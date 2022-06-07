package com.mpirv.actionmonitor.model;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
public class ChatMessage {
    private MessageType type;
    private String content;
    private String sender;
    private String time;
}
