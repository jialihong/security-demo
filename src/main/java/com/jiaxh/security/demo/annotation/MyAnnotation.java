package com.jiaxh.security.demo.annotation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义注解
 * @Auther: jiaxh
 * @Date: 2019/4/29 11:20
 */
@Target({ElementType.METHOD,ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = MyConstraintValidator.class)
public @interface MyAnnotation {

    String message();

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

}
