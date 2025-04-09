package com.example.gerenciadorpessoas.entity;

import jakarta.persistence.*;  // Importa os pacotes do Jakarta Persistence (JPA) para a definição de entidades e mapeamento de banco de dados
import lombok.*;  // Importa as anotações do Lombok para simplificação de código

@Entity  // Anotação que indica que essa classe é uma entidade, ou seja, será mapeada para uma tabela no banco de dados
@Data  // Lombok gera automaticamente os métodos getter, setter, toString, equals e hashCode para todos os campos da classe
@NoArgsConstructor  // Lombok gera o construtor sem argumentos (necessário para JPA)
@AllArgsConstructor  // Lombok gera o construtor com todos os campos como argumentos (útil para inicialização completa)
public class Pessoa {

    @Id  // Indica que o campo abaixo é a chave primária da tabela
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Indica que o valor do campo 'id' será gerado automaticamente pelo banco (autoincrement)
    private Long id;  // A chave primária da entidade, do tipo Long, que será usada para identificar de forma única cada pessoa

    private String nome;  // Atributo que representa o nome da pessoa
    private String cpf; // Atributo que representa o CPF da pessoa
    private Integer idade;  // Atributo que representa a idade da pessoa, do tipo Integer (pode ser nulo)
}
