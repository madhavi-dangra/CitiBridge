package com.example.model;
import java.math.BigDecimal;
import lombok.Data;

@Data
public class StockDetails {
	
	
	private Long volume;
	private BigDecimal dividend;
	private BigDecimal pe;
	private BigDecimal roe;
	private BigDecimal marketCap;
	private BigDecimal revenue;
	private BigDecimal currentPrice;
	private BigDecimal twoWeeksPrice;
	private BigDecimal growth;
	private String companyName;
	private BigDecimal percentageChange;
	private BigDecimal bidPrice;
	
	
	public BigDecimal getBidPrice() {
		return bidPrice;
	}
	public void setBidPrice(BigDecimal bidPrice) {
		this.bidPrice = bidPrice;
	}
	public BigDecimal getPercentageChange() {
		return percentageChange;
	}
	public void setPercentageChange(BigDecimal percentageChange) {
		this.percentageChange = percentageChange;
	}
	public Long getVolume() {
		return volume;
	}
	public void setVolume(Long volume) {
		this.volume = volume;
	}
	public BigDecimal getDividend() {
		return dividend;
	}
	public void setDividend(BigDecimal dividend) {
		this.dividend = dividend;
	}
	public BigDecimal getPe() {
		return pe;
	}
	public void setPe(BigDecimal pe) {
		this.pe = pe;
	}
	public BigDecimal getRoe() {
		return roe;
	}
	public void setRoe(BigDecimal roe) {
		this.roe = roe;
	}
	public BigDecimal getMarketCap() {
		return marketCap;
	}
	public void setMarketCap(BigDecimal marketCap) {
		this.marketCap = marketCap;
	}
	public BigDecimal getRevenue() {
		return revenue;
	}
	public void setRevenue(BigDecimal revenue) {
		this.revenue = revenue;
	}
	public BigDecimal getCurrentPrice() {
		return currentPrice;
	}
	public void setCurrentPrice(BigDecimal currentPrice) {
		this.currentPrice = currentPrice;
	}
	public BigDecimal getTwoWeeksPrice() {
		return twoWeeksPrice;
	}
	public void setTwoWeeksPrice(BigDecimal twoWeeksPrice) {
		this.twoWeeksPrice = twoWeeksPrice;
	}
	public BigDecimal getGrowth() {
		return growth;
	}
	public void setGrowth(BigDecimal growth) {
		this.growth = growth;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	
	
	
	
}
