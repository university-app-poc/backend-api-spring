package com.tahachaudhry.universityapi.model;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "university")
public class University {
	private @Id @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "university_generator") Long id;
    private String name;
    private String address;
	@Embedded
	private Contact contact;

	public University() {

	}
	
	public University(String name, String address) {
		this.name = name;
		this.address = address;
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
    
    @Column(name = "address", nullable = false)
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}

	public Contact getContact() {
		return this.contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
	}
}