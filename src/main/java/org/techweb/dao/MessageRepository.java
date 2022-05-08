package org.techweb.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.techweb.entities.Message;

public interface MessageRepository extends JpaRepository<Message, Long> {
	@Query("select m from Message m where m.sender = :x and m.recipient = :y order by timeStamp")
	public List<Message> findMessageWithSenderAndRecipient(@Param("x")String sender, @Param("y")String recipient);
	
	@Query("select distinct recipient from Message m where m.sender = :x")
	public List<String> findRecipients(@Param("x")String sender);
	
	@Query("select distinct sender from Message m where m.recipient = :x")
	public List<String> findSenders(@Param("x")String recipient);
}
