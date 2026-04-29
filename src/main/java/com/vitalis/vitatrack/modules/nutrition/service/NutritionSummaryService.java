package com.vitalis.vitatrack.modules.nutrition.service;

import com.vitalis.vitatrack.modules.nutrition.dto.DailyFoodLogResponse;
import com.vitalis.vitatrack.modules.nutrition.dto.NutritionSummaryResponse;
import com.vitalis.vitatrack.modules.user.domain.User;
import com.vitalis.vitatrack.modules.user.repository.UserRepository;
import com.vitalis.vitatrack.modules.user.service.UserHealthService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class NutritionSummaryService {

    private final UserRepository userRepository;
    private final UserHealthService userHealthService;
    private final DailyFoodLogService dailyFoodLogService;

    public NutritionSummaryResponse getSummary(Long userId, LocalDate data) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        DailyFoodLogResponse log = dailyFoodLogService.findByUserAndDate(userId, data);

        double tmb = userHealthService.calcularTmb(
                user.getPeso(),
                user.getAltura(),
                user.getIdade(),
                user.getGender()
        );

        double get = userHealthService.calcularGet(
                tmb,
                user.getNivelAtividade()
        );

        var macros = userHealthService.calcularMacros(
                user.getPeso(),
                get,
                user.getObjetivo()
        );

        return new NutritionSummaryResponse(
                user.getId(),
                data,

                round(get),
                log.totalCalorias(),
                round(get - log.totalCalorias()),
                log.totalCalorias() > get,

                macros.proteinas(),
                log.totalProteinas(),
                round(macros.proteinas() - log.totalProteinas()),
                log.totalProteinas() > macros.proteinas(),

                macros.carboidratos(),
                log.totalCarboidratos(),
                round(macros.carboidratos() - log.totalCarboidratos()),
                log.totalCarboidratos() > macros.carboidratos(),

                macros.gorduras(),
                log.totalGorduras(),
                round(macros.gorduras() - log.totalGorduras()),
                log.totalGorduras() > macros.gorduras()
        );
    }

    private double round(double value) {
        return Math.round(value * 100.0) / 100.0;
    }
}