package com.tahachaudhry.universityapi.controller;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tahachaudhry.universityapi.service.CourseService;
import com.tahachaudhry.universityapi.service.UniversityService;


import com.tahachaudhry.universityapi.exception.BadRequestException;
import com.tahachaudhry.universityapi.exception.ResourceNotFoundException;
import com.tahachaudhry.universityapi.repository.CourseRepository;
import com.tahachaudhry.universityapi.model.Course;

@RestController
public class CourseController {
	
    @Autowired
    private CourseRepository courseRepository;

	@Autowired
	private CourseService courseService;

	@Autowired
	private UniversityService universityService;

	@GetMapping("/courses")
	@ResponseBody
	public List<Course> getAllCourses() {
		return courseService.getCourses();
	}

	@GetMapping("/course/{id}")
	@ResponseBody
	public ResponseEntity<Course> getCourseById(@PathVariable(value = "id") Long courseId) throws ResourceNotFoundException {
		try {
			Objects.requireNonNull(courseService.getCourse(courseId));

			return ResponseEntity.ok(courseService.getCourse(courseId));
		} catch (NullPointerException e) {
			throw new ResourceNotFoundException("Course not found");
		}
	}

	@PostMapping("/course")
	@ResponseBody
	public ResponseEntity<Course> createCourse(@RequestBody Course course) throws BadRequestException, ResourceNotFoundException {
		if (course.getName() == null) throw new BadRequestException("Course Name is required");
	
		if (course.getDuration() == null) throw new BadRequestException("Course Duration is required");
		
		if (course.getUniversity() == null) throw new BadRequestException("Course University is required");
		
		if (course.getDescription() == null) throw new BadRequestException("Course Description is required");
		
		if (course.getEntryRequirements() == null) throw new BadRequestException("Course Entry Requirements is required");

		if (universityService.getUniversity(course.getUniversity().getId()) == null) throw new ResourceNotFoundException("University not found");

		final Course addedCourse = courseService.addCourse(course);
		return ResponseEntity.ok(addedCourse);
	}

	@PutMapping("/course/{id}")
	@ResponseBody
	public ResponseEntity<Course> updateCourse(@PathVariable(value = "id") Long courseId, @RequestBody Course courseDetails) throws ResourceNotFoundException, BadRequestException {
		Course course = courseService.getCourse(courseId);
		
		if (course == null) throw new ResourceNotFoundException("Course not found");

		if (courseDetails.getName() == null) throw new BadRequestException("Course Name is required");
	
		if (courseDetails.getUniversity() == null) throw new BadRequestException("Course University is required");
		
		if (courseDetails.getDescription() == null) throw new BadRequestException("Course Description is required");
		
		if (courseDetails.getEntryRequirements() == null) throw new BadRequestException("Course Entry Requirements is required");

		if (universityService.getUniversity(courseDetails.getUniversity().getId()) == null) throw new ResourceNotFoundException("University not found");

		course.setName(courseDetails.getName());
		course.setDuration(courseDetails.getDuration());
		course.setDescription(courseDetails.getDuration());
		course.setEntryRequirements(courseDetails.getEntryRequirements());
		course.setUniversity(courseDetails.getUniversity());
		final Course updatedCourse = courseRepository.save(course);
		return ResponseEntity.ok(updatedCourse);
	}

	@DeleteMapping("/course/{id}")
	@ResponseBody
	public ResponseEntity<Boolean> deleteCourse(@PathVariable(value = "id") Long courseId) throws ResourceNotFoundException {
		try {
			Objects.requireNonNull(courseService.getCourse(courseId));

			return ResponseEntity.ok(courseService.delete(courseId));
		} catch (NullPointerException e) {
			throw new ResourceNotFoundException("Course not found");
		}
	}
}
