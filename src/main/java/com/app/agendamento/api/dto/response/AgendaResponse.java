package com.app.agendamento.api.dto.response;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AgendaResponse {

    private Long id;
    private LocalDateTime horario;
    private ProcedimentoResponse procedimento;
    private MedicoResponse medico;
    private PacienteResponse paciente;
}
