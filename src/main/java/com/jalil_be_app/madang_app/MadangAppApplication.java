package com.jalil_be_app.madang_app;

import com.jalil_be_app.madang_app.model.enums.SeatCategory;
import com.jalil_be_app.madang_app.utils.GenerateTransactionNumber;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class MadangAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(MadangAppApplication.class, args);
	}



}
