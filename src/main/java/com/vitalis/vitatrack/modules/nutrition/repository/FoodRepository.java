package com.vitalis.vitatrack.modules.nutrition.repository;

import com.vitalis.vitatrack.modules.nutrition.domain.Food;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FoodRepository extends JpaRepository<Food, Long> {

    List<Food> findByNomeContainingIgnoreCase(String nome);
}