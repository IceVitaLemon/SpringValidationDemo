package com.example.springvalidationdemo.controller;

import com.example.springvalidationdemo.controller.dto.UserDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping(value = "/innerHandle")
public class HandleExceptionController {

    @PostMapping(value = "/user")
    public void addUser(@Valid @RequestBody UserDTO userDTO){
//        绑定错误时，不会进入controller方法，会抛出异常
        log.info("inner addUser");
    }
    
    @ExceptionHandler(Exception.class)
    public void handleException(Exception e){
        log.info("handleException");
        log.error(e.getMessage());
    }
}
