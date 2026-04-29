package com.vitalis.vitatrack.modules.nutrition.controller;

import com.vitalis.vitatrack.modules.nutrition.dto.AddFoodLogItemRequest;
import com.vitalis.vitatrack.modules.nutrition.dto.DailyFoodLogResponse;
import com.vitalis.vitatrack.modules.nutrition.service.DailyFoodLogService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/daily-food-logs")
@RequiredArgsConstructor
public class DailyFoodLogController {

    private final DailyFoodLogService service;

    @PostMapping("/users/{userId}/date/{data}/items")
    public DailyFoodLogResponse addItem(
            @PathVariable Long userId,
            @PathVariable LocalDate data,
            @RequestBody @Valid AddFoodLogItemRequest request
    ) {
        return service.addItem(userId, data, request);
    }

    @GetMapping("/users/{userId}/date/{data}")
    public DailyFoodLogResponse findByUserAndDate(
            @PathVariable Long userId,
            @PathVariable LocalDate data
    ) {
        return service.findByUserAndDate(userId, data);
    }
}