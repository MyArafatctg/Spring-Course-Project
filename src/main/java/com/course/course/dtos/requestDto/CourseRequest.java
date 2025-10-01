package com.course.course.dtos.requestDto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CourseRequest {
    @NotBlank(message = "courseName is required")
    private String courseName;

    @NotBlank(message = "description is required")
    private String description;

    @NotBlank(message = "startDate is required")
    private LocalDate startDate;

    @NotBlank(message = "endDate is required")
    private LocalDate endDate;
}
