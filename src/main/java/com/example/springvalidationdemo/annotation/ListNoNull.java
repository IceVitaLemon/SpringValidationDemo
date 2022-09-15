package com.example.springvalidationdemo.annotation;

import com.example.springvalidationdemo.validator.ListNoNullValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 自定义参数校验注解
 * 列表中不允许有null元素
 * @author Junhao
 */
@Target({ANNOTATION_TYPE, METHOD, ElementType.FIELD})
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = ListNoNullValidator.class) //指定了注解的验证实现类为ListNoNullValidator
public @interface ListNoNull {
    
    String message() default "List集合中不能含有null元素";
    
    Class<?>[] groups() default {};
    
    Class<? extends Payload>[] payload() default {};
    
    /**
     * 定义List，为了让Bean的一个属性上可以添加多套规则
     */
    @Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER})
    @Retention(RUNTIME)
    @Documented
    @interface List {
        ListNoNull[] value();
    }
}
