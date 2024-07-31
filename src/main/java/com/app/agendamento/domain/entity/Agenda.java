package com.app.agendamento.domain.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "agenda")
public class Agenda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "data_hora")
    private LocalDateTime horario;

    @ManyToOne
    @JoinColumn(name = "procedimento_id")
    private Procedimento procedimento;

    @ManyToOne
    @JoinColumn(name = "medico_id")
    private Medico medico;
    
    @ManyToOne
    @JoinColumn(name = "paciente_id")
    private Paciente paciente;

}
