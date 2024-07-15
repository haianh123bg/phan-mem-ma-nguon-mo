package com.haianh123.library.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.io.Serializable;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse <T> implements Serializable {
    private int code = 1000;
    private String message;
    private T result;

    public ApiResponse(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
