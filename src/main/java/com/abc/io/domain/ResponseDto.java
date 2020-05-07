package com.abc.io.domain;

import lombok.Builder;
import lombok.Data;

/**
 * ResponseDto is data transfer object for representing ResponseEntity class to use in application
 *
 * @author Duygu Muslu
 * @since  2020-05-06
 * @version 1.0
 */

@Data
@Builder
public class ResponseDto<T> {
    private String status;

    @Builder.Default
    private String message = "Success";

    private T body;
}