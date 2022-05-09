package org.techweb.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.techweb.entities.Offer;
import org.techweb.entities.User;

public interface TradeRepository extends JpaRepository<Offer, Long>{
	@Query("select t from Trade t where t.senderOfferId = :x or t.recipientOfferId = :x")
	public User findByOffer(@Param("x")String n);

}
