package org.techweb.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Image {
	@Id @GeneratedValue
	private Long idImage;
	
	@Column(length=500000)
	private byte[] image;
	private Long houseId;

	public Image(byte[] images, Long houseId) {
		this.setImage(images);
		this.setHouseId(houseId);
	}
	
	public Image() {
		super();
	}


	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}



	public Long getHouseId() {
		return houseId;
	}

	public void setHouseId(Long houseId) {
		this.houseId = houseId;
	}
}
