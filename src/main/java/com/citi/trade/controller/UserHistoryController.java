package com.citi.trade.controller;

import com.citi.trade.model.UserHistory;
import com.citi.trade.service.UserHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user-history")
@Component
public class UserHistoryController {

	@Autowired
	public UserHistoryService userHistoryService;

	//    @CrossOrigin(origins = "*", allowedHeaders = "*")
	//    @GetMapping("/getHistory")
	//    public List<UserHistory> getHistory(@RequestParam("userName") String userName) {
	//        return userHistoryService.getSavedStocks(userName);
	//    }

	//@CrossOrigin(origins = "*", allowedHeaders = "*")
	// @PostMapping("/saveHistory")
	// public boolean saveHistory(@RequestBody UserHistory history) {
	//return userHistoryService.saveUserHistory(history);
	// }

	public UserHistoryController(UserHistoryService theuserHistoryService){
		userHistoryService = theuserHistoryService;
	}

	@PostMapping("/userHistory")
	private UserHistory add(@RequestBody UserHistory userinfo)
	{
		userHistoryService.save(userinfo);

		return userinfo;

	}
	@GetMapping("/userHistory")
	private List<UserHistory> findAll()
	{
		return userHistoryService.findAll();

	}



	@DeleteMapping("/userHistory/{Id}")
	public String deleteWatchlist(@PathVariable Long Id){
		UserHistory temp = userHistoryService.findById(Id);

		if(temp == null)
		{
			throw new RuntimeException("Watchlist not found" + Id);

		}
		userHistoryService.deleteById(Id);
		return "Deleted Id " + Id;
	}
}