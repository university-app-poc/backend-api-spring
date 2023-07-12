package com.tahachaudhry.universityapi.controller;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tahachaudhry.universityapi.service.UniversityService;
import com.tahachaudhry.universityapi.exception.BadRequestException;
import com.tahachaudhry.universityapi.exception.ResourceNotFoundException;
import com.tahachaudhry.universityapi.model.University;
import com.tahachaudhry.universityapi.repository.UniversityRepository;

@RestController
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
	public ResponseEntity<University> getUniversityById(@PathVariable(value = "id") Long universityId) throws ResourceNotFoundException {
		try {
			Objects.requireNonNull(universityService.getUniversity(universityId));

			return ResponseEntity.ok(universityService.getUniversity(universityId));
		} catch (NullPointerException e) {
			throw new ResourceNotFoundException("University not found");
		}
	}

	@PostMapping("/university")
	@ResponseBody
	public ResponseEntity<University> createUniversity(@RequestBody University university) throws BadRequestException {
		if (university.getName() == null) throw new BadRequestException("University Name is required");
	
		if (university.getAddress() == null) throw new BadRequestException("University Address is required");
		
		if (university.getContact() == null) throw new BadRequestException("University Contact information is required");
		if (university.getContact().getEmail() == null) throw new BadRequestException("University Contact Email is required");
		if (university.getContact().getPhone() == null) throw new BadRequestException("University Contact Phone Number is required");

		final University addedUniversity = universityService.addUniversity(university);
		return ResponseEntity.ok(addedUniversity);
	}

	@PutMapping("/university/{id}")
	@ResponseBody
	public ResponseEntity<University> updateUniversity(@PathVariable(value = "id") Long universityId, @RequestBody University universityDetails) throws ResourceNotFoundException, BadRequestException {
		University university = universityService.getUniversity(universityId);
		
		if (university == null) throw new ResourceNotFoundException("University not found");

		if (universityDetails.getName() == null) throw new BadRequestException("University Name is required");
	
		if (universityDetails.getAddress() == null) throw new BadRequestException("University Address is required");
		
		if (universityDetails.getContact() == null) throw new BadRequestException("University Contact information is required");
		if (universityDetails.getContact().getEmail() == null) throw new BadRequestException("University Contact Email is required.");
		if (universityDetails.getContact().getPhone() == null) throw new BadRequestException("University Contact Phone Number is required");
		

		university.setName(universityDetails.getName());
		university.setAddress(universityDetails.getAddress());
		university.setContact(universityDetails.getContact());
		final University updatedUniversity = universityRepository.save(university);
		return ResponseEntity.ok(updatedUniversity);
	}

	@DeleteMapping("/university/{id}")
	@ResponseBody
	public ResponseEntity<Boolean> deleteUniversity(@PathVariable(value = "id") Long universityId) throws ResourceNotFoundException {
		try {
			Objects.requireNonNull(universityService.getUniversity(universityId));

			return ResponseEntity.ok(universityService.delete(universityId));
		} catch (NullPointerException e) {
			throw new ResourceNotFoundException("University not found");
		}
	}
}