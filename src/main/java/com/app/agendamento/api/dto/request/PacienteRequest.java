package com.app.agendamento.api.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class PacienteRequest {

    @NotBlank(message = "Nome é obrigatório")
    private String nome;

    @NotBlank(message = "Sobrenome é obrigatório")
    private String sobrenome;

    private String telefone;

    private String email;

    @NotBlank(message = "Cpf é obrigatório")
    private String cpf;
}
