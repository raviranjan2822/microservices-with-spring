package com.learning.loans.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.learning.loans.config.LoansServiceConfig;
import com.learning.loans.model.Customer;
import com.learning.loans.model.Loans;
import com.learning.loans.model.Properties;
import com.learning.loans.repository.LoansRepository;

@RestController
public class LoansController {

	@Autowired
	private LoansRepository loansRepository;
	@Autowired
	private LoansServiceConfig config;

	@PostMapping(value = "/myLoans")
	public List<Loans> getLoansDetails(@RequestBody Customer customer) {
		List<Loans> loans = loansRepository.findByCustomerIdOrderByStartDateDesc(customer.getCustomerId());
		if (!loans.isEmpty()) {
			return loans;
		} else {
			return null;
		}
	}
	@GetMapping("/loans/properties")
	public String getPropertyDetails() throws JsonProcessingException {
		ObjectWriter ow=new ObjectMapper().writer().withDefaultPrettyPrinter();
		Properties p=new Properties(config.getMsg(), config.getBuildVersion(), config.getMailDetails(), config.getActiveBranches());
		String jsonString=ow.writeValueAsString(p);
		return jsonString;
	}

}
