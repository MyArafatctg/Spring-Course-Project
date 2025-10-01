package com.course.course.dtos.requestDto;

import com.course.course.validation.annotation.ValidDateRange;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
@ValidDateRange
public class CourseRequest {
    @NotBlank(message = "courseName is required")
    private String courseName;

    @NotBlank(message = "description is required")
    private String description;

    @NotNull(message = "startDate is required")
    private LocalDate startDate;

    @NotNull(message = "endDate is required")
    private LocalDate endDate;
}
