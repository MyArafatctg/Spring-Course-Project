package com.course.course.dtos.responseDto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@AllArgsConstructor
@Data
public class CourseResponse {
    private Long id;

    private String courseName;

    private String description;

    private LocalDate startDate;

    private LocalDate endDate;

    private Boolean status;
}
