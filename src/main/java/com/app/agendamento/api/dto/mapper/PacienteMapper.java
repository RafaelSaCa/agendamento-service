package com.app.agendamento.api.dto.mapper;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.app.agendamento.api.dto.request.PacienteRequest;
import com.app.agendamento.api.dto.response.PacienteResponse;
import com.app.agendamento.domain.entity.Paciente;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class PacienteMapper {

    private final ModelMapper mapper;

    public Paciente toPaciente(PacienteRequest pacienteRequest) {
        return mapper.map(pacienteRequest, Paciente.class);
    }

    public PacienteResponse toPacienteResponse(Paciente paciente) {
        return mapper.map(paciente, PacienteResponse.class);
    }

    public List<PacienteResponse> toPacienteResponseList(List<Paciente> pacientes) {
        return pacientes.stream()
                .map(this::toPacienteResponse)
                .collect(Collectors.toList());

    }
}
