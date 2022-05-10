package org.techweb.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.techweb.entities.Image;

public interface ImageRepository extends JpaRepository<Image, Long> {
	@Query("select i from Image i where i.offerId = :x")
	public List<Image> findByOfferId(@Param("x")Long offerId);
	
}
