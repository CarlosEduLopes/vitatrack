package com.vitalis.vitatrack.modules.nutrition.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "foods")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Food {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 500)
    private String nome;

    private Double calorias;

    private Double proteinas;

    private Double carboidratos;

    private Double gorduras;
}