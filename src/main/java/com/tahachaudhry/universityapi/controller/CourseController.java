package com.tahachaudhry.universityapi.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tahachaudhry.universityapi.service.CourseService;
import com.tahachaudhry.universityapi.exception.ResourceNotFoundException;
import com.tahachaudhry.universityapi.repository.CourseRepository;
import com.tahachaudhry.universityapi.model.Course;
import com.tahachaudhry.universityapi.model.University;

@Controller
public class CourseController {
	
    @Autowired
    private CourseRepository courseRepository;

	@Autowired
	private CourseService courseService;

	@GetMapping("/courses")
	@ResponseBody
	public List<Course> getAllCourses() {
		return courseService.getCourses();
	}

	@GetMapping("/course/{id}")
	@ResponseBody
	public Course getCourseById(@PathVariable(value = "id") Long courseId) {
		return courseService.getCourse(courseId);
	}

	@PostMapping("/course")
	@ResponseBody
	public String createCourse(@RequestBody Course course) {
		if (course.getName() != null && course.getUniversity() != null) {
			courseService.addCourse(course);
			return "Added a course";
		} else {
			return "Request does not contain a body";
		}
	}

	@PutMapping("/course/{id}")
	@ResponseBody
	public ResponseEntity<Course> updateCourse(@PathVariable(value = "id") Long courseId, @RequestBody Course courseDetails) throws ResourceNotFoundException {
		Course course = courseService.getCourse(courseId);

		course.setName(courseDetails.getName());
		final Course updatedCourse = courseRepository.save(course);
		return ResponseEntity.ok(updatedCourse);
	}

	@DeleteMapping("/course/{id}")
	@ResponseBody
	public Map<String, Boolean> deleteCourse(@PathVariable(value = "id") Long courseId) throws ResourceNotFoundException {

		courseService.delete(courseId);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
}
