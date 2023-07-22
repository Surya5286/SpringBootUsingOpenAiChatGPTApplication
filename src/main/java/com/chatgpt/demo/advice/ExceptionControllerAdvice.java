package com.chatgpt.demo.advice;

import com.chatgpt.demo.dto.DataResponse;
import com.chatgpt.demo.exception.InternalServerError;
import com.chatgpt.demo.exception.ResourceNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;

import static com.chatgpt.demo.constants.ChatGptConstants.EXCEPTION_OCCURRED_PROCESSING;
import static com.chatgpt.demo.constants.ChatGptConstants.EXCEPTION_RECEIVED;
import static com.chatgpt.demo.util.ChatGptUtility.setDataResponse;

@ControllerAdvice
@Slf4j
public class ExceptionControllerAdvice {

    @ExceptionHandler(value = ResourceNotFoundException.class)
    public final ResponseEntity<DataResponse<String>> handleResourceNotFoundException(ResourceNotFoundException e) {
        log.error("Inside the handleResourceNotFoundException() method.");
        DataResponse<String> errResponse = setDataResponse(EXCEPTION_RECEIVED + e.getClass(), HttpStatus.NOT_FOUND, MDC.get("traceId"), EXCEPTION_OCCURRED_PROCESSING);
        List<Error> errorList = new ArrayList<>();
        errorList.add(new Error(e.getMessage()));
        errResponse.setErrors(errorList);

        log.error("Received ResourceNotFoundException: ", e.getClass());
        return new ResponseEntity<DataResponse<String>>(errResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {InternalServerError.class})
    public final ResponseEntity<DataResponse<String>> handleInternalServerError(InternalServerError e) {
        log.error("Inside the handleInternalServerError() method.");
        DataResponse<String> errResponse = setDataResponse(EXCEPTION_RECEIVED + e.getClass(), HttpStatus.INTERNAL_SERVER_ERROR, MDC.get("traceId"), EXCEPTION_OCCURRED_PROCESSING);
        List<Error> errorList = new ArrayList<>();
        errorList.add(new Error(e.getMessage()));
        errResponse.setErrors(errorList);

        log.error("Received InternalServerError: ", e.getClass());
        return new ResponseEntity<DataResponse<String>>(errResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = Exception.class)
    public final ResponseEntity<DataResponse<String>> handleOtherException(Exception e) {
        log.error("Inside the handleOtherException() method.");
        DataResponse<String> errResponse = setDataResponse(EXCEPTION_RECEIVED + e.getClass(), HttpStatus.INTERNAL_SERVER_ERROR, MDC.get("traceId"), EXCEPTION_OCCURRED_PROCESSING);
        List<Error> errorList = new ArrayList<>();
        errorList.add(new Error(e.getMessage()));
        errResponse.setErrors(errorList);

        log.error("Received Exception: ", e.getClass());
        return new ResponseEntity<DataResponse<String>>(errResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
