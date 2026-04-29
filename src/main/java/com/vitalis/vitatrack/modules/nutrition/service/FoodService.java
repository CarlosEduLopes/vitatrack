package com.vitalis.vitatrack.modules.nutrition.service;

import com.vitalis.vitatrack.modules.nutrition.domain.Food;
import com.vitalis.vitatrack.modules.nutrition.repository.FoodRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FoodService {

    private final FoodRepository foodRepository;

    public List<Food> buscarTodos() {
        return foodRepository.findAll();
    }

    public List<Food> buscarPorNome(String nome) {
        return foodRepository.findByNomeContainingIgnoreCase(nome);
    }
}