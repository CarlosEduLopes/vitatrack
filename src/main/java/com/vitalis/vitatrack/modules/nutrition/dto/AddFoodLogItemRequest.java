package com.vitalis.vitatrack.modules.nutrition.dto;

import com.vitalis.vitatrack.modules.nutrition.domain.MealType;
import jakarta.validation.constraints.NotNull;

public record AddFoodLogItemRequest(

        @NotNull
        Long foodId,

        @NotNull
        Double quantidadeGramas,

        @NotNull
        MealType refeicao

) {
}