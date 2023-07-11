package com.tahachaudhry.universityapi.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tahachaudhry.universityapi.service.UniversityService;
import com.tahachaudhry.universityapi.exception.ResourceNotFoundException;
import com.tahachaudhry.universityapi.model.University;
import com.tahachaudhry.universityapi.repository.UniversityRepository;

@Controller
public class UniversityController {
	
	@Autowired
	private UniversityRepository universityRepository;
	
	@Autowired
	private UniversityService universityService;


	@GetMapping("/universities")
	@ResponseBody
	public List<University> getAllUniversities() {
		return universityService.getUniversities();
	}

	@GetMapping("/university/{id}")
	@ResponseBody
	public University getUniversityById(@PathVariable(value = "id") Long universityId) {
		return universityService.getUniversity(universityId);
	}

	@PostMapping("/university")
	@ResponseBody
	public String createUniversity(@RequestBody University university) {
		if (university.getName() != null && university.getAddress() != null) {
			universityService.addUniversity(university);
			return "Added a university";
		} else {
			return "Request does not contain a body";
		}
	}

	@PutMapping("/university/{id}")
	@ResponseBody
	public ResponseEntity<University> updateUniversity(@PathVariable(value = "id") Long universityId, @RequestBody University universityDetails) throws ResourceNotFoundException {
		University university = universityService.getUniversity(universityId);

		university.setName(universityDetails.getName());
		final University updatedUniversity = universityRepository.save(university);
		return ResponseEntity.ok(updatedUniversity);
	}

	@DeleteMapping("/university/{id}")
	@ResponseBody
	public Map<String, Boolean> deleteUniversity(@PathVariable(value = "id") Long universityId) throws ResourceNotFoundException {

		universityService.delete(universityId);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
}