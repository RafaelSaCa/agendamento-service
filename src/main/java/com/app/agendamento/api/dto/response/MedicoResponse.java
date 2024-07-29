package com.app.agendamento.api.dto.response;

import lombok.Data;

@Data
public class MedicoResponse {
    private Long id;
    private String nome;
    private String sobrenome;
    private String crm;
    private String telefone;


}
