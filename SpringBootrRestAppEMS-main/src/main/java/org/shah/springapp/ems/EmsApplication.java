package org.shah.springapp.ems;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

//@SpringBootApplication(scanBasePackages = "org.shah.springapp.ems.Repository","org.shah.springapp.ems.Controller",)
//@EnableJpaRepositories("org.shah.springapp.ems.Repository")
@SpringBootApplication
public class EmsApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmsApplication.class, args);
	}

}
