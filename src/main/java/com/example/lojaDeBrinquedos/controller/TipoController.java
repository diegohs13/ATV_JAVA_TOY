package com.example.lojaDeBrinquedos.controller;

import com.example.lojaDeBrinquedos.domain.Tipo;
import com.example.lojaDeBrinquedos.repository.TipoRepository;
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
@RequestMapping("/tipo")
public class TipoController {

    @Autowired
    private TipoRepository tipoRepository;

    //Retorna todos os tipos
    @GetMapping
    public ResponseEntity<List<EntityModel<Tipo>>> getAllTipos() {
        List<EntityModel<Tipo>> todosTipos = tipoRepository.findAll().stream()
                .map(tipo -> EntityModel.of(tipo,
                        linkTo(methodOn(TipoController.class).getTipoById(tipo.getId_tipo())).withSelfRel(),
                        linkTo(methodOn(TipoController.class).getAllTipos()).withRel("todosTipos"),
                        linkTo(methodOn(TipoController.class).deleteTipo(tipo.getId_tipo())).withRel("delete"),
                        linkTo(methodOn(TipoController.class).updateTipo(tipo.getId_tipo(), tipo)).withRel("atualizarNome")))
                .collect(Collectors.toList());

        return ResponseEntity.ok(todosTipos);
    }

    //Retorna um tipo pelo id
    @GetMapping("/{id_tipo}")
    public ResponseEntity<EntityModel<Tipo>> getTipoById(@PathVariable Integer id_tipo) {
        Optional<Tipo> tipoEncontrado = tipoRepository.findById(id_tipo);

        if (tipoEncontrado.isPresent()) {
            Tipo tipo = tipoEncontrado.get();
            EntityModel<Tipo> tipoModel = EntityModel.of(tipo,
                    linkTo(methodOn(TipoController.class).getTipoById(tipo.getId_tipo())).withSelfRel(),
                    linkTo(methodOn(TipoController.class).getAllTipos()).withRel("todosTipos"),
                    linkTo(methodOn(TipoController.class).deleteTipo(tipo.getId_tipo())).withRel("delete"),
                    linkTo(methodOn(TipoController.class).updateTipo(tipo.getId_tipo(), tipo)).withRel("atualizarNome"));

            return ResponseEntity.ok(tipoModel);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //Cadastra um novo tipo
    @PostMapping
    public ResponseEntity<EntityModel<Tipo>> saveTipo(@RequestBody Tipo tipo) {
        Tipo novoTipo = tipoRepository.save(tipo);

        EntityModel<Tipo> tipoModel = EntityModel.of(novoTipo,
                linkTo(methodOn(TipoController.class).getTipoById(novoTipo.getId_tipo())).withSelfRel(),
                linkTo(methodOn(TipoController.class).getAllTipos()).withRel("todosTipos"),
                linkTo(methodOn(TipoController.class).deleteTipo(novoTipo.getId_tipo())).withRel("delete"),
                linkTo(methodOn(TipoController.class).updateTipo(novoTipo.getId_tipo(), novoTipo)).withRel("atualizarNome"));

        return ResponseEntity.ok(tipoModel);
    }

    //Atualiza o nome do tipo
    @PutMapping("/{id_tipo}/atualizarNome")
    public ResponseEntity<EntityModel<Tipo>> updateTipo(@PathVariable Integer id_tipo, @RequestBody Tipo tipo) {
        Optional<Tipo> tipoEncontrado = tipoRepository.findById(id_tipo);

        if (tipoEncontrado.isPresent()) {
            Tipo tipoAtualizado = tipoEncontrado.get();
            tipoAtualizado.setNome_tipo(tipo.getNome_tipo());
            tipoRepository.save(tipoAtualizado);

            EntityModel<Tipo> tipoModel = EntityModel.of(tipoAtualizado,
                    linkTo(methodOn(TipoController.class).getTipoById(tipoAtualizado.getId_tipo())).withSelfRel(),
                    linkTo(methodOn(TipoController.class).getAllTipos()).withRel("todosTipos"),
                    linkTo(methodOn(TipoController.class).deleteTipo(tipoAtualizado.getId_tipo())).withRel("delete"),
                    linkTo(methodOn(TipoController.class).updateTipo(tipoAtualizado.getId_tipo(), tipoAtualizado)).withRel("atualizarNome"));

            return ResponseEntity.ok(tipoModel);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //Deleta um tipo
    @DeleteMapping("/{id_tipo}")
    public ResponseEntity<Void> deleteTipo(@PathVariable Integer id_tipo) {
        Optional<Tipo> tipoEncontrado = tipoRepository.findById(id_tipo);

        if (tipoEncontrado.isPresent()) {
            tipoRepository.deleteById(id_tipo);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
