package com.app.agendamento.api.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class MedicoRequest {

    @NotBlank(message = "Nome é obrigatório")
    private String nome;
    @NotBlank(message = "Sobrenome é obrigatório")
    private String sobrenome;
    @NotBlank(message = "CRM é obrigatório")
    private String crm;
    private String telefone;
}
