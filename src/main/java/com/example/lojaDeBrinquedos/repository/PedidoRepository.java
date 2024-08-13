package com.example.lojaDeBrinquedos.repository;

import com.example.lojaDeBrinquedos.domain.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {
}
