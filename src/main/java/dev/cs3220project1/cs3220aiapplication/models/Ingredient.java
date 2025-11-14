package dev.cs3220project1.cs3220aiapplication.models;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record Ingredient(
        @NotBlank String item,                       // e.g., "egg", "spinach"
        @Positive BigDecimal quantity,               // e.g., 2, 0.25
        Unit unit,                                   // e.g., UNIT, CUP, TSP, GRAM
        String notes                                 // optional: “chopped”, “ripe”
) {}
