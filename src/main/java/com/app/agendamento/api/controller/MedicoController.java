package com.app.agendamento.api.controller;

import java.util.List;
import java.util.Optional;

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
import org.springframework.web.bind.annotation.RestController;

import com.app.agendamento.api.dto.mapper.MedicoMapper;
import com.app.agendamento.api.dto.request.MedicoRequest;
import com.app.agendamento.api.dto.response.MedicoResponse;
import com.app.agendamento.domain.entity.Medico;
import com.app.agendamento.domain.service.MedicoService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/medico")
public class MedicoController {

    private final MedicoService service;
    private final MedicoMapper mapper;

    public MedicoController(MedicoService service, MedicoMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity<MedicoResponse> salvar (@RequestBody MedicoRequest medicoRequest){
        Medico medico = mapper.toMedico(medicoRequest);
        Medico medicoSalvo = service.salvar(medico);
        MedicoResponse medicoResponse = mapper.toMedicoResponse(medicoSalvo);
        return ResponseEntity.status(HttpStatus.CREATED).body(medicoResponse);
    }
    
    @GetMapping
    public ResponseEntity<List<MedicoResponse>> lista(){
        List<Medico> medicos = service.listar();
        List<MedicoResponse> listaMedicoResponses = mapper.toMedicoResponseList(medicos);
        return ResponseEntity.status(HttpStatus.OK).body(listaMedicoResponses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicoResponse> buscaPorId(@PathVariable Long id){

        Optional<Medico> medicoExistente = service.buscaPorId(id);
        if (medicoExistente.isEmpty()){
           return ResponseEntity.notFound().build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(mapper.toMedicoResponse(medicoExistente.get()));       

    }

    @PutMapping("/{id}")
    public ResponseEntity<MedicoResponse> alterar (@PathVariable Long id, @RequestBody MedicoRequest medicoRequest){
        Medico medico = mapper.toMedico(medicoRequest);
        Medico medicoAlterado = service.alterar(id, medico);
        MedicoResponse medicoResponse = mapper.toMedicoResponse(medicoAlterado);
       return ResponseEntity.status(HttpStatus.OK).body(medicoResponse);
        
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar (@PathVariable Long id){
        service.deletar(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }



}
