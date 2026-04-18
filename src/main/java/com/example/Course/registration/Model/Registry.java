package com.example.Course.registration.Model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import jakarta.persistence.*;

@Entity
@Data  // Only for getters/setters, not constructors
public class Registry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    @Email
    @NotBlank
    private String email;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "courseid", referencedColumnName = "courseid")
    private Course course;

    // No-args constructor (required by JPA)
    public Registry() {
    }

    // Constructor matching your Courseservice usage: new Registry(name, course, email)
    public Registry(String name, Course course, String email) {
        this.name = name;
        this.email = email;
        this.course = course;
    }

    // Full constructor with ID (for other uses)
    public Registry(Long id, String name, String email, Course course) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.course = course;
    }
}