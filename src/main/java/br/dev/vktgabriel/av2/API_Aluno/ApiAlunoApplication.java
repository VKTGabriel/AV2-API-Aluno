package br.dev.vktgabriel.av2.API_Aluno;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ApiAlunoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiAlunoApplication.class, args);
	}

}
