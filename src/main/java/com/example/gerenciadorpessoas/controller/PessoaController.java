package com.example.gerenciadorpessoas.controller;

import com.example.gerenciadorpessoas.dto.PessoaDTO;
import com.example.gerenciadorpessoas.entity.Pessoa;
import com.example.gerenciadorpessoas.service.PessoaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {

    private final PessoaService pessoaService;

    public PessoaController(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }

    @PostMapping
    public ResponseEntity<Pessoa> criarPessoa(@RequestBody PessoaDTO dto) {
        Pessoa salva = pessoaService.criarPessoa(dto);
        return ResponseEntity.ok(salva);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pessoa> buscarPorId(@PathVariable Long id) {
        Pessoa pessoa = pessoaService.buscarPorId(id);
        return ResponseEntity.ok(pessoa);
    }

    @GetMapping
    public ResponseEntity<List<Pessoa>> listarTodas() {
        List<Pessoa> pessoas = pessoaService.listarTodas();
        return ResponseEntity.ok(pessoas);
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<Pessoa>> buscarPorNome(@RequestParam String nome) {
        List<Pessoa> pessoas = pessoaService.buscarPorNome(nome);
        return ResponseEntity.ok(pessoas);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pessoa> atualizarPessoa(@PathVariable Long id, @RequestBody PessoaDTO dto) {
        Pessoa atualizada = pessoaService.atualizarPessoa(id, dto);
        return ResponseEntity.ok(atualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPessoa(@PathVariable Long id) {
        pessoaService.deletarPessoa(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/filtrar")
    public ResponseEntity<List<Pessoa>> buscarPorNomeEIdade(
            @RequestParam String nome,
            @RequestParam int idade) {
        List<Pessoa> pessoas = pessoaService.buscarPorNomeEIdade(nome, idade);
        return ResponseEntity.ok(pessoas);
    }

}
