package com.app.agendamento.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.app.agendamento.domain.entity.Medico;
import com.app.agendamento.domain.repository.MedicoRepository;
import com.app.agendamento.exception.NegocioException;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;


@Service
@Transactional
@RequiredArgsConstructor
public class MedicoService {

    private final MedicoRepository repository;

    public Medico salvar (Medico medico){
        boolean existeCRM = false;

        Optional<Medico> medicoExistente = repository.findByCrm(medico.getCrm());

        if (medicoExistente.isPresent()){
            if(!medicoExistente.get().getId().equals(medico.getId())){
                existeCRM = true;
            }
        }

        if (existeCRM){
            throw new NegocioException("CRM já cadastrado!");
        }
        return repository.save(medico);

    }


    public List<Medico> listar (){
        return repository.findAll();
    }

    public Optional<Medico> buscaPorId(Long id){
        return repository.findById(id);
    }

    public Medico alterar (Long id, Medico medico){
        Optional<Medico> medicoExistente = repository.findById(id);
        
        if(medicoExistente.isEmpty()){
            throw new NegocioException(id);
        }
        medico.setId(id);
        return salvar(medico);

    }

    public void deletar (Long id){
        repository.deleteById(id);
    }
}
