package dev.cs3220project1.cs3220aiapplication.entities;

import dev.cs3220project1.cs3220aiapplication.models.Ingredient;
import dev.cs3220project1.cs3220aiapplication.models.Unit;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Embeddable
public class IngredientEmbeddable {

    @Column(nullable = false)
    private String item;

    @Column(precision = 10, scale = 2)
    private BigDecimal quantity;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Unit unit;

    @Column
    private String notes;

    protected IngredientEmbeddable() {
    }

    public IngredientEmbeddable(Ingredient ingredient) {
        this.item = ingredient.item();
        this.quantity = ingredient.quantity();
        this.unit = ingredient.unit();
        this.notes = ingredient.notes();
    }

    // Getters

    public String getItem() {
        return item;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public Unit getUnit() {
        return unit;
    }

    public String getNotes() {
        return notes;
    }
}
