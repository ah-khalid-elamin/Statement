package com.nagarro.assessments.controllers;

import java.util.Date;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api/statement")
public class StatementController {

	
	@GetMapping()
	public String GetAccountStatement(@RequestParam Date fromDate, 
			@RequestParam Date toDate) {
		return "Response from ping controller.";
	}
}
