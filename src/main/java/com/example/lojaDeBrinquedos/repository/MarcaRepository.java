package com.example.lojaDeBrinquedos.repository;

import com.example.lojaDeBrinquedos.domain.Marca;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MarcaRepository extends JpaRepository<Marca, Integer> {
}
