package com.abc.io.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
/**
 * UnsupportedMediaTypeException is an exception for the unsupported file types
 *
 * @author Duygu Muslu
 * @since  2020-05-06
 * @version 1.0
 */

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UnsupportedMediaTypeException extends RuntimeException {
    public UnsupportedMediaTypeException(String message){
        super(message);
    }

}
