package com.example.gerenciadorpessoas.service.exception;

public class PessoaNotFoundException extends RuntimeException {
    public PessoaNotFoundException(Long id) {
        super("Pessoa com ID " + id + " não encontrada.");
    }
}
