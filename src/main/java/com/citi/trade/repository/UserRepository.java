package com.citi.trade.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.citi.trade.model.User;
@CrossOrigin("http://localhost:4200")
//@RepositoryRestResource(collectionResourceRel = "user",path = "user",exported = false)
@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	

	//User findUserMasterByUserId(String userId);

	User findUserMasterByUserId(Long userId);
	
}
