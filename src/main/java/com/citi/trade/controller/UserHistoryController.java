package com.citi.trade.controller;

import com.citi.trade.model.UserHistory;
import com.citi.trade.service.UserHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/userProfile")
@Component
public class UserHistoryController {

	@Autowired
	public UserHistoryService userHistoryService;

	public UserHistoryController(UserHistoryService theuserHistoryService){
		userHistoryService = theuserHistoryService;
	}

	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@PostMapping("/addSavedStocks")
	private UserHistory add(@RequestBody UserHistory userinfo)
	{
		userHistoryService.save(userinfo);

		return userinfo;

	}
	
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@GetMapping("/userHistory")
	private List<UserHistory> findAll()
	{
		return userHistoryService.findAll();

	}
	
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@GetMapping("/getSavedStocks")
	private List<UserHistory> getSavedStocks(@RequestParam("userName") String userName)
	{
		return userHistoryService.findByUserName(userName);

	}
	
}