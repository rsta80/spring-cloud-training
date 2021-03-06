package org.rsta80.demo.exceptions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;

/**
 * Wraps exception data
 */
@Data
@Builder(toBuilder = true)
public class ExceptionDto {

    private Instant timestamp;
    private int status;
    private String errorMsg;
    private String details;
    private String spanId;
    private String traceId;

}
