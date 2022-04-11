package org.techweb.dao;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.techweb.entities.User;

public interface UserRepository extends JpaRepository<User, Long>{
	@Query("select u from User u where u.name = :x")
	public User findByName(@Param("x")String n);
}
