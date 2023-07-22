package com.chatgpt.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;

@Data
@AllArgsConstructor
@Generated
public class Header {
    private String message;
    private String code;
    private String traceId;
}
