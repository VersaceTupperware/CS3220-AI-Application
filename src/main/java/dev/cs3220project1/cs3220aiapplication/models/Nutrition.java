package dev.cs3220project1.cs3220aiapplication.models;

import jakarta.validation.constraints.Positive;

public record Nutrition(
        @Positive Integer proteinGrams,
        @Positive Integer carbsGrams,
        @Positive Integer fatGrams,
        @Positive Integer fiberGrams,
        @Positive Integer sodiumMg
) {}
