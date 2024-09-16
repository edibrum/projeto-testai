package br.com.exemplo.testai;

import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TestaiApplication implements CommandLineRunner {

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
