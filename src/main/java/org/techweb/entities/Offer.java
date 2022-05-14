package org.techweb.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Offer {
	@Id @GeneratedValue
	private Long idOffer;
	@Column(length=100)
	private String name;
	private String location;
	private long duration;
	private String description;
	private String owner;
	private boolean validate;
	
	public Offer(String name, String location, long duration, String description, String owner) {
		setValidate(false);
		this.setName(name);
		this.setLocation(location);
		this.setDuration(duration);
		this.setDescription(description);
		this.setOwner(owner);
	}
	
	public Offer(){
		super();
	}
	
	public Long getIdOffer() {
		return idOffer;
	}

	public void setIdOffer(Long idOffer) {
		this.idOffer = idOffer;
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

	public long getDuration() {
		return duration;
	}

	public void setDuration(long duration) {
		this.duration = duration;
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

	public boolean isValidate() {
		return validate;
	}

	public void setValidate(boolean validate) {
		this.validate = validate;
	}
}
