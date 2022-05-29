package org.techweb.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.techweb.entities.Offer;

public interface OfferRepository extends JpaRepository<Offer, Long> {
	@Query("select o from Offer o where o.houseId = :x")
	public List<Offer> findByHouseId(@Param("x")Long houseId);
	
	@Query("select o from Offer o where o.houseId = :x and o.validate = :y")
	public List<Offer> findByHouseIdAndValidation(@Param("x")Long houseId, @Param("y")boolean validate);
	
}
