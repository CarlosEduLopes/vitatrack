package com.vitalis.vitatrack.modules.user.dto;

import com.vitalis.vitatrack.modules.user.domain.ActivityLevel;
import com.vitalis.vitatrack.modules.user.domain.BmiClassification;
import com.vitalis.vitatrack.modules.user.domain.Gender;
import com.vitalis.vitatrack.modules.user.domain.HealthGoal;

public record UserResponse(
        Long id,
        String nome,
        String email,

        Integer idade,
        Gender gender,

        Double peso,
        Double altura,

        HealthGoal objetivo,
        ActivityLevel nivelAtividade,

        Double imc,
        BmiClassification classificacaoImc,

        Double tmb,
        Double get,

        Double carboidratosDia,
        Double proteinasDia,
        Double gordurasDia
) {
}