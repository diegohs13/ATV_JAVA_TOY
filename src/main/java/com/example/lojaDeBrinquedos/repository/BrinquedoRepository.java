package com.example.lojaDeBrinquedos.repository;

import com.example.lojaDeBrinquedos.domain.Brinquedo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrinquedoRepository extends JpaRepository<Brinquedo, Integer> {
}
