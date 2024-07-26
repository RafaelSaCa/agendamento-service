package com.app.agendamento.api.dto.response;

import java.util.List;

public record PacientePageResponse(
        List<PacienteResponse> pacientes,
        int page, int size,
        long totalElements,
        int totalPages) {
}
