package com.suprised.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 通过bean获取配置文件中的值，自动注入属性值
 */
@RestController
@SpringBootApplication
public class GetConfigBeanValue {

	@Autowired
	private ConfigBean configBean;
	
	@RequestMapping("/config/")
	public String index() {
		if (configBean != null) {
			return configBean.toString();
		} else {
			throw new RuntimeException("configBean init error.");
		}
	}
	
	public static void main(String[] args) {
		SpringApplication.run(GetConfigBeanValue.class, args);
	}
}

@Component
@ConfigurationProperties(prefix = "get.config", ignoreInvalidFields=true)// 指定前缀
class ConfigBean {

	private String name;
	private String password;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return String.format("从application.yml获取配置值name=%s, password=%s", name, password);
	}
}