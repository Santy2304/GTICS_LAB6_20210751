package com.example.lab6_gtics_20210751.Repository;

import com.example.lab6_gtics_20210751.Entity.Mesa;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MesaRepository extends JpaRepository<Mesa,Integer> {
    @Query(nativeQuery = true, value = "SELECT * FROM mesas WHERE habilitado=1")
    List<Mesa> listarMesasValidas();

    @Transactional
    @Modifying
    @Query(value = "update mesas set habilitado = 0  where idmesas =?1" , nativeQuery = true)
    void eliminarMesaPorId(int idMesa);
}
