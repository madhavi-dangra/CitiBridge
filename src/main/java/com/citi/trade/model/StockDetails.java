package com.citi.trade.model;
import java.math.BigDecimal;
import lombok.Data;

@Data
public class StockDetails {
	
	
	private Long volume;
	private BigDecimal dividends;
	private BigDecimal pe;
	private BigDecimal marketCap;
	private BigDecimal currentPrice;
	private BigDecimal growth;
	private String companyName;
	private BigDecimal percentageChange;
	private String sector;
	private BigDecimal eps ; //Earnings Per Share
	private String symbol;
	private BigDecimal movingAverage;
	private BigDecimal close;
	
	public BigDecimal getClose() {
		return close;
	}
	public void setClose(BigDecimal close) {
		this.close = close;
	}

	
	public BigDecimal getMovingAverage() {
		return movingAverage;
	}
	public void setMovingAverage(BigDecimal movingAverage) {
		this.movingAverage = movingAverage;
	}
	
	public void setEps(BigDecimal eps)
	{
		this.eps = eps ;
	}
	
	public BigDecimal getEps()
	{
		return eps ;
	}

	public String getSector() {
		return sector;
	}
	public void setSector(String sector) {
		this.sector = sector;
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
	public BigDecimal getDividends() {
		return dividends;
	}
	public void setDividends(BigDecimal dividends) {
		this.dividends = dividends;
	}
	public BigDecimal getPe() {
		return pe;
	}
	public void setPe(BigDecimal pe) {
		this.pe = pe;
	}
	public BigDecimal getMarketCap() {
		return marketCap;
	}
	public void setMarketCap(BigDecimal marketCap) {
		this.marketCap = marketCap;
	}
	
	public BigDecimal getCurrentPrice() {
		return currentPrice;
	}
	public void setCurrentPrice(BigDecimal currentPrice) {
		this.currentPrice = currentPrice;
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
	public void setSymbol(String symbol) {
		this.symbol = symbol ;
		
	}
	
	public String getSymbol() {
		return symbol;
	}
	
	
	
}