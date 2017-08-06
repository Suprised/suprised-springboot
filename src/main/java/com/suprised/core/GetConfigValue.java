package com.suprised.core;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 自动获取配置文件的值
 */
@RestController
@SpringBootApplication
public class GetConfigValue {

	@Value("${get.config.name}")
	private String name;
	
	@Value("${get.config.password}")
	private String password;
	
	@RequestMapping("/")
	public String index() {
		return String.format("application.yml配置文件中的用户名：%s, 密码：%s", name, password);
	}
	
	public static void main(String[] args) {
		SpringApplication.run(GetConfigValue.class, args);
	}
}
