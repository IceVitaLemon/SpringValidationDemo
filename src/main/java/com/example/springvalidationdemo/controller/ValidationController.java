package com.example.springvalidationdemo.controller;

import com.example.springvalidationdemo.controller.dto.UserDTO;
import com.example.springvalidationdemo.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.groups.Default;
import java.util.ArrayList;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping(value = "/validation")
//@Validated
public class ValidationController {
    
    
    @PostMapping(value = "/user1")
    public void addUser1(@Valid @RequestBody UserDTO userDTO, BindingResult bindingResult){
//        使用BindingResult就算绑定出错，也会进入controller方法
        log.error(bindingResult.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining("\n")));
        log.info("addUser1");
    }

    @PostMapping(value = "/user2")
    public void addUser2(@Validated @RequestBody UserDTO userDTO){
//        绑定错误时，不会进入controller方法，会抛出异常
        log.info("addUser2");
    }
    
    @PostMapping(value = "/user3")
    public void addUser3(@Validated({UserDTO.Update.class, Default.class}) @RequestBody UserDTO userDTO){
        log.info("addUser3");
    }
    
    @GetMapping(value = "/user")
    public User getUser(@NotBlank String username){
        User user = new User();
        user.setUserName(username);
        return user;
    }

    @GetMapping(value = "/user/{id}")
    public User getUser2(@Min(value = 10) @PathVariable("id") Long userId){
        User user = new User();
        user.setUserId(userId);
        return user;
    }

    
    @PostMapping(value = "/validateUser")
    public void validateUser(@RequestBody UserDTO userDTO){
        userDTO.setBooks(new ArrayList<>());
        userDTO.getBooks().add(null);
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<UserDTO>> validate = validator.validate(userDTO);
        String message = validate.stream().map(ConstraintViolation::getMessage).collect(Collectors.joining("\n"));
        System.out.println(message);
    }
    
}
