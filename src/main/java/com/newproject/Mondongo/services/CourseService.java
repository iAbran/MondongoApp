package com.newproject.Mondongo.services;

import com.newproject.Mondongo.dto.CourseDTO;
import com.newproject.Mondongo.entities.Course;
import com.newproject.Mondongo.exceptions.MyCustomApiException;
import com.newproject.Mondongo.repositories.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CourseService {
    private final CourseRepository repository;

    public CourseService(CourseRepository repository) {
        this.repository = repository;
    }

    //GET ALL COURSES
    public List<CourseDTO> getCourses() {
        return convertData(repository.findAll());
    }

    //GET COURSE BY ID
    public CourseDTO getCourseById(Long id) {
        return repository.findById(id)
                .map(c -> new CourseDTO(
                        c.getId(),
                        c.getTitle(), c.getDescription(),
                        c.getTime(), c.getReleased(),
                        c.getRating(), c.getClientSize()))
                .orElseThrow(() -> new MyCustomApiException("No course with id: "+id+" was found"));
    }

    //ADD NEW COURSE
    public void addNewCourse(Course c) {
        Optional<Course> courseOptional = repository.findByUniqueCode(c.getUniqueCode());

        if (courseOptional.isPresent()) {
            throw new MyCustomApiException("Unique Code is not available");
        }
        repository.save(c);
    }

    //DELETE COURSE BY ID
    public void deleteCourse(Long id) {
        boolean exists = repository.existsById(id);
        if (!exists) {
            throw new MyCustomApiException("Course with id "+id+" was not found" );
        }
        repository.deleteById(id);
    }

    //Convert List of Courses into List of CourseDto
    private List<CourseDTO> convertData(List<Course> courses) {
        return courses.stream()
                .map(c -> new CourseDTO(c.getId(),
                        c.getTitle(), c.getDescription(),
                        c.getTime(), c.getReleased(),
                        c.getRating(), c.getClientSize()))
                .collect(Collectors.toList());
    }
}
