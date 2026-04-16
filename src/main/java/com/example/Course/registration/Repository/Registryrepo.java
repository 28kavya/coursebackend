package com.example.Course.registration.Repository;

import com.example.Course.registration.Model.Registry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Registryrepo extends JpaRepository<Registry,Long>{
}
