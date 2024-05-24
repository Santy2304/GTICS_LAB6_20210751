package com.example.lab6_gtics_20210751.Controller;

import com.example.lab6_gtics_20210751.Entity.Mesa;
import com.example.lab6_gtics_20210751.Entity.Reserva;
import com.example.lab6_gtics_20210751.Entity.Usuario;
import com.example.lab6_gtics_20210751.Repository.MesaRepository;
import com.example.lab6_gtics_20210751.Repository.ReservaRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cliente")

public class ClienteController {
    final MesaRepository mesaRepository;

    final ReservaRepository reservaRepository;
    public ClienteController(MesaRepository mesaRepository, ReservaRepository reservaRepository) {
        this.mesaRepository = mesaRepository;
        this.reservaRepository = reservaRepository;
    }
    @GetMapping(value = { "/listaMesas",""})
    public String listarMesa(Model model) {

        model.addAttribute("listaMesas",mesaRepository.listarMesasValidas());

        return "ListaMesas";
    }


    @GetMapping(value = { "/listaReservas",""})
    public String listarReservas(Model model, HttpSession httpSession) {

        Usuario usuario = (Usuario) httpSession.getAttribute("usuario");

        model.addAttribute("listaReservas",reservaRepository.listarMisReservas(usuario.getIdUsuario()));

        return "Reservas/Reservas";
    }

    @GetMapping(value = { "/new"})
    public String crearMesa(@ModelAttribute("reserva") Reserva reserva) {
        return "Cliente/newFrm";

    }




}
