package com.thymeleaf.demo.rest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/thymeleaf")
public class ThymeleafRestController {

	@GetMapping("/test")
	public String thymeleafRestTest() {
		return "thymeleafresttest/hellothymeleaf";
	}

}
