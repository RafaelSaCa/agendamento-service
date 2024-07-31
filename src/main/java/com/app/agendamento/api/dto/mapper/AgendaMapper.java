package com.app.agendamento.api.dto.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.app.agendamento.api.dto.request.AgendaRequest;
import com.app.agendamento.api.dto.response.AgendaResponse;
import com.app.agendamento.domain.entity.Agenda;
import com.app.agendamento.domain.repository.MedicoRepository;
import com.app.agendamento.domain.repository.PacienteRepository;
import com.app.agendamento.domain.repository.ProcedimentoRepository;
import com.app.agendamento.exception.NegocioException;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AgendaMapper {

    private final ModelMapper mapper;
    private final MedicoRepository medicoRepository;
    private final PacienteRepository pacienteRepository;
    private final ProcedimentoRepository procedimentoRepository;

    public Agenda toAgenda(AgendaRequest agendaRequest) {
        return mapper.map(agendaRequest, Agenda.class);
    }

    public AgendaResponse toAgendaResponse(Agenda agenda) {
        return mapper.map(agenda, AgendaResponse.class);
    }

    public List<AgendaResponse> toAgendaResponsesList(List<Agenda> agendas) {
        return agendas.stream()
                .map(this::toAgendaResponse)
                .collect(Collectors.toList());
    }

    public Agenda convertToAgendaResponse(AgendaRequest agendaRequest){
        Agenda agenda = mapper.map(agendaRequest, Agenda.class);
        agenda.setHorario(agendaRequest.getHorario());
        agenda.setProcedimento(procedimentoRepository.findById(agendaRequest.getProcedimentoId()).orElseThrow(() -> new NegocioException("Procedimento não encontrado!")));
        agenda.setMedico(medicoRepository.findById(agendaRequest.getMedicoId()).orElseThrow(() -> new NegocioException("Médico não encontrado!")));
        agenda.setPaciente(pacienteRepository.findById(agendaRequest.getPacienteId()).orElseThrow(() -> new NegocioException("Paciente não encontrado!")));

        return agenda;
    }

   
}
