package com.citi.trade.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "User")
public class User {

	@Id
	@Column(name ="userId")
	Long userId;
	
	@Column(name ="password")
	String password;
	
	@Column(name="userName")
	String userName;

	public Long getUserId() {
		return userId;
	}


	public void setUserId(Long userId) {
		this.userId = userId;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Override
	public String toString() {
		return "UserMaster [userId=" + userId + ", password=" + password + "]";
	}


	

	

    
    

}
