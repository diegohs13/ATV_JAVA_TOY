package com.example.lojaDeBrinquedos.domain;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Data
@Table(name = "TB_TIPO")
@Entity(name = "TB_TIPO")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Tipo {

    @Id
    private int id_tipo;

    private String nome_tipo;
}
