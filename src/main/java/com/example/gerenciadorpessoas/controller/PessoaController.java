package com.example.gerenciadorpessoas.controller;

import com.example.gerenciadorpessoas.dto.PessoaDTO;
import com.example.gerenciadorpessoas.entity.Pessoa;
import com.example.gerenciadorpessoas.repository.PessoaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {

    private final PessoaRepository pessoaRepository;

    public PessoaController(PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }

    @PostMapping
    public ResponseEntity<Pessoa> criarPessoa(@RequestBody PessoaDTO dto) {
        Pessoa pessoa = new Pessoa(null, dto.getNome(), dto.getCpf(), dto.getIdade());
        Pessoa salva = pessoaRepository.save(pessoa);
        return ResponseEntity.ok(salva);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pessoa> buscarPorId(@PathVariable Long id) {
        Optional<Pessoa> pessoa = pessoaRepository.findById(id);
        return pessoa.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Pessoa>> listarTodas() {
        List<Pessoa> pessoas = pessoaRepository.findAll();
        return ResponseEntity.ok(pessoas);
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<Pessoa>> buscarPorNome(@RequestParam String nome) {
        List<Pessoa> pessoas = pessoaRepository.findByNomeContainingIgnoreCase(nome);
        return ResponseEntity.ok(pessoas);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Pessoa> atualizarPessoa(@PathVariable Long id, @RequestBody PessoaDTO dto) {
        return pessoaRepository.findById(id)
                .map(pessoa -> {
                    pessoa.setNome(dto.getNome());
                    pessoa.setCpf(dto.getCpf());
                    pessoa.setIdade(dto.getIdade());
                    Pessoa atualizada = pessoaRepository.save(pessoa);
                    return ResponseEntity.ok(atualizada);
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPessoa(@PathVariable Long id) {
        if (pessoaRepository.existsById(id)) {
            pessoaRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
}
