package com.tahachaudhry.universityapi.service;

import java.util.List;
import java.util.Optional;

import com.tahachaudhry.universityapi.model.Course;
import com.tahachaudhry.universityapi.repository.CourseRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CourseService {

    @Autowired
    private CourseRepository courseList;

    public Course addCourse(Course course) {
        return courseList.save(course);
    }

    public List<Course> getCourses() {
        return (List<Course>)courseList.findAll();
    }

    public Course getCourse(Long courseId) {
        Optional<Course> result = courseList.findById(courseId);
        return result.orElse(null);
    }

    public boolean updateCourse(Course course) {
        try {
            courseList.save(course);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean delete(Long courseId) {
        try {
            courseList.deleteById(courseId);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}


