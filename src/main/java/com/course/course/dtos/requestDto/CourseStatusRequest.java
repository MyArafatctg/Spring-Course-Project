package com.course.course.dtos.requestDto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class CourseStatusRequest {
    @NotNull(message = "Status must be provided")
    private Boolean status;
}
