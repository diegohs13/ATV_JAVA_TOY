package com.example.lojaDeBrinquedos.controller;

import com.example.lojaDeBrinquedos.domain.Cliente;
import com.example.lojaDeBrinquedos.repository.ClienteRepository;
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
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    //Retorna todos os clientes
    @GetMapping
    public ResponseEntity<List<EntityModel<Cliente>>> getAllClientes(){
        List<EntityModel<Cliente>> todosClientes = clienteRepository.findAll().stream()
                .map(cliente -> EntityModel.of(cliente,
                        linkTo(methodOn(ClienteController.class).getClienteById(cliente.getId_cliente())).withSelfRel(),
                        linkTo(methodOn(ClienteController.class).getAllClientes()).withRel("todosClientes"),
                        linkTo(methodOn(ClienteController.class).deleteCliente(cliente.getId_cliente())).withRel("delete"),
                        linkTo(methodOn(ClienteController.class).updateCliente(cliente.getId_cliente(), cliente)).withRel("atualizarTelefone")))
                .collect(Collectors.toList());
        return ResponseEntity.ok(todosClientes);
    }

    //Retorna um cliente pelo id
    @GetMapping("/{id_cliente}")
    public ResponseEntity<EntityModel<Cliente>> getClienteById(@PathVariable Integer id_cliente){
        Optional<Cliente> clienteEncontrado = clienteRepository.findById(id_cliente);

        if (clienteEncontrado.isPresent()){
            Cliente cliente = clienteEncontrado.get();
            EntityModel<Cliente> clienteModel = EntityModel.of(cliente,
                    linkTo(methodOn(ClienteController.class).getClienteById(cliente.getId_cliente())).withSelfRel(),
                    linkTo(methodOn(ClienteController.class).getAllClientes()).withRel("todosClientes"),
                    linkTo(methodOn(ClienteController.class).deleteCliente(cliente.getId_cliente())).withRel("delete"),
                    linkTo(methodOn(ClienteController.class).updateCliente(cliente.getId_cliente(), cliente)).withRel("atualizarTelefone"));

            return ResponseEntity.ok(clienteModel);
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    //Cadastra um novo cliente
    @PostMapping
    public ResponseEntity<EntityModel<Cliente>> saveCliente(@RequestBody Cliente cliente){
        Cliente novoCliente = clienteRepository.save(cliente);
        EntityModel<Cliente> clienteModel = EntityModel.of(novoCliente,
                linkTo(methodOn(ClienteController.class).getClienteById(novoCliente.getId_cliente())).withSelfRel(),
                linkTo(methodOn(ClienteController.class).getAllClientes()).withRel("todosClientes"),
                linkTo(methodOn(ClienteController.class).deleteCliente(novoCliente.getId_cliente())).withRel("delete"),
                linkTo(methodOn(ClienteController.class).updateCliente(novoCliente.getId_cliente(), novoCliente)).withRel("atualizarTelefone"));

        return ResponseEntity.ok(clienteModel);
    }

    //Atualiza o telefone de um cliente
    @PutMapping("/{id_cliente}/atualizarTelefone")
    public ResponseEntity<EntityModel<Cliente>> updateCliente(@PathVariable Integer id_cliente, @RequestBody Cliente cliente){
        Optional<Cliente> clienteEncontrado = clienteRepository.findById(id_cliente);

        if (clienteEncontrado.isPresent()){
            Cliente clienteAtualizado = clienteEncontrado.get();
            clienteAtualizado.setTelefone(cliente.getTelefone());
            clienteRepository.save(clienteAtualizado);

            EntityModel<Cliente> clienteModel = EntityModel.of(clienteAtualizado,
                    linkTo(methodOn(ClienteController.class).getClienteById(clienteAtualizado.getId_cliente())).withSelfRel(),
                    linkTo(methodOn(ClienteController.class).getAllClientes()).withRel("todosClientes"),
                    linkTo(methodOn(ClienteController.class).deleteCliente(clienteAtualizado.getId_cliente())).withRel("delete"),
                    linkTo(methodOn(ClienteController.class).updateCliente(clienteAtualizado.getId_cliente(), clienteAtualizado)).withRel("atualizarTelefone"));

            return ResponseEntity.ok(clienteModel);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //Deleta um cliente
    @DeleteMapping("/{id_cliente}")
    public ResponseEntity<Void> deleteCliente(@PathVariable Integer id_cliente){
        Optional<Cliente> clienteEncontrado = clienteRepository.findById(id_cliente);

        if (clienteEncontrado.isPresent()){
            clienteRepository.deleteById(id_cliente);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
