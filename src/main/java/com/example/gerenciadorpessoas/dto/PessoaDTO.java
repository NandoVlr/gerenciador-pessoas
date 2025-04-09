package com.example.gerenciadorpessoas.dto;

import lombok.Data;

@Data
public class PessoaDTO {
    private String nome;
    private String cpf;
    private Integer idade;
}
