package com.example.gerenciadorpessoas.dto;

import lombok.Data;   // Importa a anotação Lombok @Data para geração automática de métodos

@Data  // Lombok vai gerar automaticamente os métodos getter, setter, toString, equals e hashCode para todos os campos dessa classe
public class PessoaDTO {
    private String nome;  // Atributo que representa o nome da pessoa (será transferido em uma solicitação)
    private String cpf;  // Atributo que representa o CPF da pessoa (será transferido em uma solicitação)
    private Integer idade;  // Atributo que representa a idade da pessoa (será transferido em uma solicitação)
}
