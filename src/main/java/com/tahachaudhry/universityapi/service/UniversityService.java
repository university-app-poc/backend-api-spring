package com.tahachaudhry.universityapi.service;

import java.util.List;
import java.util.Optional;

import com.tahachaudhry.universityapi.model.University;
import com.tahachaudhry.universityapi.repository.UniversityRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class UniversityService {

    @Autowired
    private UniversityRepository universityList;
    
    
    public University addUniversity(University university) {
        return universityList.save(university);
    }

    public List<University> getUniversities() {
        return (List<University>)universityList.findAll();
    }

    public University getUniversity(Long universityId) {
        Optional<University> result = universityList.findById(universityId);
        if (result.isPresent()) {
            return result.get();
        } else {
            return null;
        }
    }

    public boolean updateUniversity(University university) {
        try {
            universityList.save(university);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean delete(Long universityId) {
        try {
            universityList.deleteById(universityId);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}