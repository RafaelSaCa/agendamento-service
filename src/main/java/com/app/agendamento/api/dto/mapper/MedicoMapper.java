package com.app.agendamento.api.dto.mapper;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.app.agendamento.api.dto.request.MedicoRequest;
import com.app.agendamento.api.dto.response.MedicoResponse;
import com.app.agendamento.domain.entity.Medico;

import lombok.RequiredArgsConstructor;


@Component
@RequiredArgsConstructor
public class MedicoMapper {
    
    private final ModelMapper mapper;

    public Medico toMedico (MedicoRequest medicoRequest){
        return mapper.map(medicoRequest,Medico.class);
    }   

    public MedicoResponse toMedicoResponse (Medico medico){
        return mapper.map(medico, MedicoResponse.class);
    }

    public List<MedicoResponse> toMedicoResponseList (List<Medico> medicos){
        return medicos.stream()
            .map(this::toMedicoResponse)
            .collect(Collectors.toList());
    }
}
