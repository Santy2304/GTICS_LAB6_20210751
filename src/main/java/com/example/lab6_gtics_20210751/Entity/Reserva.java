package com.example.lab6_gtics_20210751.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Entity
@Getter
@Setter
@Table(name = "reservas")
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idreservas", nullable = false)
    private Integer idReserva;

    @NotNull
    @Column(name = "fechainicio", nullable = false)
    private Instant fechaInicio;

    @NotNull
    @Column(name = "fechafin", nullable = false)
    private Instant fechaFin;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "idusuario", nullable = false)
    private Usuario idusuario;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "idmesas", nullable = false)
    private Mesa idmesas;

}
