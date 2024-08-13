package com.example.lojaDeBrinquedos.domain;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Data
@Table(name = "TB_CLASSIFICACAO")
@Entity(name = "TB_CLASSIFICACAO")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Classificacao {

    @Id
    private int id_classificacao;

    private String nome_classificacao;
}
