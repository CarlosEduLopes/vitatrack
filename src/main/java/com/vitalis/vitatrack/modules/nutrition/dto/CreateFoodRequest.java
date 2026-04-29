package com.vitalis.vitatrack.modules.nutrition.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateFoodRequest(

        @NotBlank
        String nome,

        @NotNull
        Double calorias,

        Double proteinas,
        Double carboidratos,
        Double gorduras

) {}