package com.vitalis.vitatrack.modules.nutrition.controller;

import com.vitalis.vitatrack.modules.nutrition.domain.Food;
import com.vitalis.vitatrack.modules.nutrition.service.FoodService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/foods")
@RequiredArgsConstructor
public class FoodController {

    private final FoodService foodService;

    @GetMapping
    public List<Food> buscarTodos() {
        return foodService.buscarTodos();
    }

    @GetMapping("/search")
    public List<Food> buscarPorNome(@RequestParam String nome) {
        return foodService.buscarPorNome(nome);
    }
}