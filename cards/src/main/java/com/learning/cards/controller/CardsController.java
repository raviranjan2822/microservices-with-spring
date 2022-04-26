package com.learning.cards.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.learning.cards.config.CardsServiceConfig;
import com.learning.cards.model.Cards;
import com.learning.cards.model.Customer;
import com.learning.cards.model.Properties;
import com.learning.cards.repository.CardsRepository;

@RestController
public class CardsController {
	
	@Autowired
	private CardsRepository cardsRepository;
	@Autowired
	private CardsServiceConfig config;
	
	@PostMapping(value = "/myCards")
	public List<Cards> getCardDetails(@RequestBody Customer customer){
		List<Cards> cards = cardsRepository.findByCustomerId(customer.getCustomerId());
		if(!cards.isEmpty()) {
			return cards;
				
		}else {
			return null;
		}
	}
	
	@GetMapping("/cards/properties")
	public String getPropertyDetails() throws JsonProcessingException {
		ObjectWriter ow=new ObjectMapper().writer().withDefaultPrettyPrinter();
		Properties p=new Properties(config.getMsg(), config.getBuildVersion(), config.getMailDetails(), config.getActiveBranches());
		String jsonString=ow.writeValueAsString(p);
		return jsonString;
	}

}
