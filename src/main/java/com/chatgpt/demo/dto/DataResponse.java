package com.chatgpt.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Generated
public class DataResponse<T> {

    private Header header;
    private T payload;  // any object or JSON can be sent through this generic Object
    private List<Error> errors;
}
