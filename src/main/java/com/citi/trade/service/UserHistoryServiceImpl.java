package com.citi.trade.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.citi.trade.model.UserHistory;
import com.citi.trade.repository.*;


@Service
public class UserHistoryServiceImpl implements UserHistoryService {

	@Autowired
    private UserHistoryRepository userHistoryRepository;
	
	
	@Autowired
	 public  UserHistoryServiceImpl(UserHistoryRepository theuserHistoryRepository)
    {
		userHistoryRepository = theuserHistoryRepository;
    }

	@Override
	public List<UserHistory> findAll() {
		// TODO Auto-generated method stub
		return userHistoryRepository.findAll();
	}

	@Override
	public void save(UserHistory userInfo) {
		// TODO Auto-generated method stub
		userHistoryRepository.save(userInfo);
		
	}

	@Override
	public void deleteById(Long Id) {
		// TODO Auto-generated method stub
		 userHistoryRepository.deleteById(Id);
		
	}

	@Override
	public UserHistory findById(Long Id) {
		// TODO Auto-generated method stub
		Optional<UserHistory> result = userHistoryRepository.findById(Id);
		UserHistory userInfo = null;
		if(result.isPresent()){
			
			userInfo = result.get();
		}
		else{
            //we did not find item in UserHistory
            throw new RuntimeException("Not found");
        }
		return userInfo;
	}

	
    
    
}
