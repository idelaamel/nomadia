package com.ILSI.TouristeProject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class TouristeProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(TouristeProjectApplication.class, args);
	}

}
