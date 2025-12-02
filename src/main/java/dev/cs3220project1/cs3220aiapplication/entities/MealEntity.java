package dev.cs3220project1.cs3220aiapplication.entities;

import dev.cs3220project1.cs3220aiapplication.models.MealType;
import jakarta.persistence.*;

import java.time.Instant;
import java.util.List;

@Entity
@Table(name = "meals")
public class MealEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Long PK in DB
    private Integer id;

    @Column(nullable = false)
    private String username;   // owner

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MealType type;

    @ElementCollection
    @CollectionTable(
            name = "meal_ingredients",
            joinColumns = @JoinColumn(name = "meal_id")
    )
    private List<IngredientEmbeddable> ingredients;

    @ElementCollection
    @CollectionTable(
            name = "meal_steps",
            joinColumns = @JoinColumn(name = "meal_id")
    )
    @OrderColumn(name = "step_number")  // JPA-managed order index
    private List<StepEmbeddable> instructions;


    @Column
    private Integer calories;

    @Embedded
    private NutritionEmbeddable nutrition;

    @Column(nullable = false)
    private Instant createdAt;

    protected MealEntity() {
        // JPA requires a no-arg constructor
    }

    public MealEntity(
            String username,
            String name,
            MealType type,
            List<IngredientEmbeddable> ingredients,
            List<StepEmbeddable> instructions,
            Integer calories,
            NutritionEmbeddable nutrition,
            Instant createdAt
    ) {
        this.username = username;
        this.name = name;
        this.type = type;
        this.ingredients = ingredients;
        this.instructions = instructions;
        this.calories = calories;
        this.nutrition = nutrition;
        this.createdAt = createdAt;
    }

    // --- Getters & setters (you can use Lombok if allowed) ---

    public Integer getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getName() {
        return name;
    }

    public MealType getType() {
        return type;
    }

    public List<IngredientEmbeddable> getIngredients() {
        return ingredients;
    }

    public List<StepEmbeddable> getInstructions() {
        return instructions;
    }

    public Integer getCalories() {
        return calories;
    }

    public NutritionEmbeddable getNutrition() {
        return nutrition;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(MealType type) {
        this.type = type;
    }

    public void setIngredients(List<IngredientEmbeddable> ingredients) {
        this.ingredients = ingredients;
    }

    public void setInstructions(List<StepEmbeddable> instructions) {
        this.instructions = instructions;
    }

    public void setCalories(Integer calories) {
        this.calories = calories;
    }

    public void setNutrition(NutritionEmbeddable nutrition) {
        this.nutrition = nutrition;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }
}
