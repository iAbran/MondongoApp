package com.newproject.Mondongo.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Data
@Entity
@Table(name = "author")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String lastName;
    @Transient
    private Integer age;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthdate;
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @JsonIgnore
    @Column(unique = true)
    @Size(max = 4, min = 4, message = "unique code most be 4 digit long")
    private String uniqueCode;

    @JsonManagedReference
    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Course> courses;

    public Integer getAge() {
        return Period.between(this.birthdate, LocalDate.now()).getYears();
    }
}
