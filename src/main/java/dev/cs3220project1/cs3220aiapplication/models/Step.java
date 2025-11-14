package dev.cs3220project1.cs3220aiapplication.models;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public record Step(
        @Positive int number,
        @NotBlank String text
) {}
