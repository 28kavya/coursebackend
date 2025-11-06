package com.example.Course.registration.Repository;

import com.example.Course.registration.Model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Courserepository extends JpaRepository<Course,String> {
}
