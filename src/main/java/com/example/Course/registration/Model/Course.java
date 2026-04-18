package com.example.Course.registration.Model;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
public class Course {
    @Id
    private String courseid;

    @NotBlank
    private String coursename;

    @NotBlank
    private String trainer;

    @Min(1)
    private int duration;
}

