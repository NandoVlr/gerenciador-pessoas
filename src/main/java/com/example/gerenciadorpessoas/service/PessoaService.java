package com.example.gerenciadorpessoas.service;

import com.example.gerenciadorpessoas.dto.PessoaDTO;
import com.example.gerenciadorpessoas.entity.Pessoa;
import com.example.gerenciadorpessoas.repository.PessoaRepository;
import com.example.gerenciadorpessoas.service.exception.PessoaNotFoundException;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@Service
public class PessoaService {


    private final PessoaRepository pessoaRepository;

    public PessoaService(PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }

    // Caso de uso: criar nova pessoa
    public Pessoa criarPessoa(PessoaDTO dto) {
        log.info("Criando nova pessoa: nome={}, cpf={}", dto.getNome(), dto.getCpf());
        Pessoa pessoa = new Pessoa(null, dto.getNome(), dto.getCpf(), dto.getIdade());
        Pessoa salva = pessoaRepository.save(pessoa);
        log.info("Pessoa salva com ID {}", salva.getId());
        return salva;
    }

    // Caso de uso: buscar por ID
    public Pessoa buscarPorId(Long id) {
        log.info("Buscando pessoa com ID: {}", id);
        return pessoaRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Pessoa com ID {} não encontrada.", id);
                return new PessoaNotFoundException(id);
                });
    }

    // Caso de uso: listar todas
    public List<Pessoa> listarTodas() {
        return pessoaRepository.findAll();
    }

    // Caso de uso: buscar por nome parcial
    public List<Pessoa> buscarPorNome(String nome) {
        return pessoaRepository.findByNomeContainingIgnoreCase(nome);
    }

    // Caso de uso: atualizar pessoa
    public Pessoa atualizarPessoa(Long id, PessoaDTO dto) {
        Pessoa pessoa = buscarPorId(id); // já lança exceção se não existir
        pessoa.setNome(dto.getNome());
        pessoa.setCpf(dto.getCpf());
        pessoa.setIdade(dto.getIdade());
        Pessoa atualizada = pessoaRepository.save(pessoa);
        log.info("Pessoa atualizada com ID: {}", atualizada.getId());
        return atualizada;
    }

    // Caso de uso: deletar pessoa
    public void deletarPessoa(Long id) {
        if (!pessoaRepository.existsById(id)) {
            throw new PessoaNotFoundException(id);
        }
        pessoaRepository.deleteById(id);
        log.info("Pessoa deletada com ID: {}", id);
    }

    // Caso de uso: buscar nome "Rafael" e idade > 18
    public List<Pessoa> buscarPorNomeEIdade(String nome, int idade) {
        return pessoaRepository.findByNomeStartingWithAndIdadeGreaterThan(nome, idade);
    }
}
