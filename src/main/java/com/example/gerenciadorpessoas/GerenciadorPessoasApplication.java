package com.example.gerenciadorpessoas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication  // Indica que esta é a classe principal do Spring Boot, que contém as configurações padrão
@EnableJpaRepositories(basePackages = "com.example.gerenciadorpessoas.repository")  // Habilita o uso do Spring Data JPA no pacote especificado para detectar e configurar os repositórios
public class GerenciadorPessoasApplication {
	public static void main(String[] args) {
		// Inicia o aplicativo Spring Boot. O método 'run' irá configurar o contexto Spring e iniciar o servidor web embutido.
		SpringApplication.run(GerenciadorPessoasApplication.class, args);
	}
}
