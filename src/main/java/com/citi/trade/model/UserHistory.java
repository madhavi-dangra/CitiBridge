package com.citi.trade.model;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
@Entity
@Table(name ="user_history")
@Data
@AllArgsConstructor
public class UserHistory {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "save_id")
	private Long Id;
	@Column(name = "userName")
	String userName; 

	@Column(name = "sector")
	String sector; 

	@Column(name = "stockName")
	String stockName;

	@Column(name = "currentPrices")
	BigDecimal currentPrices;

	@Column(name = "quantity")
	int quantity;

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getSector() {
		return sector;
	}

	public void setSector(String sector) {
		this.sector = sector;
	}

	public String getStockName() {
		return stockName;
	}

	public void setStockName(String stockName) {
		this.stockName = stockName;
	}

	public BigDecimal getCurrentPrices() {
		return currentPrices;
	}

	public void setCurrentPrices(BigDecimal currentPrices) {
		this.currentPrices = currentPrices;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}



	




}
