package io.github.bhuwanupadhyay.eg1;

import io.github.bhuwanupadhyay.eg1.core.LoggedInUser;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class EgApplication {

	public static void main(String[] args) {
		SpringApplication.run(EgApplication.class, args);
	}

	@Bean
	public LoggedInUser loggedInUser() {
		return () -> "default";
	}

}
