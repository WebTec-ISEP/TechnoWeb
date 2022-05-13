package org.techweb.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.techweb.entities.Offer;
import org.techweb.entities.Trade;
import org.techweb.entities.User;

public interface TradeRepository extends JpaRepository<Trade, Long>{
	@Query("select t from Trade t where t.senderOfferId = :x or t.recipientOfferId = :x")
	public List<Trade> findByOffer(@Param("x")Long offerId);
	
	@Query("select t from Trade t where t.recipientOfferId = :x")
	public List<Trade> findTradeProposal(@Param("x")Long offerId);
	
	@Query("select t from Trade t where t.senderOfferId = :x")
	public List<Trade> findTradeProposed(@Param("x")Long offerId);

}
