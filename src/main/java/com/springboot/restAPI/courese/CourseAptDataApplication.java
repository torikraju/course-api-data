package com.springboot.restAPI.courese;

import org.jsondoc.spring.boot.starter.EnableJSONDoc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableJSONDoc
public class CourseAptDataApplication {

	public static void main(String[] args) {
		SpringApplication.run(CourseAptDataApplication.class, args);
	}
}
