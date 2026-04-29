package com.vitalis.vitatrack.modules.nutrition.controller;

import com.vitalis.vitatrack.modules.nutrition.dto.NutritionSummaryResponse;
import com.vitalis.vitatrack.modules.nutrition.service.NutritionSummaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/nutrition-summary")
@RequiredArgsConstructor
public class NutritionSummaryController {

    private final NutritionSummaryService service;

    @GetMapping("/users/{userId}/date/{data}")
    public NutritionSummaryResponse getSummary(
            @PathVariable Long userId,
            @PathVariable LocalDate data
    ) {
        return service.getSummary(userId, data);
    }
}