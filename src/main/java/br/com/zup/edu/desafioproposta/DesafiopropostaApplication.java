package br.com.zup.edu.desafioproposta;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableFeignClients
@EnableJpaRepositories(enableDefaultTransactions = false)
@EnableScheduling
public class DesafiopropostaApplication {

	public static void main(String[] args) {
		SpringApplication.run(DesafiopropostaApplication.class, args);
	}

}
