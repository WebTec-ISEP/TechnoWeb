package org.techweb.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.techweb.entities.Offer;

public interface OfferRepository extends JpaRepository<Offer, Long> {
	@Query("select o from Offer o where o.name like :x")
	public List<Offer> findByName(@Param("x")String offerName);
	
	@Query("select o from Offer o where o.location = :x")
	public List<Offer> findByLocation(@Param("x")String offerLocation);
	
	@Query("select o from Offer o where o.owner = :x and o.validate= :y")
	public List<Offer> findByOwnerAndValidation(@Param("x")String offerOwner, @Param("y")Boolean validate);
	
	@Query("select id from Offer o where o.owner = :x and o.name = :y")
	public long findByOwnerAndName(@Param("x")String offerOwner,@Param("y")String offerName);
}
