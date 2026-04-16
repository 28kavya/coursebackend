package com.example.Course.registration.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Registry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;

    // Create a foreign key relationship to Course
    @ManyToOne
    @JoinColumn(name = "courseid", referencedColumnName = "courseid")
    private Course course;

    public Registry(String name, Course course, String email) {
        this.name = name;
        this.email = email;
        this.course = course;
    }
}
