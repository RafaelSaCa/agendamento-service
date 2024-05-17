package com.app.agendamento.api.dto.response;

import lombok.Data;

@Data
public class PacienteResponse {
    private Long id;
    private String nome;
    private String sobrenome;
    private String email;
    private String cpf;
}
