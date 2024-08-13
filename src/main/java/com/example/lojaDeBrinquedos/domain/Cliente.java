package com.example.lojaDeBrinquedos.domain;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Data
@Table(name = "TB_CLIENTE")
@Entity(name = "TB_CLIENTE")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {

    @Id
    private int id_cliente;

    private String nome_cliente;

    private String telefone;

    private String cpf;

}
