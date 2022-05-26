package org.techweb.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

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
	private String[] equipments;
	private String[] services;
	private String[] constraints;
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name="id_offer")
	private Set<Tag> tags;

	public Offer(String name, String location, long duration, String description, String owner, String[] equipments, String[] services, String[] constraints) {
		setValidate(false);
		this.setName(name);
		this.setLocation(location);
		this.setDuration(duration);
		this.setDescription(description);
		this.setOwner(owner);
		this.setEquipments(equipments);
		this.setServices(services);
		this.setConstraints(constraints);
		this.tags = new HashSet<Tag>();
	}
	
	public Offer(){
		super();
		this.tags = new HashSet<Tag>();
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

	public String[] getEquipments() {
		return equipments;
	}

	public void setEquipments(String[] equipments) {
		this.equipments = equipments;
	}

	public String[] getServices() {
		return services;
	}

	public void setServices(String[] services) {
		this.services = services;
	}

	public String[] getConstraints() {
		return constraints;
	}

	public void setConstraints(String[] constraints) {
		this.constraints = constraints;
	}
	public Set<Tag> getTags() {
		return tags;
	}

	public void addTags(Tag tag) {
		tags.add(tag);
	}
	
	public void setTags(Set<Tag> tags) {
		this.tags = tags;
	}

	
}
