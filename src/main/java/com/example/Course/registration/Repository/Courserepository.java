package com.example.Course.registration.Repository;

import com.example.Course.registration.Model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface Courserepository extends JpaRepository<Course, String> {

    /**
     * Find courses by trainer name
     */
    List<Course> findByTrainer(String trainer);

    /**
     * Find courses by name (case-insensitive)
     */
    List<Course> findByCoursename(String coursename);

    /**
     * Find courses with minimum duration
     */
    List<Course> findByDurationGreaterThanEqual(int duration);
}
