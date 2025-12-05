package dev.cs3220project1.cs3220aiapplication.entities;

import dev.cs3220project1.cs3220aiapplication.models.Nutrition;
import jakarta.persistence.*;

@Embeddable
public class NutritionEmbeddable {

    private Integer proteinGrams;
    private Integer carbsGrams;
    private Integer fatGrams;
    private Integer fiberGrams;
    private Integer sodiumMg;

    protected NutritionEmbeddable() {
    }

    public NutritionEmbeddable(Nutrition nutrition) {
        this.proteinGrams = nutrition.proteinGrams();
        this.carbsGrams = nutrition.carbsGrams();
        this.fatGrams = nutrition.fatGrams();
        this.fiberGrams = nutrition.fiberGrams();
        this.sodiumMg = nutrition.sodiumMg();
    }

    public Integer getProteinGrams() {
        return proteinGrams;
    }

    public Integer getCarbsGrams() {
        return carbsGrams;
    }

    public Integer getFatGrams() {
        return fatGrams;
    }

    public Integer getFiberGrams() {
        return fiberGrams;
    }

    public Integer getSodiumMg() {
        return sodiumMg;
    }
}
