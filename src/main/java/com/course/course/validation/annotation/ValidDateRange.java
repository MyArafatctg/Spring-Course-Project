package com.course.course.validation.annotation;

import com.course.course.validation.validator.DateRangeValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = DateRangeValidator.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidDateRange {
    String message() default "Invalid Date Range";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
