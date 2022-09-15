package com.example.springvalidationdemo.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class ValidationExceptionHandler {
    // 使用@Valid或者@Validated校验传入‘对象’的‘属性’时，校验失败后抛出的异常是MethodArgumentNotValidException
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public void handleMethodArgumentNotValidException(HttpServletResponse response, MethodArgumentNotValidException exception) throws IOException {
        log.info("handleMethodArgumentNotValidException");
        String message = exception.getBindingResult().getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining("\n"));
        log.error(message);
        ServletOutputStream outputStream = response.getOutputStream();
        outputStream.write(message.getBytes(StandardCharsets.UTF_8));
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
    }

    // Spring validation方法入参上对‘单个参数’进行校验，校验失败时会抛出ConstraintViolationException异常
    @ExceptionHandler(ConstraintViolationException.class)
    public void handleConstraintViolationException(HttpServletResponse response, ConstraintViolationException exception) throws IOException {
        log.info("handleConstraintViolationException");
        String message = exception.getConstraintViolations().stream().map(ConstraintViolation::getMessage).collect(Collectors.joining("\n"));
        log.error(message);
        ServletOutputStream outputStream = response.getOutputStream();
        outputStream.write(message.getBytes(StandardCharsets.UTF_8));
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
    }
}
