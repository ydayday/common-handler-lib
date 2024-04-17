package com.ydayday.cmlib.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@Builder
@AllArgsConstructor
public class CmlibErrorResponse {

    private int code;

    private String message;


    public static CmlibErrorResponse of(CmlibException ex) {
        return CmlibErrorResponse.builder()
                .code(ex.getStatus().value())
                .message(ex.getMessage())
                .build();
    }

    public static CmlibErrorResponse of(HttpStatus status, String message) {
        return CmlibErrorResponse.builder()
                .code(status.value())
                .message(message)
                .build();
    }

}
