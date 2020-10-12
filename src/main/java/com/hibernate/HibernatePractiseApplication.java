package com.hibernate;

 import org.springframework.boot.SpringApplication; 
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

 
 
@EnableAutoConfiguration(exclude = HibernateJpaAutoConfiguration.class)
@SpringBootApplication
public class HibernatePractiseApplication {

	public static void main(String[] args) {
		 
		SpringApplication.run(HibernatePractiseApplication.class, args);
	}

}
