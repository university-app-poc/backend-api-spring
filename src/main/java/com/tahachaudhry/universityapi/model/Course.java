package com.tahachaudhry.universityapi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "course")
public class Course {
    private @Id @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "course_generator") Long id;
    private String name;
	private String duration;
	private String description;
	private String entryRequirements;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name="university_id", nullable=false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private University university;
	
    public Course() {

    }

	public Course(String name, University university) {
		this.name = name;
        this.university = university;
	}
	
	public Long getId() {
		return id;
    }	
    
	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(name = "name", nullable = false)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
    }
    
    @Column(name = "university", nullable = false)
	public University getUniversity() {
		return university;
	}
	public void setUniversity(University university) {
		this.university = university;
	}

	public String getDuration() {
		return this.duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getEntryRequirements() {
		return this.entryRequirements;
	}

	public void setEntryRequirements(String entryRequirements) {
		this.entryRequirements = entryRequirements;
	}
}
