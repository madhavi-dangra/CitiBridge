package com.citi.trade.repository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.citi.trade.model.*;
@CrossOrigin("http://localhost:4200")
@Repository
public interface UserHistoryRepository extends JpaRepository<UserHistory, Long>{
	
	List<UserHistory> findByUserName(String userName);
	@SuppressWarnings("unchecked")
	UserHistory save(UserHistory s);

}
