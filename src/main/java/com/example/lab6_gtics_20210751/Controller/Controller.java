package com.example.lab6_gtics_20210751.Controller;

import com.example.lab6_gtics_20210751.Repository.MesaRepository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@org.springframework.stereotype.Controller
public class Controller {

    final MesaRepository mesaRepository;

    public Controller(MesaRepository mesaRepository) {
        this.mesaRepository = mesaRepository;
    }

    @GetMapping("/")
    public String listaMesas(Model model) {
        model.addAttribute("listaMesas", mesaRepository.listarMesasValidas());
        return "ListaMesas";
    }

}
