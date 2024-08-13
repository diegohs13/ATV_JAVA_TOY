package com.example.lojaDeBrinquedos.controller;

import com.example.lojaDeBrinquedos.domain.Classificacao;
import com.example.lojaDeBrinquedos.repository.ClassificacaoRepository;
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
@RequestMapping("/classificacao")
public class ClassificacaoController {

    @Autowired
    private ClassificacaoRepository classificacaoRepository;

    //Retorna todas as classificacoes
    @GetMapping
    public ResponseEntity<List<EntityModel<Classificacao>>> getAllClassificacoes(){
        List<EntityModel<Classificacao>> todasClassificacoes = classificacaoRepository.findAll().stream()
                .map(classificacao -> EntityModel.of(classificacao,
                        linkTo(methodOn(ClassificacaoController.class).getClassificacaoById(classificacao.getId_classificacao())).withSelfRel(),
                        linkTo(methodOn(ClassificacaoController.class).getAllClassificacoes()).withRel("todasClassificacoes"),
                        linkTo(methodOn(ClassificacaoController.class).deleteClassificacao(classificacao.getId_classificacao())).withRel("delete"),
                        linkTo(methodOn(ClassificacaoController.class).updateClassificacao(classificacao.getId_classificacao(), classificacao)).withRel("atualizarNome")))
                .collect(Collectors.toList());
        return ResponseEntity.ok(todasClassificacoes);
    }

    //Retorna uma classificacao pelo id
    @GetMapping("/{id_classificacao}")
    public ResponseEntity<EntityModel<Classificacao>> getClassificacaoById(@PathVariable Integer id_classificacao){
        Optional<Classificacao> classificacaoEncontrada = classificacaoRepository.findById(id_classificacao);

        if (classificacaoEncontrada.isPresent()){
            Classificacao classificacao = classificacaoEncontrada.get();
            EntityModel<Classificacao> classificacaoModel = EntityModel.of(classificacao,
                    linkTo(methodOn(ClassificacaoController.class).getClassificacaoById(classificacao.getId_classificacao())).withSelfRel(),
                    linkTo(methodOn(ClassificacaoController.class).getAllClassificacoes()).withRel("todasClassificacoes"),
                    linkTo(methodOn(ClassificacaoController.class).deleteClassificacao(classificacao.getId_classificacao())).withRel("delete"),
                    linkTo(methodOn(ClassificacaoController.class).updateClassificacao(classificacao.getId_classificacao(), classificacao)).withRel("atualizarNome"));

            return ResponseEntity.ok(classificacaoModel);
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    //Cadastra uma nova classificacao
    @PostMapping
    public ResponseEntity<EntityModel<Classificacao>> saveClassificacao(@RequestBody Classificacao classificacao){
        Classificacao novaClassificacao = classificacaoRepository.save(classificacao);
        EntityModel<Classificacao> classificacaoModel = EntityModel.of(novaClassificacao,
                linkTo(methodOn(ClassificacaoController.class).getClassificacaoById(novaClassificacao.getId_classificacao())).withSelfRel(),
                linkTo(methodOn(ClassificacaoController.class).getAllClassificacoes()).withRel("todasClassificacoes"),
                linkTo(methodOn(ClassificacaoController.class).deleteClassificacao(novaClassificacao.getId_classificacao())).withRel("delete"),
                linkTo(methodOn(ClassificacaoController.class).updateClassificacao(novaClassificacao.getId_classificacao(), novaClassificacao)).withRel("atualizarNome"));

        return ResponseEntity.ok(classificacaoModel);
    }

    //Atuliza o nome de uma classificacao
    @PutMapping("/{id_classificacao}/atualizarNome")
    public ResponseEntity<EntityModel<Classificacao>> updateClassificacao(@PathVariable Integer id_classificacao, @RequestBody Classificacao classificacao){
        Optional<Classificacao> classificacaoEncontrada = classificacaoRepository.findById(id_classificacao);

        if (classificacaoEncontrada.isPresent()){
            Classificacao classificacaoAtualizada = classificacaoEncontrada.get();
            classificacaoAtualizada.setNome_classificacao(classificacao.getNome_classificacao());
            classificacaoRepository.save(classificacaoAtualizada);

            EntityModel<Classificacao> classificacaoModel = EntityModel.of(classificacaoAtualizada,
                    linkTo(methodOn(ClassificacaoController.class).getClassificacaoById(classificacaoAtualizada.getId_classificacao())).withSelfRel(),
                    linkTo(methodOn(ClassificacaoController.class).getAllClassificacoes()).withRel("todasClassificacoes"),
                    linkTo(methodOn(ClassificacaoController.class).deleteClassificacao(classificacaoAtualizada.getId_classificacao())).withRel("delete"),
                    linkTo(methodOn(ClassificacaoController.class).updateClassificacao(classificacaoAtualizada.getId_classificacao(), classificacaoAtualizada)).withRel("atualizarNome"));

            return ResponseEntity.ok(classificacaoModel);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //Deleta uma classificacao
    @DeleteMapping("/{id_classificacao}")
    public ResponseEntity<EntityModel<Classificacao>> deleteClassificacao(@PathVariable Integer id_classificacao){
        Optional<Classificacao> classificacaoEncontrada = classificacaoRepository.findById(id_classificacao);

        if (classificacaoEncontrada.isPresent()){
            classificacaoRepository.deleteById(id_classificacao);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
