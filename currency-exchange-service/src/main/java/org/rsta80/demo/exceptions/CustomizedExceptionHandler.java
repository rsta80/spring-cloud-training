package org.rsta80.demo.exceptions;

import brave.Tracer;
import brave.Tracing;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class CustomizedExceptionHandler extends ResponseEntityExceptionHandler {

    final Tracer tracer;

    public CustomizedExceptionHandler(Tracer tracer) {
        this.tracer = tracer;
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public final ResponseEntity<?> allExceptionHandler(Exception ex, WebRequest request) {

        return new ResponseEntity<>(error(ex, 500, request.getDescription(false)), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ObjectNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ExceptionDto> notFoundException(Exception ex, WebRequest request) {

        return new ResponseEntity<>(error(ex, 404, request.getDescription(false)), HttpStatus.NOT_FOUND);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status, WebRequest request) {
        log.error("Error during validation: " + ex.getMessage());
        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());

        return new ResponseEntity<>(this.error(ex, status.value(), errors.toString()), headers, status);

    }

    private ExceptionDto error(Exception e, int code, String details) {
        return ExceptionDto.builder()
                .timestamp(Instant.now())
                .status(code)
                .errorMsg(e.getMessage())
                .details(details)
                .traceId(tracer.currentSpan().context().traceIdString())
                .spanId(tracer.currentSpan().context().spanIdString())
                .build();
    }

}
