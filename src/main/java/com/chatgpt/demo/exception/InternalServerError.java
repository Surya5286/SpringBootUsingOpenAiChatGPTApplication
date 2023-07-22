package com.chatgpt.demo.exception;

import com.chatgpt.demo.config.Generated;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Generated
@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class InternalServerError extends RuntimeException {
    public InternalServerError(String message) {
        super(message);
    }
}
