package com.example.service;
import java.util.List;
import com.example.repo.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.*;

@Service
public class UserHistoryService {

	@Autowired
    private UserHistoryRepository repository;
	
	
	
	public UserHistoryService() {
    }

    public UserHistory saveUserHistory(UserHistory s) {
        return repository.save(s);
    }

    public List<UserHistory> getSavedStocks(String userName) {
        return repository.findByUserName(userName);
    }

    public List<UserHistory> getAllSavedStocksById(long user_id)
    {
    	return repository.findByUserId(user_id);
    }
    
    public List<UserHistory> getAllSavedStocks()
    {
    	return repository.findAll();
    }
    
    
}
