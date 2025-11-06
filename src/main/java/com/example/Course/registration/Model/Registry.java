package com.example.Course.registration.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
    private Long id;//no need to provide value for id....
    private String name;
    private String email;
    private String coursename;

    public Registry(String name, String coursename, String email) {
        this.name = name;
        this.email = email;
        this.coursename = coursename;
    }
}
