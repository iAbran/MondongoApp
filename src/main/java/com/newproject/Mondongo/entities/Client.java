package com.newproject.Mondongo.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Data
@Entity
@Table(name = "clients")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String lastName;
    @Transient
    private Integer age;
    private LocalDate birthdate;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @Column(unique = true)
    @Size(max = 15, min = 10, message = "phone number most have 10 or 15 digit long")
    private String phoneNumber;
    @Column(unique = true)
    private String email;
    private String address;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinTable(
            name = "client_course",
            joinColumns = @JoinColumn(name = "client_id"),
            inverseJoinColumns = @JoinColumn(name = "course")
    )
    private List<Course> courses;

    public Integer getAge() {
        return Period.between(this.birthdate, LocalDate.now()).getYears();
    }
}
