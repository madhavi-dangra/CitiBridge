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
	public User checkLogin(User userObject) 
	{
		
		
		String password = decodeString(userObject.getPassword());
		User temp = null;
		temp = userRepository.findUserMasterByUserName(userObject.getUserName());
		System.out.println("Frontend User name :" +userObject.getUserName());
		
		if(temp != null) 
		{
			System.out.println("Database user name : "+temp.getUserName());
			
			logger.info("User found in database");
			if(temp.getPassword().equals(password)) 
			{
				logger.info("User Login Successful");
				temp.setPassword("");
			} else {
				logger.info("User Login Unsuccessful");
				temp = null;
			}
			
		}
		
		else 
		{
			logger.info("User not found in databae" );
			
		}
		
		return temp;
	}
	
	
	private String decodeString(String encodedPassword) {

		String decoded = new String(Base64.getDecoder().decode(encodedPassword));
		StringBuilder password = new StringBuilder();
		password.append(decoded);
		password = password.reverse();
		return password.toString();
	}
}
