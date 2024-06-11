package com.app.agendamento.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.agendamento.api.dto.mapper.AgendaMapper;
import com.app.agendamento.api.dto.request.AgendaRequest;
import com.app.agendamento.api.dto.response.AgendaResponse;
import com.app.agendamento.domain.entity.Agenda;
import com.app.agendamento.domain.service.AgendaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/agenda")
public class AgendaController {

    private final AgendaService service;
    private final AgendaMapper mapper;

    public AgendaController(AgendaService service, AgendaMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping
    public ResponseEntity<List<AgendaResponse>> listar() {
        List<Agenda> agendas = service.listar();
        List<AgendaResponse> listAgendaResponse = mapper.toAgendaResponsesList(agendas);
        return ResponseEntity.status(HttpStatus.OK).body(listAgendaResponse);

    }

    @GetMapping("/{id}")
    public ResponseEntity<AgendaResponse> buscarPorId(@Valid @PathVariable Long id) {

        Optional<Agenda> agendaExistente = service.buscarPorId(id);

        if (agendaExistente.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        AgendaResponse agendaResponse = mapper.toAgendaResponse(agendaExistente.get());
        return ResponseEntity.status(HttpStatus.OK).body(agendaResponse);
    }

    @PostMapping
    public ResponseEntity<AgendaResponse> salvar(@Valid @RequestBody AgendaRequest agendaRequest) {
        Agenda agenda = mapper.toAgenda(agendaRequest);
        Agenda agendaSalva = service.salvar(agenda);
        AgendaResponse agendaResponse = mapper.toAgendaResponse(agendaSalva);

        return ResponseEntity.status(HttpStatus.CREATED).body(agendaResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AgendaResponse> alterar(@PathVariable Long id,
            @Valid @RequestBody AgendaRequest agendaRequest) {

        Agenda agenda = mapper.toAgenda(agendaRequest);
        Agenda agendaAlterada = service.alterar(id, agenda);

        AgendaResponse agendaResponse = mapper.toAgendaResponse(agendaAlterada);
        return ResponseEntity.status(HttpStatus.OK).body(agendaResponse);
    }

}
