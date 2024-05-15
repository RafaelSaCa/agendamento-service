package com.app.agendamento.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.agendamento.domain.entity.Paciente;
import com.app.agendamento.domain.service.PacienteService;

@RestController
@RequestMapping("/paciente")
public class PacienteController {

    private final PacienteService service;

    public PacienteController(PacienteService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Paciente> salvar(@RequestBody Paciente paciente) {

        Paciente pacienteSalvo = service.salvar(paciente);
        return ResponseEntity.status(HttpStatus.CREATED).body(pacienteSalvo);
    }

    @GetMapping
    public ResponseEntity<List<Paciente>> listar() {

        List<Paciente> pacientes = service.listar();
        return ResponseEntity.status(HttpStatus.OK).body(pacientes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Paciente> buscarPorId(@PathVariable Long id) {

        Optional<Paciente> pacienteExistente = service.buscarPorId(id);

        if (pacienteExistente.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.status(HttpStatus.OK).body(pacienteExistente.get());

    }

    @PutMapping
    public ResponseEntity<Paciente> alterar(@RequestBody Paciente paciente) {

        Paciente pacienteAlterado = service.salvar(paciente);
        return ResponseEntity.status(HttpStatus.OK).body(pacienteAlterado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
