package com.citi.trade.service;

import java.util.List;

import com.citi.trade.model.UserHistory;

//import antlr.collections.List;

public interface UserHistoryService {
	 public List<UserHistory> findAll();
	    public void save(UserHistory userInfo); //to save userHistory
	    public List<UserHistory> findByUserName(String userName); //find user by username
	   
}
