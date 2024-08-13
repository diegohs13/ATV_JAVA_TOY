package com.example.lojaDeBrinquedos.repository;

import com.example.lojaDeBrinquedos.domain.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
}
