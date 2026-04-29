package com.vitalis.vitatrack.modules.nutrition.dto;

import com.vitalis.vitatrack.modules.nutrition.domain.MealType;

public record FoodLogItemResponse(

        Long id,
        String nomeAlimento,
        Double quantidadeGramas,
        MealType refeicao,

        Double calorias,
        Double proteinas,
        Double carboidratos,
        Double gorduras

) {
}