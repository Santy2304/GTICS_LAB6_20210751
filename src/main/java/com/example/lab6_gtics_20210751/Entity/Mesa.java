package com.example.lab6_gtics_20210751.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "mesas")
public class Mesa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idmesas", nullable = false)
    private Integer id;

    @NotBlank
    @Column(name = "nombre", nullable = false)
    private String nombre;

    @NotNull
    @Positive
    @Column(name = "cantidad", nullable = false)
    private String cantidad;

    @NotNull
    @Positive
    @Column(name = "disponible", nullable = false)
    private String disponible;

    @NotBlank
    @Column(name = "ubicacion", nullable = false)
    private String ubicacion;


    @Column(name = "habilitado", nullable = false)
    private Boolean habilitado;

}
