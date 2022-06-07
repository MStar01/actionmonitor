package com.mpirv.actionmonitor.model;

import lombok.*;
import javax.persistence.*;

@Entity
@Table(name = "messages")
@Getter
@Setter
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @Column
    private String message;

    @Column
    private String time;

    public Message(){}

    public Message(String message, String time) {
        this.message = message;
        this.time = time;
    }

    @Override
    public String toString() {
        return "timestamp=" + time + " :: " + message;
    }

}
