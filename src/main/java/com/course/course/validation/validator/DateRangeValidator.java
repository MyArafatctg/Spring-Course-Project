package com.course.course.validation.validator;

import com.course.course.dtos.requestDto.CourseRequest;
import com.course.course.validation.annotation.ValidDateRange;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class DateRangeValidator implements ConstraintValidator<ValidDateRange, CourseRequest> {
    @Override
    public boolean isValid(CourseRequest request, ConstraintValidatorContext context) {
        if (request.getStartDate() == null || request.getEndDate() == null) {
            return true; // let @NotNull handle null validation
        }
        return request.getEndDate().isAfter(request.getStartDate());
    }
}
