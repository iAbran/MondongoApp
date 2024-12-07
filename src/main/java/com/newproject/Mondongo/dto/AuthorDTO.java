package com.newproject.Mondongo.dto;

import com.newproject.Mondongo.entities.Course;
import com.newproject.Mondongo.entities.Gender;

import java.time.LocalDate;
import java.util.List;

public record AuthorDTO(
        Long id,
        String name,
        String lastName,
        Integer age,
        LocalDate birthdate,
        Gender gender,
        List<Course> courses
) {
}
