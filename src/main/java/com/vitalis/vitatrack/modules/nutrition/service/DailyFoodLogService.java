package com.vitalis.vitatrack.modules.nutrition.service;

import com.vitalis.vitatrack.modules.nutrition.domain.DailyFoodLog;
import com.vitalis.vitatrack.modules.nutrition.domain.Food;
import com.vitalis.vitatrack.modules.nutrition.domain.FoodLogItem;
import com.vitalis.vitatrack.modules.nutrition.dto.AddFoodLogItemRequest;
import com.vitalis.vitatrack.modules.nutrition.dto.DailyFoodLogResponse;
import com.vitalis.vitatrack.modules.nutrition.dto.FoodLogItemResponse;
import com.vitalis.vitatrack.modules.nutrition.repository.DailyFoodLogRepository;
import com.vitalis.vitatrack.modules.nutrition.repository.FoodRepository;
import com.vitalis.vitatrack.modules.user.domain.User;
import com.vitalis.vitatrack.modules.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DailyFoodLogService {

    private final DailyFoodLogRepository dailyFoodLogRepository;
    private final FoodRepository foodRepository;
    private final UserRepository userRepository;

    public DailyFoodLogResponse addItem(Long userId, LocalDate data, AddFoodLogItemRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        Food food = foodRepository.findById(request.foodId())
                .orElseThrow(() -> new RuntimeException("Alimento não encontrado"));

        DailyFoodLog log = dailyFoodLogRepository.findByUserIdAndData(userId, data)
                .orElseGet(() -> DailyFoodLog.builder()
                        .user(user)
                        .data(data)
                        .build());

        FoodLogItem item = FoodLogItem.builder()
                .dailyFoodLog(log)
                .food(food)
                .quantidadeGramas(request.quantidadeGramas())
                .refeicao(request.refeicao())
                .build();

        log.getItens().add(item);

        DailyFoodLog saved = dailyFoodLogRepository.save(log);

        return toResponse(saved);
    }

    public DailyFoodLogResponse findByUserAndDate(Long userId, LocalDate data) {
        DailyFoodLog log = dailyFoodLogRepository.findByUserIdAndData(userId, data)
                .orElseThrow(() -> new RuntimeException("Registro diário não encontrado"));

        return toResponse(log);
    }

    private DailyFoodLogResponse toResponse(DailyFoodLog log) {
        List<FoodLogItemResponse> itens = log.getItens()
                .stream()
                .map(this::toItemResponse)
                .toList();

        double totalCalorias = itens.stream().mapToDouble(FoodLogItemResponse::calorias).sum();
        double totalProteinas = itens.stream().mapToDouble(FoodLogItemResponse::proteinas).sum();
        double totalCarboidratos = itens.stream().mapToDouble(FoodLogItemResponse::carboidratos).sum();
        double totalGorduras = itens.stream().mapToDouble(FoodLogItemResponse::gorduras).sum();

        return new DailyFoodLogResponse(
                log.getId(),
                log.getData(),
                round(totalCalorias),
                round(totalProteinas),
                round(totalCarboidratos),
                round(totalGorduras),
                itens
        );
    }

    private FoodLogItemResponse toItemResponse(FoodLogItem item) {
        Food food = item.getFood();
        double fator = item.getQuantidadeGramas() / 100.0;

        return new FoodLogItemResponse(
                item.getId(),
                food.getNome(),
                item.getQuantidadeGramas(),
                item.getRefeicao(),
                round(food.getCalorias() * fator),
                round(food.getProteinas() * fator),
                round(food.getCarboidratos() * fator),
                round(food.getGorduras() * fator)
        );
    }

    private double round(double value) {
        return Math.round(value * 100.0) / 100.0;
    }
}