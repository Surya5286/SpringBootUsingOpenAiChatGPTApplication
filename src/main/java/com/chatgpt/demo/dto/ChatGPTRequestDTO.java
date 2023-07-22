package com.chatgpt.demo.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ChatGPTRequestDTO {

    private String model;
    private List<Message> messages;

    public ChatGPTRequestDTO(String model, String prompt) {
        this.model = model;
        this.messages = new ArrayList<>();
        Message msg = new Message();
        msg.setRole("user");
        msg.setContent(prompt);
        this.messages.add(msg);
    }
}
