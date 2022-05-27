package org.techweb.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class House {
	@Id @GeneratedValue
	private Long idHouse;
	
	@Column(length=100)
	private String name;
	private String location;
	private String description;
	private String owner;
	private int rate;
	
	public House(String name, String location, String description, String owner) {
		this.name = name;
		this.location = location;
		this.description = description;
		this.owner = owner;
		this.rate = -1;
	}
	
	public House() {
		super();
		this.rate = -1;
	}
	
	public Long getIdHouse() {
		return idHouse;
	}

	public void setIdHouse(Long idHouse) {
		this.idHouse = idHouse;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	public int getRate() {
		return rate;
	}
	public void setRate(int rate) {
		this.rate = rate;
	}
}
