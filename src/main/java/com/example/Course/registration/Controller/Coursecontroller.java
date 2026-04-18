package com.example.Course.registration.Controller;

import com.example.Course.registration.Model.Course;
import com.example.Course.registration.Model.Registry;
import com.example.Course.registration.Service.Courseservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")  // Allow requests from frontend
public class Coursecontroller {

    @Autowired
    private Courseservice courseservice;

    /**
     * GET /api/courses
     * Get all available courses
     */
    @GetMapping("/courses")
    public ResponseEntity<List<Course>> getAllCourses() {
        try {
            List<Course> courses = courseservice.getCourses();
            return ResponseEntity.ok(courses);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    /**
     * POST /api/enroll
     * Enroll a student in a course
     * Request body: { "name": "John", "email": "john@example.com", "courseid": "CS101" }
     */
    @PostMapping("/enroll")
    public ResponseEntity<Map<String, String>> enrollStudent(
            @RequestParam String name,
            @RequestParam String email,
            @RequestParam String courseid) {

        Map<String, String> response = new HashMap<>();

        try {
            courseservice.enrollCourses(name, email, courseid);
            response.put("status", "success");
            response.put("message", "Student enrolled successfully");
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (RuntimeException e) {
            response.put("status", "error");
            response.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        } catch (Exception e) {
            response.put("status", "error");
            response.put("message", "Internal server error: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    /**
     * GET /api/enrollments
     * Get all student enrollments
     */
    @GetMapping("/enrollments")
    public ResponseEntity<List<Registry>> getAllEnrollments() {
        try {
            List<Registry> enrollments = courseservice.enrolledlist();
            return ResponseEntity.ok(enrollments);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    /**
     * GET /api/enrollments/course/{courseid}
     * Get all students enrolled in a specific course
     */
    @GetMapping("/enrollments/course/{courseid}")
    public ResponseEntity<List<Registry>> getEnrollmentsByCourse(
            @PathVariable String courseid) {
        try {
            List<Registry> enrollments = courseservice.getEnrollmentsByCourse(courseid);
            if (enrollments.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
            return ResponseEntity.ok(enrollments);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    /**
     * GET /api/enrollments/email/{email}
     * Get all courses enrolled by a specific student (by email)
     */
    @GetMapping("/enrollments/email/{email}")
    public ResponseEntity<List<Registry>> getEnrollmentsByEmail(
            @PathVariable String email) {
        try {
            List<Registry> enrollments = courseservice.getEnrollmentsByEmail(email);
            if (enrollments.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
            return ResponseEntity.ok(enrollments);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    /**
     * GET /api/enrollments/name/{name}
     * Search students by name (partial match)
     */
    @GetMapping("/enrollments/name/{name}")
    public ResponseEntity<List<Registry>> getEnrollmentsByName(
            @PathVariable String name) {
        try {
            List<Registry> enrollments = courseservice.getEnrollmentsByName(name);
            if (enrollments.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
            return ResponseEntity.ok(enrollments);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    /**
     * GET /api/enrollments/{registryId}
     * Get a specific enrollment by ID
     */
    @GetMapping("/enrollments/{registryId}")
    public ResponseEntity<Registry> getEnrollmentById(
            @PathVariable Long registryId) {
        try {
            var enrollment = courseservice.getEnrollmentById(registryId);
            if (enrollment.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
            return ResponseEntity.ok(enrollment.get());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    /**
     * DELETE /api/enrollments/{registryId}
     * Unenroll a student (remove enrollment)
     */
    @DeleteMapping("/enrollments/{registryId}")
    public ResponseEntity<Map<String, String>> unenrollStudent(
            @PathVariable Long registryId) {

        Map<String, String> response = new HashMap<>();

        try {
            courseservice.unenrollStudent(registryId);
            response.put("status", "success");
            response.put("message", "Student unenrolled successfully");
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            response.put("status", "error");
            response.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        } catch (Exception e) {
            response.put("status", "error");
            response.put("message", "Internal server error: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    /**
     * GET /api/check-enrollment
     * Check if a student is enrolled in a specific course
     */
    @GetMapping("/check-enrollment")
    public ResponseEntity<Map<String, Object>> checkEnrollment(
            @RequestParam String email,
            @RequestParam String courseid) {

        Map<String, Object> response = new HashMap<>();

        try {
            boolean isEnrolled = courseservice.isStudentEnrolledInCourse(email, courseid);
            response.put("email", email);
            response.put("courseid", courseid);
            response.put("enrolled", isEnrolled);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}
