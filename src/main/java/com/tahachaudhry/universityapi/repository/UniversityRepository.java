package com.tahachaudhry.universityapi.repository;

import com.tahachaudhry.universityapi.model.University;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UniversityRepository extends JpaRepository<University, Long>{
    
}