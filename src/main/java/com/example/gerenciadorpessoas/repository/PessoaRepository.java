package com.example.gerenciadorpessoas.repository;

import com.example.gerenciadorpessoas.entity.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
    List<Pessoa> findByNomeContainingIgnoreCase(String nome);
}
