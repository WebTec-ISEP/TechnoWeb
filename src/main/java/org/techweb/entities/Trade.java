package org.techweb.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Trade {
	@Id @GeneratedValue
	private Long idTrade;
	@Column(length=100)
	private Long senderOfferId;
	private Long recipientOfferId;
	private boolean validate;
	
	public Trade(Long senderOffer, Long recipientOffer){
		setValidate(false);
		this.setSenderOffer(senderOffer);
		this.setRecipientOffer(recipientOffer);
	}
	
	public Trade() {
		super();
	}

	public Long getSenderOffer() {
		return senderOfferId;
	}

	public void setSenderOffer(Long senderOffer) {
		this.senderOfferId = senderOffer;
	}

	public Long getRecipientOffer() {
		return recipientOfferId;
	}

	public void setRecipientOffer(Long recipientOffer) {
		this.recipientOfferId = recipientOffer;
	}

	public boolean isValidate() {
		return validate;
	}

	public void setValidate(boolean validate) {
		this.validate = validate;
	}


}