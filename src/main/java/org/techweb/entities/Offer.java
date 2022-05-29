package org.techweb.entities;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class Offer {
	@Id @GeneratedValue
	private Long idOffer;
	@Column(length=100)
	private long houseId;
	private long begin;
	private long end;
	private boolean validate;
	private String[] equipments;
	private String[] services;
	private String[] constraints;
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name="id_offer")
	private Set<Tag> tags;

	public Offer(long houseId, String begin, String end, String[] equipments, String[] services, String[] constraints) {
		setValidate(false);
		this.setHouseId(houseId);
		this.setBegin(begin);
		this.setEnd(end);
		this.setEquipments(equipments);
		this.setServices(services);
		this.setConstraints(constraints);
		this.tags = new HashSet<Tag>();
	}
	
	public Offer(){
		super();
		this.tags = new HashSet<Tag>();
	}
	
	public long getHouseId() {
		return houseId;
	}

	public void setHouseId(long houseId) {
		this.houseId = houseId;
	}

	public Long getIdOffer() {
		return idOffer;
	}

	public void setIdOffer(Long idOffer) {
		this.idOffer = idOffer;
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

	public String getBegin() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date beginDate = new Date(begin);
		return format.format(beginDate);
	}

	public void setBegin(String begin) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		try {
		    Date beginDate = format.parse(begin);
		    this.begin = beginDate.getTime();
		} catch (ParseException e) {
		    e.printStackTrace();
		}
	}

	public String getEnd() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date endDate = new Date(end);
		return format.format(endDate);
	}

	public void setEnd(String end) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		try {
		    Date endDate = format.parse(end);
		    this.end = endDate.getTime();
		} catch (ParseException e) {
		    e.printStackTrace();
		}
	}
	
}
