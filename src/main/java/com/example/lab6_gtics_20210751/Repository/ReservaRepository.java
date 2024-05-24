package com.example.lab6_gtics_20210751.Repository;

import com.example.lab6_gtics_20210751.Entity.Mesa;
import com.example.lab6_gtics_20210751.Entity.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReservaRepository extends JpaRepository<Reserva,Integer> {

    @Query(nativeQuery = true, value = "SELECT * FROM reservas WHERE idusuario=1")
    List<Reserva> listarMisReservas(int idUsuario);


}
