package com.vitalis.vitatrack.modules.nutrition.dto;

import java.time.LocalDate;
import java.util.List;

public record DailyFoodLogResponse(

        Long id,
        LocalDate data,

        Double totalCalorias,
        Double totalProteinas,
        Double totalCarboidratos,
        Double totalGorduras,

        List<FoodLogItemResponse> itens

) {
}