package uk.co.springboot.course_jdev.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import uk.co.springboot.course_jdev.model.Users;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long>{
	
	@Query(value = "select u from Users u where  upper(trim(u.name)) like %?1%") // %?1% indicate one parameter only
	List<Users> searchByName(String name);

}
