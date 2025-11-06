package com.example.Course.registration.Model;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Course {
    @Id
    private  String courseid;
    private  String coursename;
    private String trainer;
    private int duration;
}

