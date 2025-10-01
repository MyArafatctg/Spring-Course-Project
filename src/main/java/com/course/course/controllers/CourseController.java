package com.course.course.controllers;

import com.course.course.dtos.requestDto.CourseRequest;
import com.course.course.dtos.requestDto.CourseStatusRequest;
import com.course.course.dtos.responseDto.ApiResponse;
import com.course.course.dtos.responseDto.CourseResponse;
import com.course.course.services.CourseService;
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
        return new ResponseEntity<>(ApiResponse.success("All Course List",courseService.findAll(),HttpStatus.OK.value()), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseResponse> getCourseById(@PathVariable(name = "id") Long id){
        return new ResponseEntity<>(courseService.findById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CourseResponse> createCourse(@RequestBody CourseRequest courseRequest){
        return new ResponseEntity<>(courseService.create(courseRequest), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CourseResponse> updateCourse(
            @PathVariable(name = "id") Long id,
            @RequestBody CourseRequest courseRequest)
    {
        return new ResponseEntity<>(courseService.update(id, courseRequest), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable(name = "id") Long id){
        courseService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<CourseResponse> updateCourseStatus(
            @PathVariable(name = "id") Long id,
            @RequestBody CourseStatusRequest request){
        return new ResponseEntity<>(courseService.updateStatus(id, request), HttpStatus.OK);
    }
}