package com.example.springvalidationdemo.controller.dto;

import com.example.springvalidationdemo.annotation.ListNoNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class UserDTO {
    
    @NotNull(groups = Update.class)
    private Long userId;
    
    @NotBlank
    @Length(max = 32)
    @Pattern(regexp = "^[\\\\u4E00-\\\\u9FA5A-Za-z0-9\\\\*]*$", message = "用户昵称限制：最多20字符，包含文字、字母和数字")
    private String userName;
    
    @NotBlank
    @Pattern(regexp = "^((17[0-9])|(14[0-9])|(13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$")
    private String mobile;
    
    private String sex;
    
    @NotBlank
    @Email
    private String email;
    
    private String password;
    
    @Future
    private LocalDateTime createdTime;
    
    @ListNoNull(message = "List中不能含有null元素")
    List<String> books;

    @NotNull
    @Valid
    private Item item;

    @Data
    public static class Item{
        @NotNull
        private Long itemId;

        @NotBlank
        private String itemName;
    }
    
    /**
     * 保存时候的校验分组
     */
    public interface Save{}

    /**
     * 更新时候的校验分组
     */
    public interface Update{}
}
