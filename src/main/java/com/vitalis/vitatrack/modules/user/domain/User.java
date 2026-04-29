package com.vitalis.vitatrack.modules.user.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String email;

    private String senha;

    private Integer idade;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private Double peso;

    private Double altura;

    @Enumerated(EnumType.STRING)
    private HealthGoal objetivo;

    @Enumerated(EnumType.STRING)
    private ActivityLevel nivelAtividade;
}