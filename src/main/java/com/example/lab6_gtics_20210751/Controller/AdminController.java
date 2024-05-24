package com.example.lab6_gtics_20210751.Controller;

import com.example.lab6_gtics_20210751.Entity.Mesa;
import com.example.lab6_gtics_20210751.Repository.MesaRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class AdminController {

    final MesaRepository mesaRepository;
    public AdminController(MesaRepository mesaRepository) {
        this.mesaRepository = mesaRepository;
    }
    @GetMapping(value = { "/listaMesas",""})
    public String listarMesa(Model model) {

        model.addAttribute("listaMesas",mesaRepository.listarMesasValidas());

        return "ListaMesas";
    }
    @GetMapping(value = { "/edit"})
    public String editarDispositivos(@ModelAttribute("mesa")Mesa mesa, Model model, @RequestParam("id") int idMesa) {

        Optional<Mesa> optionalMesa = mesaRepository.findById(idMesa);

        if (optionalMesa.isPresent()) {
            mesa = optionalMesa.get();
            model.addAttribute("mesa", mesa);
            return "Admin/newFrm";
        } else {
            return "redirect:/admin/listaMesas";
        }

    }

    @GetMapping(value = { "/new"})
    public String crearMesa(@ModelAttribute("mesa")Mesa mesa) {
        return "Admin/newFrm";

    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute ("mesa") @Valid Mesa mesa, BindingResult bindingResult, Model model, RedirectAttributes attr) {

        if(bindingResult.hasErrors()){

            return "Admin/newFrm";

        }else{
            if (mesa.getId()==null) {
                attr.addFlashAttribute("msg", "Mesa "+mesa.getNombre() + " creada exitosamente");
                mesa.setHabilitado(true);

            } else {
                attr.addFlashAttribute("msg", "Mesa "+ mesa.getNombre() + " actualizada exitosamente");

            }
            mesaRepository.save(mesa);


            return "redirect:/admin/listaMesa";
        }

    }

    @GetMapping("/delete")
    public String borrarMesa(@RequestParam("id") int id,
                                      RedirectAttributes attr) {

        Optional<Mesa> optionalDispositivo = mesaRepository.findById(id);

        if (optionalDispositivo.isPresent()) {
            mesaRepository.eliminarMesaPorId(id);
            attr.addFlashAttribute("msg", "Mesa borrada exitosamente");
        }
        return "redirect:/admin/listaMesas";

    }

}
