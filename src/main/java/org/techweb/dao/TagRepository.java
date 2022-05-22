package org.techweb.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.techweb.entities.Offer;
import org.techweb.entities.Tag;

public interface TagRepository extends JpaRepository<Tag, Long> {
	@Query("select o from offer o where o.id_offer in select t from tag t where t.id_offer = :x")
	public List<Tag> findOfferOfTags(@Param("x")long offerid);
	
	@Query("select t from tag t where t.id_offer in select o from offer o where o.id_offer = :x")
	public List<Tag> findTagsOfOffer(@Param("x")long offerid);
////	@Query("select o from Tag o where o.entry like :x")
////	public List<Tag> findByName(@Param("x")String offerName);
}
