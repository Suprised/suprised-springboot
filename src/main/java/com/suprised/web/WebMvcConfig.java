package com.suprised.web;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 配置类，增加自定义的MVC配置，保留springboot的默认配置。
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/xx").setViewName("/xx");
	}

	
	/**
	 * 使用内嵌servlet容器，注册Servlet,Filter,Listener
	 * 
	 * 有两种方式：
	 * 1,直接注册Spring Bean
	 * 2,或者注册对应的RegistrationBean，例如： ServletRegistrationBean, FilterRegistrationBean
	 */
	
}
