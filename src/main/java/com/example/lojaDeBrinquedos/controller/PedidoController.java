package com.example.lojaDeBrinquedos.controller;

import com.example.lojaDeBrinquedos.domain.Pedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.lojaDeBrinquedos.repository.PedidoRepository;

import java.util.stream.Collectors;
import java.util.Optional;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/pedido")
public class PedidoController {

    @Autowired
    private PedidoRepository pedidoRepository;

    //Retorna todos os pedidos
    @GetMapping
    public ResponseEntity<List<EntityModel<Pedido>>> getAllPedidos() {
        List<EntityModel<Pedido>> todosPedidos = pedidoRepository.findAll().stream()
                .map(pedido -> EntityModel.of(pedido,
                        linkTo(methodOn(PedidoController.class).getPedidoById(pedido.getId_pedido())).withSelfRel(),
                        linkTo(methodOn(PedidoController.class).getAllPedidos()).withRel("todosPedidos"),
                        linkTo(methodOn(PedidoController.class).deletePedido(pedido.getId_pedido())).withRel("delete"),
                        linkTo(methodOn(PedidoController.class).updatePedido(pedido.getId_pedido(), pedido)).withRel("atualizarPedido")))
                .collect(Collectors.toList());

        return ResponseEntity.ok(todosPedidos);
    }

    //Retorna um pedido pelo id
    @GetMapping("/{id_pedido}")
    public ResponseEntity<EntityModel<Pedido>> getPedidoById(@PathVariable Integer id_pedido) {
        Optional<Pedido> pedidoEncontrado = pedidoRepository.findById(id_pedido);

        if (pedidoEncontrado.isPresent()) {
            Pedido pedido = pedidoEncontrado.get();
            EntityModel<Pedido> pedidoModel = EntityModel.of(pedido,
                    linkTo(methodOn(PedidoController.class).getPedidoById(pedido.getId_pedido())).withSelfRel(),
                    linkTo(methodOn(PedidoController.class).getAllPedidos()).withRel("todosPedidos"),
                    linkTo(methodOn(PedidoController.class).deletePedido(pedido.getId_pedido())).withRel("delete"),
                    linkTo(methodOn(PedidoController.class).updatePedido(pedido.getId_pedido(), pedido)).withRel("atualizarPedido"));

            return ResponseEntity.ok(pedidoModel);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //Cadastra um novo pedido
    @PostMapping
    public ResponseEntity<EntityModel<Pedido>> savePedido(@RequestBody Pedido pedido) {
        Pedido novoPedido = pedidoRepository.save(pedido);
        EntityModel<Pedido> pedidoModel = EntityModel.of(novoPedido,
                linkTo(methodOn(PedidoController.class).getPedidoById(novoPedido.getId_pedido())).withSelfRel(),
                linkTo(methodOn(PedidoController.class).getAllPedidos()).withRel("todosPedidos"),
                linkTo(methodOn(PedidoController.class).deletePedido(novoPedido.getId_pedido())).withRel("delete"),
                linkTo(methodOn(PedidoController.class).updatePedido(novoPedido.getId_pedido(), novoPedido)).withRel("atualizarPedido"));

        return ResponseEntity.ok(pedidoModel);
    }

    //Deleta um pedido pelo id
    @DeleteMapping("/{id_pedido}")
    public ResponseEntity<?> deletePedido(@PathVariable Integer id_pedido) {
        if (pedidoRepository.existsById(id_pedido)) {
            pedidoRepository.deleteById(id_pedido);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //Atualiza um pedido
    @PutMapping("/{id_pedido}/atualizarPedido")
    public ResponseEntity<EntityModel<Pedido>> updatePedido(@PathVariable Integer id_pedido, @RequestBody Pedido pedido) {
        Optional<Pedido> pedidoEncontrado = pedidoRepository.findById(id_pedido);

        if (pedidoEncontrado.isPresent()) {
            Pedido pedidoAtualizado = pedidoEncontrado.get();
            pedidoAtualizado.setStatus(pedido.getStatus());
            pedidoRepository.save(pedidoAtualizado);

            EntityModel<Pedido> pedidoModel = EntityModel.of(pedidoAtualizado,
                    linkTo(methodOn(PedidoController.class).getPedidoById(pedidoAtualizado.getId_pedido())).withSelfRel(),
                    linkTo(methodOn(PedidoController.class).getAllPedidos()).withRel("todosPedidos"),
                    linkTo(methodOn(PedidoController.class).deletePedido(pedidoAtualizado.getId_pedido())).withRel("delete"),
                    linkTo(methodOn(PedidoController.class).updatePedido(pedidoAtualizado.getId_pedido(), pedidoAtualizado)).withRel("atualizarPedido"));

            return ResponseEntity.ok(pedidoModel);
        } else {
            return ResponseEntity.notFound().build();
        }

    }
}
