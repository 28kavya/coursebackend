package com.example.Course.registration.Repository;

import com.example.Course.registration.Model.Registry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

@Repository
public interface Registryrepo extends JpaRepository<Registry, Long> {

    /**
     * Find all enrollments for a specific course
     * Using JPQL query with proper relationship navigation
     */
    @Query("SELECT r FROM Registry r WHERE r.course.courseid = :courseid")
    List<Registry> findByCourseId(@Param("courseid") String courseid);

    /**
     * Find all enrollments by student email
     */
    List<Registry> findByEmail(String email);

    /**
     * Find all enrollments by student name (case-insensitive)
     */
    List<Registry> findByNameContainingIgnoreCase(String name);
    /**
     * Find enrollment by email and course
     */
    @Query("SELECT r FROM Registry r WHERE r.email = :email AND r.course.courseid = :courseid")
    List<Registry> findByEmailAndCourseId(@Param("email") String email, @Param("courseid") String courseid);
}