package com.vitalis.vitatrack.modules.nutrition.repository;

import com.vitalis.vitatrack.modules.nutrition.domain.FoodLogItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodLogItemRepository extends JpaRepository<FoodLogItem, Long> {
}