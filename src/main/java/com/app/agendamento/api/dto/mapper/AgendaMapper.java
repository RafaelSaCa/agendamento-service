package com.app.agendamento.api.dto.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Configuration;

import com.app.agendamento.api.dto.request.AgendaRequest;
import com.app.agendamento.api.dto.response.AgendaResponse;
import com.app.agendamento.domain.entity.Agenda;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class AgendaMapper {

    private final ModelMapper mapper;

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

}
