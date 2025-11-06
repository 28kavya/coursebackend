package com.example.Course.registration.Service;

import com.example.Course.registration.Model.Course;
import com.example.Course.registration.Model.Registry;
import com.example.Course.registration.Repository.Courserepository;
import com.example.Course.registration.Repository.Registryrepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Courseservice {
    @Autowired
    Courserepository cr;
    @Autowired
    Registryrepo regrepo;
    public List<Course> getCourses(){
        return cr.findAll();
    }

    public List<Registry> enrolledlist() {
        return regrepo.findAll();
    }

    public void enrollCourses(String name, String email, String coursename) {
        Registry r = new Registry(name,coursename,email);
        regrepo.save(r);
    }

}
