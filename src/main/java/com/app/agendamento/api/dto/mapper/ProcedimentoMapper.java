package com.app.agendamento.api.dto.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.app.agendamento.api.dto.request.ProcedimentoRequest;
import com.app.agendamento.api.dto.response.ProcedimentoResponse;
import com.app.agendamento.domain.entity.Procedimento;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ProcedimentoMapper {

    private final ModelMapper mapper;

    public Procedimento toProcedimento (ProcedimentoRequest procedimentoRequest){
        return mapper.map(procedimentoRequest, Procedimento.class);
    }

    public ProcedimentoResponse toProcedimentoResponse (Procedimento procedimento){
        return mapper.map(procedimento, ProcedimentoResponse.class);
    }

    public List<ProcedimentoResponse> toProcedimentoResponseList (List<Procedimento> procedimentos){
        return procedimentos.stream()
                .map(this::toProcedimentoResponse)
                .collect(Collectors.toList());
    }

}
