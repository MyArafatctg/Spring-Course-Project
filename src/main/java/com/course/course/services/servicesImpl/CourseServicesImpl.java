package com.course.course.services.servicesImpl;

import com.course.course.dtos.requestDto.CourseRequest;
import com.course.course.dtos.requestDto.CourseStatusRequest;
import com.course.course.dtos.responseDto.CourseResponse;
import com.course.course.mappers.CourseMapper;
import com.course.course.repositories.CourseRepository;
import com.course.course.services.CourseService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CourseServicesImpl implements CourseService {
    private final CourseRepository courseRepository;
    private final CourseMapper mapper;
    private static final Logger LOGGER = LoggerFactory.getLogger(CourseServicesImpl.class);

    @Override
    public List<CourseResponse> findAll() {
        return courseRepository.findAll()
                .stream()
                .map(mapper::toCourseResponse)
                .toList();
    }

    @Override
    public CourseResponse findById(Long id) {
        return courseRepository.findById(id)
                .map(mapper::toCourseResponse)
                .orElseThrow(() -> new EntityNotFoundException("Course with id: " + id + " not found"));
    }

    @Override
    @Transactional
    public CourseResponse create(CourseRequest courseRequest) {
        var course = mapper.toCourse(courseRequest);
        courseRepository.save(course);
        LOGGER.info("Course created: {}", course);
        return mapper.toCourseResponse(course);
    }

    @Override
    public CourseResponse update(Long id, CourseRequest courseRequest) {
        var course = courseRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Course with id: " + id + " not found")
        );
        course.setCourseName(courseRequest.getCourseName());
        course.setDescription(courseRequest.getDescription());
        course.setStartDate(courseRequest.getStartDate());
        course.setEndDate(courseRequest.getEndDate());
        courseRepository.save(course);
        LOGGER.info("Course updated: {}", course);
        return mapper.toCourseResponse(course);
    }

    @Override
    public void deleteById(Long id) {
        var course = courseRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Course with id: " + id + " not found")
        );
        courseRepository.delete(course);
        LOGGER.info("Course deleted: {}", course);
    }

    @Override
    public CourseResponse updateStatus(Long id, CourseStatusRequest statusRequest) {
        var course = courseRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Course with id: " + id + " not found")
        );
        course.setStatus(statusRequest.getStatus());
        courseRepository.save(course);
        LOGGER.info("Course status updated: {}", course);
        return mapper.toCourseResponse(course);
    }
}
