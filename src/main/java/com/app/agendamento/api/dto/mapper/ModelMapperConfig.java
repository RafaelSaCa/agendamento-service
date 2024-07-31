package com.app.agendamento.api.dto.mapper;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.app.agendamento.api.dto.request.AgendaRequest;
import com.app.agendamento.domain.entity.Agenda;

@Configuration
public class ModelMapperConfig {

    // @Bean
    // public ModelMapper modelMapper() {
    // return new ModelMapper();
    // }
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        modelMapper.typeMap(Agenda.class, AgendaRequest.class).addMappings(mapper -> {
            mapper.map(src -> src.getHorario(),AgendaRequest::setHorario);
            mapper.map(src -> src.getMedico().getId(), AgendaRequest::setMedicoId);
            mapper.map(src -> src.getPaciente().getId(), AgendaRequest::setPacienteId);
            mapper.map(src -> src.getProcedimento().getId(), AgendaRequest::setProcedimentoId);
        });

        modelMapper.typeMap(AgendaRequest.class, Agenda.class).addMappings(mapper -> {
            mapper.skip(Agenda::setHorario);
            mapper.skip(Agenda::setMedico);
            mapper.skip(Agenda::setPaciente);
            mapper.skip(Agenda::setProcedimento);
        });

        return modelMapper;
    }
}