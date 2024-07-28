package com.app.agendamento.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.app.agendamento.domain.entity.Procedimento;
import com.app.agendamento.domain.repository.ProcedimentoRepository;
import com.app.agendamento.exception.NegocioException;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class ProcedimentoService {

    private final ProcedimentoRepository repository;

    public Procedimento salvar (Procedimento procedimento){
        return repository.save(procedimento);
    }

    public List<Procedimento> listar (){
        return repository.findAll();
    }

    public Optional<Procedimento> buscarPorId(Long id){
        return repository.findById(id);
    }

    public Procedimento alterar (Long id, Procedimento procedimento){
        Optional<Procedimento> procedimentoExistente = this.buscarPorId(id);

        if ( procedimentoExistente.isEmpty()){
            throw new NegocioException(id);
        }

        procedimento.setId(id);
        return salvar(procedimento);
    }

    public void deletar (Long id){
        repository.deleteById(id);
    }
}
