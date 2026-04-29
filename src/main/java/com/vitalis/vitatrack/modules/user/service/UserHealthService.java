package com.vitalis.vitatrack.modules.user.service;

import com.vitalis.vitatrack.modules.user.domain.ActivityLevel;
import com.vitalis.vitatrack.modules.user.domain.BmiClassification;
import com.vitalis.vitatrack.modules.user.domain.Gender;
import com.vitalis.vitatrack.modules.user.domain.HealthGoal;
import org.springframework.stereotype.Service;

@Service
public class UserHealthService {

    public double calcularImc(double peso, double altura) {
        return round(peso / (altura * altura));
    }

    public BmiClassification classificarImc(double imc) {
        if (imc < 18.5) return BmiClassification.ABAIXO_DO_PESO;
        if (imc < 25) return BmiClassification.PESO_NORMAL;
        if (imc < 30) return BmiClassification.SOBREPESO;
        if (imc < 35) return BmiClassification.OBESIDADE_GRAU_I;
        if (imc < 40) return BmiClassification.OBESIDADE_GRAU_II;
        return BmiClassification.OBESIDADE_GRAU_III;
    }

    public double calcularTmb(double peso, double altura, int idade, Gender gender) {
        double alturaCm = altura * 100;

        double tmb;

        if (gender == Gender.MASCULINO) {
            tmb = 10 * peso + 6.25 * alturaCm - 5 * idade + 5;
        } else {
            tmb = 10 * peso + 6.25 * alturaCm - 5 * idade - 161;
        }

        return round(tmb);
    }

    public double calcularGet(double tmb, ActivityLevel activityLevel) {
        double fator = switch (activityLevel) {
            case LEVE -> 1.375;
            case MODERADA -> 1.55;
            case INTENSA -> 1.725;
        };

        return round(tmb * fator);
    }

    public Macronutrients calcularMacros(double peso, double get, HealthGoal objetivo) {
        double proteinaPorKg;
        double gorduraPorKg;

        switch (objetivo) {
            case PERDER_PESO -> {
                proteinaPorKg = 2.0;
                gorduraPorKg = 0.8;
            }
            case MANTER_PESO -> {
                proteinaPorKg = 1.6;
                gorduraPorKg = 1.0;
            }
            case GANHAR_MASSA -> {
                proteinaPorKg = 2.2;
                gorduraPorKg = 1.0;
            }
            case MELHORAR_CONDICIONAMENTO -> {
                proteinaPorKg = 1.8;
                gorduraPorKg = 0.9;
            }
            default -> {
                proteinaPorKg = 1.7;
                gorduraPorKg = 1.0;
            }
        }

        double proteinas = peso * proteinaPorKg;
        double gorduras = peso * gorduraPorKg;

        double kcalProteinas = proteinas * 4;
        double kcalGorduras = gorduras * 9;

        double kcalRestantes = get - (kcalProteinas + kcalGorduras);

        double carboidratos = kcalRestantes / 4;

        if (carboidratos < 0) {
            carboidratos = 0;
        }

        return new Macronutrients(
                round(carboidratos),
                round(proteinas),
                round(gorduras)
        );
    }

    public record Macronutrients(
            double carboidratos,
            double proteinas,
            double gorduras
    ) {
    }

    private double round(double value) {
        return Math.round(value * 100.0) / 100.0;
    }
}