package org.techweb.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.techweb.entities.House;

public interface HouseRepository extends JpaRepository<House, Long> {
	@Query("select h from House h where h.owner = :x")
	public List<House> findByOwner(@Param("x")String owner);
	
	@Query("select id from House h where h.owner = :x and h.name = :y")
	public long findIdByOwnerAndName(@Param("x")String owner, @Param("y")String name);
	
	@Query("select h from House h where h.name like :x")
	public List<House> findByName(@Param("x")String name);
}
