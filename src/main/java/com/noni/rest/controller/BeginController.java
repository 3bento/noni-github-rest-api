package com.noni.rest.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/test")
public class BeginController {

	@GetMapping
	private String test() {
		return "Hiiiiiiii... :D";
	}
	
}
