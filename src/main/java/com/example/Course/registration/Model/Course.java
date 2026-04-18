package com.example.Course.registration.Model;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "course")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Course {
    @Id
    private String courseid;

    @NotBlank(message = "Course name cannot be blank")
    private String coursename;

    @NotBlank(message = "Trainer name cannot be blank")
    private String trainer;

    @Min(value = 1, message = "Duration must be at least 1 day")
    private int duration;
}
