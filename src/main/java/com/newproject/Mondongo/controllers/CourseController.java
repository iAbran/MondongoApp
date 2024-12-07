package com.newproject.Mondongo.controllers;

import com.newproject.Mondongo.dto.CourseDTO;
import com.newproject.Mondongo.entities.Course;
import com.newproject.Mondongo.services.CourseService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/inicio")
public class CourseController {
    private final CourseService service;

    public CourseController(CourseService service) {
        this.service = service;
    }

    //GET ALL
    @GetMapping(path = "/course")
    public List<CourseDTO> getCourses() {
        return service.getCourses();
    }

    //GET BY ID
    @GetMapping(path = "/course/{id}")
    public CourseDTO getCourseById(@PathVariable Long id) {
        return service.getCourseById(id);
    }

    //POST
    @PostMapping(path = "/course/api")
    public void addNewCourse(@RequestBody Course c) {
        service.addNewCourse(c);
    }

    //DELETE
    @DeleteMapping(path = "/course/delete/{id}")
    public void deleteCourse(@PathVariable("id") Long id) {
        service.deleteCourse(id);
    }
}
