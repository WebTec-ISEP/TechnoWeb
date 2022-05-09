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
	private String senderOfferId;
	private String recipientOfferId;
	
	public Trade(String senderOffer, String recipientOffer){
		this.setSenderOffer(senderOffer);
		this.setRecipientOffer(recipientOffer);
	}
	
	public Trade() {
		super();
	}

	public String getSenderOffer() {
		return senderOfferId;
	}

	public void setSenderOffer(String senderOffer) {
		this.senderOfferId = senderOffer;
	}

	public String getRecipientOffer() {
		return recipientOfferId;
	}

	public void setRecipientOffer(String recipientOffer) {
		this.recipientOfferId = recipientOffer;
	}


}