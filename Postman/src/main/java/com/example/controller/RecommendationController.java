package com.example.controller;
import com.example.service.*;
import com.example.model.*;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


//@CrossOrigin(origins = "*", allowedHeaders = "*")
//@GetMapping("/fetch_data/{category}")
@Controller
@RequestMapping("/recommendation")
@Component
public class RecommendationController {

	@Autowired
	private RecommendationService recommendationService;
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@GetMapping("/getRecommendation")
	public List<StockDetails> getRecommendation(@RequestParam("sector") String sector, @RequestParam("parameter") String parameter) {
		return recommendationService.getRecommendation(sector, parameter);
	}
	
	
}
