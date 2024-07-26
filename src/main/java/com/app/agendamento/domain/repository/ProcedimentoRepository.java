package com.app.agendamento.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.agendamento.domain.entity.Procedimento;

@Repository
public interface ProcedimentoRepository extends JpaRepository<Procedimento,Long>{

}
