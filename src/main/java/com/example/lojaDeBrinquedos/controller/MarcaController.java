package com.example.lojaDeBrinquedos.controller;

import com.example.lojaDeBrinquedos.domain.Marca;
import com.example.lojaDeBrinquedos.repository.MarcaRepository;
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
@RequestMapping("/marca")
public class MarcaController {

    @Autowired
    private MarcaRepository marcaRepository;

    //Retorna todas as marcas
    @GetMapping
    public ResponseEntity<List<EntityModel<Marca>>> getAllMarcas() {
        List<EntityModel<Marca>> todasMarcas = marcaRepository.findAll().stream()
                .map(marca -> EntityModel.of(marca,
                        linkTo(methodOn(MarcaController.class).getMarcaById(marca.getId_marca())).withSelfRel(),
                        linkTo(methodOn(MarcaController.class).getAllMarcas()).withRel("todasMarcas"),
                        linkTo(methodOn(MarcaController.class).deleteMarca(marca.getId_marca())).withRel("delete"),
                        linkTo(methodOn(MarcaController.class).updateMarca(marca.getId_marca(), marca)).withRel("atualizarNome")))
                .collect(Collectors.toList());

        return ResponseEntity.ok(todasMarcas);
    }

    //Retorna uma marca pelo id
    @GetMapping("/{id_marca}")
    public ResponseEntity<EntityModel<Marca>> getMarcaById(@PathVariable Integer id_marca) {
        Optional<Marca> marcaEncontrada = marcaRepository.findById(id_marca);

        if (marcaEncontrada.isPresent()) {
            Marca marca = marcaEncontrada.get();
            EntityModel<Marca> marcaModel = EntityModel.of(marca,
                    linkTo(methodOn(MarcaController.class).getMarcaById(marca.getId_marca())).withSelfRel(),
                    linkTo(methodOn(MarcaController.class).getAllMarcas()).withRel("todasMarcas"),
                    linkTo(methodOn(MarcaController.class).deleteMarca(marca.getId_marca())).withRel("delete"),
                    linkTo(methodOn(MarcaController.class).updateMarca(marca.getId_marca(), marca)).withRel("atualizarNome"));

            return ResponseEntity.ok(marcaModel);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //Cadastra uma nova marca
    @PostMapping
    public ResponseEntity<EntityModel<Marca>> saveMarca(@RequestBody Marca marca) {
        Marca novaMarca = marcaRepository.save(marca);
        EntityModel<Marca> marcaModel = EntityModel.of(novaMarca,
                linkTo(methodOn(MarcaController.class).getMarcaById(novaMarca.getId_marca())).withSelfRel(),
                linkTo(methodOn(MarcaController.class).getAllMarcas()).withRel("todasMarcas"),
                linkTo(methodOn(MarcaController.class).deleteMarca(novaMarca.getId_marca())).withRel("delete"),
                linkTo(methodOn(MarcaController.class).updateMarca(novaMarca.getId_marca(), novaMarca)).withRel("atualizarNome"));

        return ResponseEntity.ok(marcaModel);
    }

    //Atualiza o nome de uma marca
    @PutMapping("/{id_marca}/atualizarNome")
    public ResponseEntity<EntityModel<Marca>> updateMarca(@PathVariable Integer id_marca, @RequestBody Marca marca) {
        Optional<Marca> marcaEncontrada = marcaRepository.findById(id_marca);

        if (marcaEncontrada.isPresent()) {
            Marca marcaAtualizada = marcaEncontrada.get();
            marcaAtualizada.setNome_marca(marca.getNome_marca());
            marcaRepository.save(marcaAtualizada);

            EntityModel<Marca> marcaModel = EntityModel.of(marcaAtualizada,
                    linkTo(methodOn(MarcaController.class).getMarcaById(marcaAtualizada.getId_marca())).withSelfRel(),
                    linkTo(methodOn(MarcaController.class).getAllMarcas()).withRel("todasMarcas"),
                    linkTo(methodOn(MarcaController.class).deleteMarca(marcaAtualizada.getId_marca())).withRel("delete"),
                    linkTo(methodOn(MarcaController.class).updateMarca(marcaAtualizada.getId_marca(), marcaAtualizada)).withRel("atualizarNome"));

            return ResponseEntity.ok(marcaModel);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //Deleta uma marca
    @DeleteMapping("/{id_marca}")
    public ResponseEntity<Void> deleteMarca(@PathVariable Integer id_marca) {
        marcaRepository.deleteById(id_marca);
        return ResponseEntity.noContent().build();
    }
}
