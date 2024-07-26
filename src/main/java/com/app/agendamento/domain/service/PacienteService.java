package com.app.agendamento.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.agendamento.domain.entity.Paciente;
import com.app.agendamento.domain.repository.PacienteRepository;
import com.app.agendamento.exception.NegocioException;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
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

    public Page<Paciente> listaPaginada(@PositiveOrZero int page, @Positive @Max(100) int size) {
        return repository.findAll(PageRequest.of(page, size));
    }

    public Optional<Paciente> buscarPorId(Long id) {
        return repository.findById(id);

    }

    public Paciente alterar(Long id, Paciente paciente) {
        Optional<Paciente> pacienteExistente = this.buscarPorId(id);

        if (pacienteExistente.isEmpty()) {
            throw new NegocioException(id);
        }

        paciente.setId(id);

        return salvar(paciente);
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }

}
