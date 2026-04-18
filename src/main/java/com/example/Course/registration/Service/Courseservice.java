package com.example.Course.registration.Service;

import com.example.Course.registration.Model.Course;
import com.example.Course.registration.Model.Registry;
import com.example.Course.registration.Repository.Courserepository;
import com.example.Course.registration.Repository.Registryrepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class Courseservice {

    @Autowired
    private Courserepository courseRepository;

    @Autowired
    private Registryrepo registryRepository;

    /**
     * Get all available courses
     */
    public List<Course> getCourses() {
        return courseRepository.findAll();
    }

    /**
     * Get all enrolled students
     */
    public List<Registry> enrolledlist() {
        return registryRepository.findAll();
    }

    /**
     * Enroll a student in a course
     * @param name Student name
     * @param email Student email
     * @param courseid Course ID to enroll in
     * @throws RuntimeException if course is not found
     */
    @Transactional
    public void enrollCourses(String name, String email, String courseid) {
        // Fetch the course
        Optional<Course> courseOptional = courseRepository.findById(courseid);

        if (courseOptional.isEmpty()) {
            throw new RuntimeException("Course with ID '" + courseid + "' not found");
        }

        Course course = courseOptional.get();

        // Create and save the registry
        Registry registry = new Registry(name, course, email);
        registryRepository.save(registry);
    }

    /**
     * Get enrollments for a specific course
     * @param courseid Course ID
     * @return List of registries for that course
     */
    public List<Registry> getEnrollmentsByCourse(String courseid) {
        return registryRepository.findByCourseId(courseid);
    }

    /**
     * Get enrollments by student email
     * @param email Student email
     * @return List of registries for that email
     */
    public List<Registry> getEnrollmentsByEmail(String email) {
        return registryRepository.findByEmail(email);
    }

    /**
     * Get enrollments by student name (partial match)
     * @param name Student name (partial)
     * @return List of registries matching the name
     */
    public List<Registry> getEnrollmentsByName(String name) {
        return registryRepository.findByNameContainingIgnoreCase(name);
    }

    /**
     * Get a specific enrollment by ID
     * @param registryId Registry ID
     * @return Optional containing the registry if found
     */
    public Optional<Registry> getEnrollmentById(Long registryId) {
        return registryRepository.findById(registryId);
    }

    /**
     * Check if student is enrolled in a specific course
     * @param email Student email
     * @param courseid Course ID
     * @return true if enrolled, false otherwise
     */
    public boolean isStudentEnrolledInCourse(String email, String courseid) {
        List<Registry> enrollments = registryRepository.findByEmailAndCourseId(email, courseid);
        return !enrollments.isEmpty();
    }

    /**
     * Unenroll a student
     * @param registryId Registry ID
     * @throws RuntimeException if registry not found
     */
    @Transactional
    public void unenrollStudent(Long registryId) {
        if (!registryRepository.existsById(registryId)) {
            throw new RuntimeException("Registry with ID '" + registryId + "' not found");
        }
        registryRepository.deleteById(registryId);
    }
}