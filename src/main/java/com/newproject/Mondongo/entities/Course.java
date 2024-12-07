package com.newproject.Mondongo.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table(name = "courses")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private String time;
    private LocalDate released;
    private Double rating;
    private Integer clientSize;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    @JsonBackReference
    private Author author;

    @JsonIgnore
    @ManyToMany(mappedBy = "courses")
    private List<Client> clients;

    @JsonIgnore
    @Column(unique = true)
    @Size(max = 4, min = 4, message = "unique code most be 4 digit long")
    private String uniqueCode;

    public Integer getClientSize() {
        return clients != null ? clients.size() : 0;
    }
}