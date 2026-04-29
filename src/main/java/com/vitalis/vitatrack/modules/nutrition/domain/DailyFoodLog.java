package com.vitalis.vitatrack.modules.nutrition.domain;

import com.vitalis.vitatrack.modules.user.domain.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "daily_food_logs")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DailyFoodLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate data;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "dailyFoodLog", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<FoodLogItem> itens = new ArrayList<>();
}