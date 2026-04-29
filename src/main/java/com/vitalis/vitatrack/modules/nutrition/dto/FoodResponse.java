package com.vitalis.vitatrack.modules.nutrition.dto;

public record FoodResponse(
        Long id,
        String nome,
        Double calorias,
        Double proteinas,
        Double carboidratos,
        Double gorduras
) {
}