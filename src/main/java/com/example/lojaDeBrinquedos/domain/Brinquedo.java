package com.example.lojaDeBrinquedos.domain;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Data
@Entity(name = "TB_BRINQUEDO")
@Table(name = "TB_BRINQUEDO")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Brinquedo {

    @Id
    private int id_brinquedo;

    private int id_classificacao;

    private int id_marca;

    private int id_tipo;

    private String nome_brinquedo;

    private double preco;

}
