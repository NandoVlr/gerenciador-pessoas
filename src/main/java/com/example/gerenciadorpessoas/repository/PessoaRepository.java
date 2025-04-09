package com.example.gerenciadorpessoas.repository;

import com.example.gerenciadorpessoas.entity.Pessoa; // Importa a classe Pessoa que é a entidade no banco
import org.springframework.data.jpa.repository.JpaRepository; // Importa a interface JpaRepository para fornecer métodos CRUD prontos
import java.util.List; // Importa a classe List para usar como tipo de retorno

// Interface do repositório que estende JpaRepository para interagir com a entidade Pessoa no banco de dados
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

    // Método customizado que busca pessoas pelo nome, ignorando maiúsculas e minúsculas
    // O nome 'findByNomeContainingIgnoreCase' segue o padrão de nomenclatura do Spring Data JPA
    List<Pessoa> findByNomeContainingIgnoreCase(String nome);
}
