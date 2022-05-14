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
	public List<Trade> findTradesForOffer(@Param("x")Long offerId);
	
	@Query("select t from Trade t where t.recipientOfferId = :x and t.validate=false")
	public List<Trade> findPendingTradeProposal(@Param("x")Long offerId);
	
	@Query("select t from Trade t where t.senderOfferId = :x and t.validate=false")
	public List<Trade> findPendingTradeProposed(@Param("x")Long offerId);
	
	@Query("select t from Trade t where t.senderOfferId = :x and t.recipientOfferId = :y")
	public Trade findBySenderAndRecipientOffers(@Param("x")Long senderOfferId,@Param("y")Long recipientOfferId);
	
	@Query("select t from Trade t where (t.senderOfferId = :x or t.recipientOfferId = :x) and t.validate=true")
	public Trade findAcceptedOffer(@Param("x")Long offerId);

}
