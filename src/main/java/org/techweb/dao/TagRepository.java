package org.techweb.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.techweb.entities.Offer;
import org.techweb.entities.Tag;

public interface TagRepository extends JpaRepository<Tag, Long> {

	@Query("select o.offer from Tag o where entry in :tags")
	public List<Offer> findOffersMatchingTags(@Param("tags")List<String> tags);
}
