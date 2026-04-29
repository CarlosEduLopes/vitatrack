package com.vitalis.vitatrack.modules.nutrition.dto;

import java.time.LocalDate;

public record NutritionSummaryResponse(

        Long userId,
        LocalDate data,

        Double metaCalorias,
        Double caloriasConsumidas,
        Double caloriasRestantes,
        Boolean excedeuCalorias,

        Double metaProteinas,
        Double proteinasConsumidas,
        Double proteinasRestantes,
        Boolean excedeuProteinas,

        Double metaCarboidratos,
        Double carboidratosConsumidos,
        Double carboidratosRestantes,
        Boolean excedeuCarboidratos,

        Double metaGorduras,
        Double gordurasConsumidas,
        Double gordurasRestantes,
        Boolean excedeuGorduras

) {
}