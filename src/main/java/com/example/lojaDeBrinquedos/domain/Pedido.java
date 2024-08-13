package com.example.lojaDeBrinquedos.domain;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Data
@Table(name = "TB_PEDIDO")
@Entity(name = "TB_PEDIDO")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Pedido {

    @Id
    @Column(name = "id_pedido")
    private int id_pedido;

    private int id_brinquedo;

    private int id_cliente;

    private String status;
}

