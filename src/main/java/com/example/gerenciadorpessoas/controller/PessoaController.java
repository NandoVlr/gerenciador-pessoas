package com.example.gerenciadorpessoas.controller;

// Importa as demais classes Pessoa do projeto (DTO, Entidade e o Repositório)
import com.example.gerenciadorpessoas.dto.PessoaDTO;
import com.example.gerenciadorpessoas.entity.Pessoa;
import com.example.gerenciadorpessoas.repository.PessoaRepository;

import org.springframework.http.ResponseEntity;  // Importa a classe ResponseEntity para encapsular a resposta HTTP (com status e corpo)
import org.springframework.web.bind.annotation.*;  // Importa as anotações de controle REST, como @RestController, @RequestMapping, @PostMapping, @GetMapping, etc.

import java.util.List;  // Importa a classe List, que será usada para retornar uma lista de objetos Pessoa
import java.util.Optional;  // Importa a classe Optional, usada para representar o valor retornado de maneira segura (evita NullPointerException)

@RestController  // Anotação que define esta classe como um controlador REST
@RequestMapping("/pessoas")  // Define o caminho base para as requisições dessa classe
public class PessoaController {

    private final PessoaRepository pessoaRepository;  // Repositório de Pessoa para acessar os dados no banco

    // Construtor para injeção de dependência do repositório
    public PessoaController(PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }

    // Endpoint para criar uma nova pessoa
    @PostMapping  // Define o método HTTP POST para criar uma nova pessoa
    public ResponseEntity<Pessoa> criarPessoa(@RequestBody PessoaDTO dto) {
        // Cria uma nova instância de Pessoa a partir do DTO recebido
        Pessoa pessoa = new Pessoa(null, dto.getNome(), dto.getCpf(), dto.getIdade());
         // Salva a nova pessoa no banco de dados
        Pessoa salva = pessoaRepository.save(pessoa);
        // Retorna a pessoa salva com um status 200 OK
        return ResponseEntity.ok(salva);
    }

    // Endpoint para buscar uma pessoa pelo ID
    @GetMapping("/{id}")  // Define o método HTTP GET para buscar uma pessoa por ID
    public ResponseEntity<Pessoa> buscarPorId(@PathVariable Long id) {
        // Tenta encontrar a pessoa no banco de dados pelo ID
        Optional<Pessoa> pessoa = pessoaRepository.findById(id);
        // Retorna a pessoa encontrada ou um erro 404 (não encontrada)
        return pessoa.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Endpoint para listar todas as pessoas
    @GetMapping  // Define o método HTTP GET para listar todas as pessoas
    public ResponseEntity<List<Pessoa>> listarTodas() {
        // Recupera todas as pessoas do banco de dados
        List<Pessoa> pessoas = pessoaRepository.findAll();
        // Retorna a lista de pessoas com um status 200 OK
        return ResponseEntity.ok(pessoas);
    }

    // Endpoint para buscar pessoas pelo nome
    @GetMapping("/buscar")  // Define o método HTTP GET para buscar pessoas pelo nome
    public ResponseEntity<List<Pessoa>> buscarPorNome(@RequestParam String nome) {
        // Encontra pessoas cujo nome contém o valor fornecido (ignora maiúsculas e minúsculas)
        List<Pessoa> pessoas = pessoaRepository.findByNomeContainingIgnoreCase(nome);
        // Retorna a lista de pessoas encontradas com um status 200 OK
        return ResponseEntity.ok(pessoas);
    }

    // Endpoint para atualizar uma pessoa
    @PutMapping("/{id}")  // Define o método HTTP PUT para atualizar uma pessoa pelo ID
    public ResponseEntity<Pessoa> atualizarPessoa(@PathVariable Long id, @RequestBody PessoaDTO dto) {
        // Tenta encontrar a pessoa pelo ID
        return pessoaRepository.findById(id)
                .map(pessoa -> {
                    // Atualiza os dados da pessoa com os dados do DTO
                    pessoa.setNome(dto.getNome());
                    pessoa.setCpf(dto.getCpf());
                    pessoa.setIdade(dto.getIdade());

                    // Salva a pessoa atualizada no banco de dados
                    Pessoa atualizada = pessoaRepository.save(pessoa);
                    // Retorna a pessoa atualizada com um status 200 OK
                    return ResponseEntity.ok(atualizada);
                }).orElse(ResponseEntity.notFound().build());  // Se a pessoa não for encontrada, retorna um erro 404 (não encontrada)
    }

    // Endpoint para deletar uma pessoa
    @DeleteMapping("/{id}")  // Define o método HTTP DELETE para deletar uma pessoa pelo ID
    public ResponseEntity<Void> deletarPessoa(@PathVariable Long id) {
        // Verifica se a pessoa existe no banco de dados
        if (pessoaRepository.existsById(id)) {
            // Deleta a pessoa do banco de dados
            pessoaRepository.deleteById(id);
            // Retorna um status 204 (sem conteúdo) indicando que a pessoa foi deletada com sucesso
            return ResponseEntity.noContent().build();
        } else {
            // Se a pessoa não for encontrada, retorna um erro 404 (não encontrada)
            return ResponseEntity.notFound().build();
        }
    }    
}
