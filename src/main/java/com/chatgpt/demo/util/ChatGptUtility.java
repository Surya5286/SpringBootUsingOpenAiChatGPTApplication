package com.chatgpt.demo.util;

import com.chatgpt.demo.dto.DataResponse;
import com.chatgpt.demo.dto.Header;
import lombok.Generated;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;

@Slf4j
@Generated
public class ChatGptUtility {

    /**
     * Map the response to the DataResponse - used to send in negative scenarios.
     *
     * @param responseString
     * @param httpStatus
     * @param traceId
     * @param payload
     * @return
     */
    public static DataResponse<String> setDataResponse(String responseString, HttpStatus httpStatus, String traceId, String payload) {
        log.info("Inside the setDataResponse() method");
        DataResponse<String> response = new DataResponse<>();
        response.setHeader(new Header(responseString, String.valueOf(httpStatus.value()), traceId));
        response.setPayload(payload);
        response.setErrors(new ArrayList<>());
        log.info("Exiting from the setDataResponse() method | Prepared the inputs for the DataResponse<T> object");
        return response;
    }
}
