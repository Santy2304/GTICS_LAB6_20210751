package com.example.lab6_gtics_20210751.Repository;

import com.example.lab6_gtics_20210751.Entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario,Integer> {
    Usuario findByCorreo(String correo);

}
