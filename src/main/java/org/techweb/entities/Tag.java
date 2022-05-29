package org.techweb.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Tag {
	@Id @GeneratedValue
	private long tagId;
	private String type;
	private String entry;
	
	@ManyToOne
	@JoinColumn(name = "id_offer", referencedColumnName = "idOffer", unique=false)
    private Offer offer;
	
	public Tag() {
	}
	public Tag(String type, String entry) {
		this.type = type;
		this.entry = entry;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getEntry() {
		return entry;
	}

	public void setEntry(String entry) {
		this.entry = entry;
	}

	public Offer getOffer() {
		return offer;
	}

	public void setOffer(Offer offer) {
		this.offer = offer;
	}


}
