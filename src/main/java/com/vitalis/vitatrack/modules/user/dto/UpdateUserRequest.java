package com.vitalis.vitatrack.modules.user.dto;

import com.vitalis.vitatrack.modules.user.domain.ActivityLevel;
import com.vitalis.vitatrack.modules.user.domain.Gender;
import com.vitalis.vitatrack.modules.user.domain.HealthGoal;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UpdateUserRequest(

        @NotBlank
        String nome,

        @Email
        @NotBlank
        String email,

        @NotNull
        Integer idade,

        @NotNull
        Gender gender,

        @NotNull
        Double peso,

        @NotNull
        Double altura,

        @NotNull
        HealthGoal objetivo,

        @NotNull
        ActivityLevel nivelAtividade

) {
}