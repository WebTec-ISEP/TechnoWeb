package org.techweb.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.techweb.entities.Offer;
import org.techweb.entities.Tag;

public interface TagRepository extends JpaRepository<Tag, Long> {
	@Query("SELECT o FROM Offer o WHERE o.id_offer IN (SELECT t.id_offer FROM Tag t)")
	public List<Tag> findByName(@Param("x")String offerName);
//	@Query("select o from Tag o where o.entry like :x")
//	public List<Tag> findByName(@Param("x")String offerName);
}
