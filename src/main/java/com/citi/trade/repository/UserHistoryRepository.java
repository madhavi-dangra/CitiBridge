package com.citi.trade.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.citi.trade.model.*;
@CrossOrigin(origins = "http://localhost:4200",allowedHeaders = "*")
@Repository
public interface UserHistoryRepository extends JpaRepository<UserHistory, Long>{
	
}
