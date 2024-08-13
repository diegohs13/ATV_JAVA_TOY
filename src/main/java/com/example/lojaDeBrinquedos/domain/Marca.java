package com.example.lojaDeBrinquedos.domain;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Data
@Entity(name = "TB_MARCA")
@Table(name = "TB_MARCA")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Marca {

    @Id
    private int id_marca;

    private String nome_marca;

}
