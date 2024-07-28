package com.app.agendamento.api.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ProcedimentoRequest {

    @NotBlank(message = "A descrição é obrigatória!")
    private String descricao;


}
