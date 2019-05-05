package com.jiaxh.security.demo.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 自定义注解，实现ConstraintValidator接口，可以在此类中注入任何自定义的类
 * @Auther: jiaxh
 * @Date: 2019/4/29 11:23
 */
public class MyConstraintValidator implements ConstraintValidator<MyAnnotation,Object> {

    @Override
    public void initialize(MyAnnotation constraintAnnotation) {
        System.out.println("my validator init ...");
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        System.out.println("isValid.value = "+value);
        return false;
    }
}
