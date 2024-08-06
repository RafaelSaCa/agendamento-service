package com.app.agendamento.domain.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.agendamento.domain.entity.Agenda;
import com.app.agendamento.domain.entity.Paciente;
import com.app.agendamento.domain.repository.AgendaRepository;
import com.app.agendamento.exception.NegocioException;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class AgendaService {

    private final AgendaRepository repository;
    private final PacienteService pacienteService;

    public Agenda salvar(Agenda agenda) {
        Optional<Paciente> pacienteExistente = pacienteService.buscarPorId(agenda.getPaciente().getId());

        if (pacienteExistente.isEmpty()) {
            throw new NegocioException("Paciente não encontrado.");
        }

        Optional<Agenda> horarioExistente = repository.findByHorario(agenda.getHorario());

        if (horarioExistente.isPresent()) {
            throw new NegocioException("Já existe agendamento neste horário!");
        }

        agenda.setPaciente(pacienteExistente.get());

        return repository.save(agenda);
    }

    public List<Agenda> listar() {
        return repository.findAll();
    }

    public Optional<Agenda> buscarPorId(Long id) {
        return repository.findById(id);
    }

    public Agenda alterar(Long id, Agenda agenda) {
        Optional<Agenda> agendaExistente = buscarPorId(id);

        if (agendaExistente.isEmpty()) {
            throw new NegocioException(id);

        }

        return salvar(agenda);
    }

    public Optional<Agenda> buscaPorData(LocalDateTime data){
        Optional<Agenda> dataExistente = repository.findByHorario(data);

        if (dataExistente.isEmpty()){
            throw new NegocioException("Não existe nenhum agendamento nesta data!");
        }

       return dataExistente;
    }

}
