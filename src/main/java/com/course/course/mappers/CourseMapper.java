package com.course.course.mappers;


import com.course.course.dtos.requestDto.CourseRequest;
import com.course.course.dtos.responseDto.CourseResponse;
import com.course.course.entities.Course;
import org.springframework.stereotype.Component;

@Component
public class CourseMapper {
    public Course toCourse(CourseRequest request) {
        Course course = new Course();
        course.setCourseName(request.getCourseName());
        course.setDescription(request.getDescription());
        course.setStartDate(request.getStartDate());
        course.setEndDate(request.getEndDate());

        return course;
    };

    public CourseResponse toCourseResponse(Course course) {
        return new CourseResponse(
                course.getId(),
                course.getCourseName(),
                course.getDescription(),
                course.getStartDate(),
                course.getEndDate(),
                course.getStatus()
        );
    }
}
