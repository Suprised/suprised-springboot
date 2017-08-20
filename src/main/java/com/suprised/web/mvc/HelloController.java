package com.suprised.web.mvc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@SpringBootApplication
public class HelloController extends SpringBootServletInitializer {

	@RequestMapping("/index")
	@ResponseBody
	public String hello() {
		return "index";
	}
	
	@Override
	protected SpringApplicationBuilder configure(
			SpringApplicationBuilder builder) {
		return builder.sources(HelloController.class);
	}
	
	public static void main(String[] args) {
		SpringApplication.run(HelloController.class, args);
	}
}
