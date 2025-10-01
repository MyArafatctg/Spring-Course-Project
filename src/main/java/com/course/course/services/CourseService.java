package com.course.course.services;

import com.course.course.dtos.requestDto.CourseRequest;
import com.course.course.dtos.requestDto.CourseStatusRequest;
import com.course.course.dtos.responseDto.CourseResponse;

import java.util.List;

public interface CourseService {
    public List<CourseResponse> findAll();
    public CourseResponse findById(Long id);
    public CourseResponse create(CourseRequest courseRequest);
    public CourseResponse update(Long id, CourseRequest courseRequest);
    public void deleteById(Long id);
    public CourseResponse updateStatus(Long id, CourseStatusRequest request);
}
