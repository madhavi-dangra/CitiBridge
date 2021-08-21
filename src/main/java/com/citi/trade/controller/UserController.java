package com.citi.trade.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.citi.trade.model.User;
import com.citi.trade.service.UserService;
@RestController
@RequestMapping("/userlogin")
@CrossOrigin(origins="http://localhost:4200")
public class UserController {
	
	@Autowired
	UserService service;
	
	//private static final Logger logger = LogManager.getLogger(UserController.class);
	
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@PostMapping("/login")
	public boolean UserLogin(@RequestBody User userObject ) {
		
//		logger.info("Validating login for User  {}",userObject.getUserId());
		return service.checkLogin(userObject);
		
		
	}

}
