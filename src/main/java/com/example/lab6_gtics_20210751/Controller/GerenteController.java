package com.example.lab6_gtics_20210751.Controller;

import com.example.lab6_gtics_20210751.Repository.MesaRepository;
import com.example.lab6_gtics_20210751.Repository.ReservaRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/gerente")

public class GerenteController {
    final MesaRepository mesaRepository;

    final ReservaRepository reservaRepository;
    public GerenteController(MesaRepository mesaRepository,ReservaRepository reservaRepository) {
        this.mesaRepository = mesaRepository;
        this.reservaRepository = reservaRepository;
    }
    @GetMapping(value = { "/listaMesas",""})
    public String listarMesa(Model model) {

        model.addAttribute("listaMesas",mesaRepository.listarMesasValidas());

        return "ListaMesas";
    }

    @GetMapping(value = { "/listaReservas",""})
    public String listarReservas(Model model) {

        model.addAttribute("listaReservas",reservaRepository.findAll());

        return "Reservas/Reservas";
    }
}
