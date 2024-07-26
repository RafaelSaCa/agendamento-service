package com.app.agendamento.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.agendamento.api.dto.mapper.PacienteMapper;
import com.app.agendamento.api.dto.request.PacienteRequest;
import com.app.agendamento.api.dto.response.PacienteResponse;
import com.app.agendamento.domain.entity.Paciente;
import com.app.agendamento.domain.service.PacienteService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/paciente")
public class PacienteController {

    private final PacienteService service;
    private final PacienteMapper mapper;

    public PacienteController(PacienteService service, PacienteMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity<PacienteResponse> salvar(@Valid @RequestBody PacienteRequest pacienteRequest) {

        Paciente paciente = mapper.toPaciente(pacienteRequest);
        Paciente pacienteSalvo = service.salvar(paciente);
        PacienteResponse pacienteResponse = mapper.toPacienteResponse(pacienteSalvo);
        return ResponseEntity.status(HttpStatus.CREATED).body(pacienteResponse);
    }

    @GetMapping
    public ResponseEntity<List<PacienteResponse>> listar() {
        List<Paciente> pacientes = service.listar();
        List<PacienteResponse> listPacienteResponse = mapper.toPacienteResponseList(pacientes);
        return ResponseEntity.status(HttpStatus.OK).body(listPacienteResponse);
    }

    // @GetMapping
    // public Page<Paciente> listarPorPagina(
    //         @RequestParam(defaultValue = "0") @PositiveOrZero int page,
    //         @RequestParam(defaultValue = "10") @Positive @Max(100) int size) {

    //     return service.listaPaginada(page, size);
    // }

    @GetMapping("/{id}")
    public ResponseEntity<PacienteResponse> buscarPorId(@PathVariable Long id) {

        Optional<Paciente> pacienteExistente = service.buscarPorId(id);

        if (pacienteExistente.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(mapper.toPacienteResponse(pacienteExistente.get()));

    }

    @PutMapping("/{id}")
    public ResponseEntity<PacienteResponse> alterar(@PathVariable Long id,
            @Valid @RequestBody PacienteRequest pacienteRequest) {

        Paciente paciente = mapper.toPaciente(pacienteRequest);
        Paciente pacienteAlterado = service.alterar(id, paciente);
        PacienteResponse pacienteResponse = mapper.toPacienteResponse(pacienteAlterado);
        return ResponseEntity.status(HttpStatus.OK).body(pacienteResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
