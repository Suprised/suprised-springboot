package com.suprised.base;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *  hello spring boot.
 */
@RestController
@SpringBootApplication// 开启自动配置
public class HelloWorldSpringBoot {
	
	@RequestMapping("/")
	public String index() {
		return "Hello Spring boot.";
	}

	public static void main(String[] args) {
		SpringApplication.run(HelloWorldSpringBoot.class, args);
	}
	
}

