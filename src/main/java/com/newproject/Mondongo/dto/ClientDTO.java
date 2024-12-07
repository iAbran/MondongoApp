package com.newproject.Mondongo.dto;

import com.newproject.Mondongo.entities.Course;
import com.newproject.Mondongo.entities.Gender;

import java.time.LocalDate;
import java.util.List;

public record ClientDTO(
        Long id,
        String name,
        String lastName,
        Integer age,
        LocalDate birthdate,
        Gender gender,
        String phoneNumber,
        String email,
        String address,
        List<Course> courses
) {
}
