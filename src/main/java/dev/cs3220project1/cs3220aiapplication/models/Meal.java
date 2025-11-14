package dev.cs3220project1.cs3220aiapplication.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

public record Meal(
        @JsonProperty("id") UUID id,                 // optional now; useful later
        @JsonProperty("username") String username,
        @NotBlank String name,
        MealType type,                               // e.g., BREAKFAST/LUNCH/DINNER/SNACK
        @NotNull @Size(min = 1) List<Ingredient> ingredients,
        @NotNull @Size(min = 1) List<Step> instructions,
        @Positive Integer calories,
        Nutrition nutrition,                         // optional details (macros, etc.)
        Instant createdAt                            // when generated
) {}

