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

import com.app.agendamento.api.dto.mapper.ProcedimentoMapper;
import com.app.agendamento.api.dto.request.ProcedimentoRequest;
import com.app.agendamento.api.dto.response.ProcedimentoResponse;
import com.app.agendamento.domain.entity.Procedimento;
import com.app.agendamento.domain.service.ProcedimentoService;

import jakarta.validation.Valid;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/procedimento")
public class ProcedimentoController {

    private final ProcedimentoService service;
    private final ProcedimentoMapper mapper;

    public ProcedimentoController(ProcedimentoService service, ProcedimentoMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity<ProcedimentoResponse> salvar (@Valid @RequestBody ProcedimentoRequest procedimentoRequest){

        Procedimento procedimento = mapper.toProcedimento(procedimentoRequest);
        Procedimento procedimentoSalvo = service.salvar(procedimento);
        ProcedimentoResponse procedimentoResponse = mapper.toProcedimentoResponse(procedimentoSalvo);
        return ResponseEntity.status(HttpStatus.CREATED).body(procedimentoResponse);
    }
    
    @GetMapping
    public ResponseEntity<List<ProcedimentoResponse>> listar(){

        List<Procedimento> procedimentos = service.listar();
        List<ProcedimentoResponse> listaProcedimentoResponses = mapper.toPacienteResponseList(procedimentos);
        return ResponseEntity.status(HttpStatus.OK).body(listaProcedimentoResponses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProcedimentoResponse> buscaPorId (@PathVariable Long id){

        Optional<Procedimento> procedimentoExistente = service.buscarPorId(id);

        if(procedimentoExistente.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(mapper.toProcedimentoResponse(procedimentoExistente.get()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProcedimentoResponse> alterar (@PathVariable Long id,@Valid @RequestBody ProcedimentoRequest procedimentoRequest){

        Procedimento procedimento = mapper.toProcedimento(procedimentoRequest);
        Procedimento procedimentoAlterado = service.alterar(id, procedimento);
        ProcedimentoResponse procedimentoResponse = mapper.toProcedimentoResponse(procedimentoAlterado);
        return ResponseEntity.status(HttpStatus.OK).body(procedimentoResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar (@PathVariable Long id){
        service.deletar(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
