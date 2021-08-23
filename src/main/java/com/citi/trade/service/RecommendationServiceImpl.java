package com.citi.trade.service;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import com.citi.trade.model.StockDetails;

import yahoofinance.Stock;
import yahoofinance.YahooFinance;
import yahoofinance.histquotes.Interval;

import com.citi.trade.util.SectorCompanyList;
@Service
public class RecommendationServiceImpl implements RecommendationService {  
	int noofDays = -3;
	Calendar from = Calendar.getInstance();
	Calendar to = Calendar.getInstance();
	BigDecimal crore = new BigDecimal(10000000);
	Map<String, List<String>> sectorWiseCompany = SectorCompanyList.sectorWiseCompany();

	public List<StockDetails> getRecommendation(String sector, String parameter) 
	{

		from.add(Calendar.WEEK_OF_MONTH, noofDays);
		List<StockDetails> recommendedStocks = new ArrayList<>();
		try 
		{
			List<String> companies = sectorWiseCompany.get(sector);

			String[] tmp = new String[companies.size()];
			for (int i = 0; i < tmp.length; i++)
				tmp[i] = companies.get(i); //get stock symbols

				Map<String, Stock> stocks = YahooFinance.get(tmp);
				List<Stock> sortedStocks = new ArrayList<>(stocks.values());

				if (!ObjectUtils.isEmpty(sortedStocks)) 
				{
					sortedStocks.forEach(stock -> {
						StockDetails stockDetail = new StockDetails();
						stockDetail.setCompanyName(stock.getName());
						if(stock.getQuote().getPrice()!=null)
						{
							stockDetail.setCurrentPrice(stock.getQuote().getPrice());
						}else
						{
							stockDetail.setCurrentPrice(BigDecimal.valueOf(0));
						}
						if((stock.getStats().getMarketCap()).divide(crore, 2, RoundingMode.HALF_UP)!=null)
						{
							stockDetail.setMarketCap((stock.getStats().getMarketCap()).divide(crore, 2, RoundingMode.HALF_UP));
						}else
						{
							stockDetail.setMarketCap(BigDecimal.valueOf(0));
						}
						if(stock.getQuote().getChangeInPercent()!=null)
						{
							stockDetail.setPercentageChange(stock.getQuote().getChangeInPercent());
						}else
						{
							stockDetail.setPercentageChange(BigDecimal.valueOf(0));
						}
						if(stock.getStats().getPe()!=null)
						{
							stockDetail.setPe(stock.getStats().getPe());
						}else
						{
							stockDetail.setPe(BigDecimal.valueOf(0));
						}
						if(stock.getStats().getEps()!=null)
						{
							stockDetail.setEps(stock.getStats().getEps());
						}else
						{
							stockDetail.setEps(BigDecimal.valueOf(0));
						}
						if(stock.getQuote().getPreviousClose()!=null)
						{
							stockDetail.setClose(stock.getQuote().getPreviousClose());
						}else
						{
							stockDetail.setClose(BigDecimal.valueOf(0));
						}
						if(stock.getDividend().getAnnualYield()!=null)
						{
							stockDetail.setDividends(stock.getDividend().getAnnualYield());
						}else
						{
							stockDetail.setDividends(BigDecimal.valueOf(0));
						}
						if(stock.getQuote().getVolume()!=null)
						{
							stockDetail.setVolume(stock.getQuote().getVolume());
						}else
						{
							stockDetail.setVolume((long) 0);
						}
						if(stock.getQuote().getChangeFromAvg50InPercent()!=null)
						{
							stockDetail.setGrowth(stock.getQuote().getChangeFromAvg50InPercent());
						}else
						{
							stockDetail.setGrowth(BigDecimal.valueOf(0));
						}
						recommendedStocks.add(stockDetail);

					});
					if ("Growth".equalsIgnoreCase(parameter)) 
					{ 
						recommendedStocks.sort((s1, s2) -> s2.getGrowth().compareTo(s1.getGrowth()));
					} 
					else if ("Volume".equalsIgnoreCase(parameter)) 
					{ 
						recommendedStocks.sort((s1, s2) -> s2.getVolume().compareTo(s1.getVolume()));
					} 
					else if ("Dividends".equalsIgnoreCase(parameter)) 
					{ 
						recommendedStocks.sort((s1, s2) -> s2.getDividends().compareTo(s1.getDividends()));
					} 
					else if ("PE".equalsIgnoreCase(parameter)) 
					{ 
						recommendedStocks.sort((s1, s2) -> s2.getPe().compareTo(s1.getPe()));
					}
					else if ("Eps".equalsIgnoreCase(parameter)) 
					{ 
						recommendedStocks.sort((s1, s2) -> s2.getEps().compareTo(s1.getEps()));
					} 
					else if ("movingAverage".equalsIgnoreCase(parameter)) 
					{ 
						int counter =0;
						for (Stock stock : sortedStocks) {
							for (int i = 0; i < 14; ++i) {
								BigDecimal ma = new BigDecimal(0);
								BigDecimal n = new BigDecimal(14);
								try {
									ma = ma.add(stock.getHistory(from, to, Interval.DAILY).get(i).getClose());
									ma = ma.divide(n, 2, RoundingMode.HALF_UP);
									recommendedStocks.get(counter).setMovingAverage(ma);

								} catch (IOException e) {
									e.printStackTrace();
								}
							}
							counter++;
						}
						recommendedStocks.sort((s1, s2) -> s2.getClose().subtract(s2.getMovingAverage()).compareTo(s1.getClose().subtract(s1.getMovingAverage())));
					} 
					// default case
					else 
					{ 
						recommendedStocks.sort((s1, s2) -> s2.getGrowth().compareTo(s1.getGrowth()));
					}
				} 
				else 
				{
					System.out.print("Unable to fetch data from yahoo api.");
				}
		} 
		catch (IOException e1) 
		{
			e1.printStackTrace();
		}


		return recommendedStocks;


	}
}