package com.newproject.Mondongo.dto;

import java.time.LocalDate;

public record CourseDTO(
        Long id,
        String title,
        String description,
        String time,
        LocalDate released,
        Double rating,
        Integer clientSize
) {
}
