package com.vitalis.vitatrack.modules.nutrition.repository;

import com.vitalis.vitatrack.modules.nutrition.domain.DailyFoodLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface DailyFoodLogRepository extends JpaRepository<DailyFoodLog, Long> {

    Optional<DailyFoodLog> findByUserIdAndData(Long userId, LocalDate data);
}