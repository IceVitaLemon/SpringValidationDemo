package com.example.springvalidationdemo.validator;

import com.example.springvalidationdemo.annotation.ListNoNull;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class ListNoNullValidator implements ConstraintValidator<ListNoNull, List> {
    
    // 实现具体的验证逻辑
    @Override
    public boolean isValid(List list, ConstraintValidatorContext constraintValidatorContext) {
        if(list == null){
            return true;
        }
        for(Object object : list){
            // 如果List集合中存在null元素，校验失败
            if(object == null){
                return false;
            }
        }
        return true;
    }

    // 验证前做的初始化工作
    // 默认是没有操作
    @Override
    public void initialize(ListNoNull constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }
}
