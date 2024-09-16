package br.com.exemplo.testai;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class TestaiApplication implements CommandLineRunner {

	@Bean
	public OpenAPI springTestaiAPI() {
		return new OpenAPI()
				.info(new Info().title("Testai Application")
						.description("Documentação da Api do Easymbark")
						.version("v1.0.0"));
	}

	@Bean
	RestTemplate restTeamplate() { return new RestTemplate(); }

	public static void main(String[] args) {
		SpringApplication.run(TestaiApplication.class, args);
	}

	@Override
	@Transactional
	public void run(String... args) throws Exception {
		// Teste para garantir que o Hibernate está configurado corretamente
		System.out.println("Spring Boot Application is running and connected to PostgreSQL.");
	}
}
