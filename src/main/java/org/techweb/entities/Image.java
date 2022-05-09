package org.techweb.entities;

import java.nio.file.Files;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import org.springframework.web.multipart.MultipartFile;

@Entity
public class Image {
	@Id @GeneratedValue
	private Long idImage;
	
	@Column(length=100)
	private byte[] image;

	private Long offerId;

	public Image(byte[] images, Long offerId) {
		this.setImage(images);
		this.setOfferId(offerId);
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}



	public Long getOfferId() {
		return offerId;
	}

	public void setOfferId(Long offerId) {
		this.offerId = offerId;
	}
}
