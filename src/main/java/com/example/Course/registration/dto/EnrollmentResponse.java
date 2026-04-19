package com.example.Course.registration.dto;

import lombok.Data;

@Data
public class EnrollmentResponse {

    private Long id;
    private String name;
    private String email;
    private String coursename;

    public EnrollmentResponse(Long id, String name, String email, String coursename) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.coursename = coursename;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getCoursename() {
        return coursename;
    }
}
