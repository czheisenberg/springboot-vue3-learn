package com.ctfer.wiki;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.ctfer.wiki.mapper")
public class WikiApplication {

	public static void main(String[] args) {
		SpringApplication.run(WikiApplication.class, args);
	}

}
