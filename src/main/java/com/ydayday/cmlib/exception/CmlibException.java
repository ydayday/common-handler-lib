package com.ydayday.cmlib.exception;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

@Slf4j
@Getter
public class CmlibException extends RuntimeException {

    private final HttpStatus status;

    private final String message;

    public CmlibException(HttpStatus status, String message, Throwable ex) {
        super(message);
        this.status = status;
        this.message = message;
        printErrorLog(ex);
    }

    public CmlibException(HttpStatus status, String message) {
        super(message);
        this.status = status;
        this.message = message;
    }

    private static void printErrorLog(Throwable ex) {
        log.error("==================== Exception ====================");
        log.error("Target : {}", ex.getStackTrace());
        log.error("Message : {}", ex.getMessage());
        log.error("==================== Exception ====================");
    }


}
