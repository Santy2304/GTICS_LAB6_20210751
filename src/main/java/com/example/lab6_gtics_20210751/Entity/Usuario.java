package com.example.lab6_gtics_20210751.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idusuario", nullable = false)
    private Integer idUsuario;

    @NotBlank
    @Column(name = "nombre", nullable = false)
    private String nombre;

    @NotBlank
    @Column(name = "password", nullable = false)
    private String password;

    @NotBlank
    @Column(name = "correo", nullable = false)
    private String correo;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "idrol", nullable = false)
    private Rol idrol;

    @NotNull
    @Column(name = "activo", nullable = false)
    private Boolean activo;

}
