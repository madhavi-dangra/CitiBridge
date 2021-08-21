package com.citi.trade.service;

import java.util.List;

import com.citi.trade.model.UserHistory;

//import antlr.collections.List;

public interface UserHistoryService {
	 public List<UserHistory> findAll();
	    public void save(UserHistory userInfo);
	    public void deleteById(Long Id);
	    public UserHistory findById(Long Id);
	   
}
