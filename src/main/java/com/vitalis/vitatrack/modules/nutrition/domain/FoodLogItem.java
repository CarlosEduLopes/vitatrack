package com.vitalis.vitatrack.modules.nutrition.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "food_log_items")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FoodLogItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double quantidadeGramas;

    @Enumerated(EnumType.STRING)
    private MealType refeicao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "daily_food_log_id")
    private DailyFoodLog dailyFoodLog;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "food_id")
    private Food food;
}