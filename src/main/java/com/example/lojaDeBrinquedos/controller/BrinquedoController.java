package com.example.lojaDeBrinquedos.controller;

import com.example.lojaDeBrinquedos.domain.Brinquedo;
import com.example.lojaDeBrinquedos.repository.BrinquedoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Controller
@RequestMapping("/brinquedo")
public class BrinquedoController {

    @Autowired
    private BrinquedoRepository brinquedoRepository;

    //Retorna todos os brinquedos
    @GetMapping
    public String getAllBrinquedos(Model model){
        List<Brinquedo> todosBrinquedos = brinquedoRepository.findAll();
        model.addAttribute("brinquedo", todosBrinquedos);
        return "brinquedo/index";
    }


    //Cadastra um novo brinquedo
    @GetMapping("/adicionar")
    public String showAddBrinquedoForm(Model model) {
        model.addAttribute("brinquedo", new Brinquedo());
        return "brinquedo/adicionar";
    }
    @PostMapping("/adicionar")
    public String saveBrinquedo(@ModelAttribute Brinquedo brinquedo) {
        brinquedoRepository.save(brinquedo);
        return "redirect:/brinquedo";
    }


    //Atualiza o preço de um brinquedo
    @GetMapping("/{id_brinquedo}/editarPreco")
    public String showUpdateForm(@PathVariable Integer id_brinquedo, Model model) {
        Optional<Brinquedo> brinquedo = brinquedoRepository.findById(id_brinquedo);

        if (brinquedo.isPresent()) {
            model.addAttribute("brinquedo", brinquedo.get());
            return "brinquedo/atualizar";
        } else {
            return "redirect:/brinquedo";
        }
    }
    @PostMapping("/{id_brinquedo}/atualizarPreco")
    public String updateBrinquedo(@PathVariable Integer id_brinquedo, @ModelAttribute Brinquedo brinquedo) {
        Optional<Brinquedo> brinquedoEncontrado = brinquedoRepository.findById(id_brinquedo);

        if (brinquedoEncontrado.isPresent()) {
            Brinquedo brinquedoAtualizado = brinquedoEncontrado.get();
            brinquedoAtualizado.setPreco(brinquedo.getPreco());
            brinquedoRepository.save(brinquedoAtualizado);

            return "redirect:/brinquedo";
        } else {
            return "redirect:/brinquedo";
        }
    }

    //Deleta um brinquedo
    @PostMapping("/{id_brinquedo}/deletar")
    public String deleteBrinquedo(@PathVariable Integer id_brinquedo) {
        Optional<Brinquedo> brinquedoEncontrado = brinquedoRepository.findById(id_brinquedo);

        if (brinquedoEncontrado.isPresent()) {
            brinquedoRepository.delete(brinquedoEncontrado.get());
        } else {
            return "redirect:/brinquedo";
        }

        return "redirect:/brinquedo";
    }
}
