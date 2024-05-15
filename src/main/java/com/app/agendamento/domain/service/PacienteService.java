package com.app.agendamento.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.agendamento.domain.entity.Paciente;
import com.app.agendamento.domain.repository.PacienteRepository;
import com.app.agendamento.exception.NegocioException;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class PacienteService {

    private final PacienteRepository repository;

    public Paciente salvar(Paciente paciente) {
        boolean existeCpf = false;

        Optional<Paciente> pacienteExistente = repository.findByCpf(paciente.getCpf());

        if (pacienteExistente.isPresent()) {
            if (!pacienteExistente.get().getId().equals(paciente.getId())) {
                existeCpf = true;
            }
        }

        if (existeCpf) {
            throw new NegocioException("Cpf já cadastrado!");
        }

        return repository.save(paciente);

    }

    public List<Paciente> listar() {
        return repository.findAll();
    }

    public Optional<Paciente> buscarPorId(Long id) {
        return repository.findById(id);

    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }

}
