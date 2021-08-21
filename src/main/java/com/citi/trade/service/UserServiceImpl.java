package com.citi.trade.service;
import java.util.Base64;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.citi.trade.model.User;
import com.citi.trade.repository.UserRepository;


@Service
public class UserServiceImpl implements UserService{
	
	
	private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);
	@Autowired
	UserRepository userRepository;
	@Override
	public boolean checkLogin(User userObject) {
		// TODO Auto-generated method stub
		String password = decodeString(userObject.getPassword());
		User temp = userRepository.findUserMasterByUserId(userObject.getUserId());

		if(temp != null) {
			logger.info("User found in database");
		}
		else {
			logger.info("User not found in databae" );
		}

		if(decodeString(temp.getPassword()).equals(password)) {
			logger.info("User Login Successful");
			return true;
		}
		logger.info("User Login UnsSuccessful");
		return false;
	}
	private String decodeString(String encodedPassword) {

		String decoded = new String(Base64.getDecoder().decode(encodedPassword));
		StringBuilder password = new StringBuilder();
		password.append(decoded);
		password = password.reverse();
		return password.toString();
	}
}
