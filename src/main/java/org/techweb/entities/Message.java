package org.techweb.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Message {
	@Id @GeneratedValue
	private Long idMessage;
	@Column(length=100)
	private String content;
	private String sender;
	private String recipient;
	private long timeStamp;
	
	public Message(String sender, String recipient, long timeStamp, String content){
		this.setRecipient(recipient);
		this.setSender(sender);
		this.setTimeStamp(timeStamp);
		this.setContent(content);
	}
	
	public Message() {
		super();
	}

	public Long getIdMessage() {
		return idMessage;
	}

	public void setIdMessage(Long idMessage) {
		this.idMessage = idMessage;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getRecipient() {
		return recipient;
	}

	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}

	public long getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(long timeStamp) {
		this.timeStamp = timeStamp;
	}

}
