package com.course.course.controllers;

import com.course.course.dtos.requestDto.CourseRequest;
import com.course.course.dtos.requestDto.CourseStatusRequest;
import com.course.course.dtos.responseDto.ApiResponse;
import com.course.course.dtos.responseDto.CourseResponse;
import com.course.course.services.CourseService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/course")
public class CourseController {

    private final CourseService courseService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<CourseResponse>>> getAllCourses(){
        return new ResponseEntity<>(
                        ApiResponse.success(
                                "All Course List",
                                courseService.findAll(),
                                HttpStatus.OK.value()),
                        HttpStatus.OK
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<CourseResponse>> getCourseById(@PathVariable(name = "id") Long id){
        return new ResponseEntity<>(
                ApiResponse.success(
                        "Succeess.",
                        courseService.findById(id),
                        HttpStatus.OK.value()
                ),
                HttpStatus.OK
        );
    }

    @PostMapping
    public ResponseEntity<ApiResponse<CourseResponse>> createCourse(
            @Valid @RequestBody CourseRequest courseRequest
    ){
        return new ResponseEntity<>(
                ApiResponse.success(
                        "success",
                        courseService.create(courseRequest),
                        HttpStatus.CREATED.value()
                ),
                HttpStatus.CREATED
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<CourseResponse>> updateCourse(
            @PathVariable(name = "id") Long id,
            @RequestBody CourseRequest courseRequest)
    {
        return new ResponseEntity<>(
                ApiResponse.success(
                        "Success",
                        courseService.update(id, courseRequest),
                        HttpStatus.OK.value()
                ),
                HttpStatus.OK
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable(name = "id") Long id){
        courseService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<ApiResponse<CourseResponse>> updateCourseStatus(
            @PathVariable(name = "id") Long id,
            @RequestBody CourseStatusRequest request){
        return new ResponseEntity<>(
                ApiResponse.success(
                        "Success",
                        courseService.updateStatus(id, request),
                        HttpStatus.OK.value()
                ),
                HttpStatus.OK);
    }
}