package org.techweb.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.techweb.entities.Message;
import org.techweb.entities.User;

public interface MessageRepository extends JpaRepository<Message, Long> {
	@Query("select m from Message m where m.sender = :x or m.recipient = :x")
	public List<Message> findByUser(@Param("x")String u);
}
