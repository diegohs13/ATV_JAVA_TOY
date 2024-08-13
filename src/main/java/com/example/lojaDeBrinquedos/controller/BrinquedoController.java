package com.example.lojaDeBrinquedos.controller;

import com.example.lojaDeBrinquedos.domain.Brinquedo;
import com.example.lojaDeBrinquedos.repository.BrinquedoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/brinquedo")
public class BrinquedoController {

    @Autowired
    private BrinquedoRepository brinquedoRepository;

    //Retorna todos os brinquedos
    @GetMapping
    public ResponseEntity<List<EntityModel<Brinquedo>>> getAllBrinquedos(){
        List<EntityModel<Brinquedo>> todosBrinquedos = brinquedoRepository.findAll().stream()
                .map(brinquedo -> EntityModel.of(brinquedo,
                        linkTo(methodOn(BrinquedoController.class).getBrinquedoById(brinquedo.getId_brinquedo())).withSelfRel(),
                        linkTo(methodOn(BrinquedoController.class).getAllBrinquedos()).withRel("todosBrinquedos"),
                        linkTo(methodOn(BrinquedoController.class).deleteBrinquedo(brinquedo.getId_brinquedo())).withRel("delete"),
                        linkTo(methodOn(BrinquedoController.class).updateBrinquedo(brinquedo.getId_brinquedo(), brinquedo)).withRel("atualizarPreco")))
                .collect(Collectors.toList());
        return ResponseEntity.ok(todosBrinquedos);
    }

    //Retorna um brinquedo pelo id
    @GetMapping("/{id_brinquedo}")
    public ResponseEntity<EntityModel<Brinquedo>> getBrinquedoById(@PathVariable Integer id_brinquedo){
        Optional<Brinquedo> brinquedoEncontrado = brinquedoRepository.findById(id_brinquedo);

        if (brinquedoEncontrado.isPresent()){
            Brinquedo brinquedo = brinquedoEncontrado.get();
            EntityModel<Brinquedo> brinquedoModel = EntityModel.of(brinquedo,
                    linkTo(methodOn(BrinquedoController.class).getBrinquedoById(brinquedo.getId_brinquedo())).withSelfRel(),
                    linkTo(methodOn(BrinquedoController.class).getAllBrinquedos()).withRel("todosBrinquedos"),
                    linkTo(methodOn(BrinquedoController.class).deleteBrinquedo(brinquedo.getId_brinquedo())).withRel("delete"),
                    linkTo(methodOn(BrinquedoController.class).updateBrinquedo(brinquedo.getId_brinquedo(), brinquedo)).withRel("atualizarPreco"));

            return ResponseEntity.ok(brinquedoModel);
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    //Cadastra um novo brinquedo
    @PostMapping
    public ResponseEntity<EntityModel<Brinquedo>> saveBrinquedo(@RequestBody Brinquedo brinquedo){
        Brinquedo novoBrinquedo = brinquedoRepository.save(brinquedo);
        EntityModel<Brinquedo> brinquedoModel = EntityModel.of(novoBrinquedo,
                linkTo(methodOn(BrinquedoController.class).getBrinquedoById(novoBrinquedo.getId_brinquedo())).withSelfRel(),
                linkTo(methodOn(BrinquedoController.class).getAllBrinquedos()).withRel("todosBrinquedos"),
                linkTo(methodOn(BrinquedoController.class).deleteBrinquedo(novoBrinquedo.getId_brinquedo())).withRel("delete"),
                linkTo(methodOn(BrinquedoController.class).updateBrinquedo(novoBrinquedo.getId_brinquedo(), novoBrinquedo)).withRel("atualizarPreco"));

        return ResponseEntity.ok(brinquedoModel);
    }

    //Atualiza o preço de um brinquedo
    @PutMapping("/{id_brinquedo}/atualizarPreco")
    public ResponseEntity<EntityModel<Brinquedo>> updateBrinquedo(@PathVariable Integer id_brinquedo, @RequestBody Brinquedo brinquedo){
        Optional<Brinquedo> brinquedoEncontrado = brinquedoRepository.findById(id_brinquedo);

        if (brinquedoEncontrado.isPresent()){
            Brinquedo brinquedoAtualizado = brinquedoEncontrado.get();
            brinquedoAtualizado.setPreco(brinquedo.getPreco());
            brinquedoRepository.save(brinquedoAtualizado);

            EntityModel<Brinquedo> brinquedoModel = EntityModel.of(brinquedoAtualizado,
                    linkTo(methodOn(BrinquedoController.class).getBrinquedoById(brinquedoAtualizado.getId_brinquedo())).withSelfRel(),
                    linkTo(methodOn(BrinquedoController.class).getAllBrinquedos()).withRel("todosBrinquedos"),
                    linkTo(methodOn(BrinquedoController.class).deleteBrinquedo(brinquedoAtualizado.getId_brinquedo())).withRel("delete"));

            return ResponseEntity.ok(brinquedoModel);
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    //Deleta um brinquedo
    @DeleteMapping("/{id_brinquedo}")
    public ResponseEntity deleteBrinquedo(@PathVariable Integer id_brinquedo){
        Optional<Brinquedo> brinquedoEncontrado = brinquedoRepository.findById(id_brinquedo);

        if (brinquedoEncontrado.isPresent()){
            brinquedoRepository.delete(brinquedoEncontrado.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }

    }
}
