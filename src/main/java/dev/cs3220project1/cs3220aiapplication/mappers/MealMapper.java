package dev.cs3220project1.cs3220aiapplication.mappers;

import dev.cs3220project1.cs3220aiapplication.entities.*;
import dev.cs3220project1.cs3220aiapplication.models.*;

import java.util.List;
import java.util.stream.IntStream;

public final class MealMapper {

    private MealMapper() {}

    public static MealEntity toEntity(Meal meal) {
        // map value objects
        List<IngredientEmbeddable> ingredientEntities = meal.ingredients().stream()
                .map(IngredientEmbeddable::new)
                .toList();

        List<StepEmbeddable> stepEntities = meal.instructions().stream()
                .map(StepEmbeddable::new)
                .toList();

        NutritionEmbeddable nutritionEmb =
                meal.nutrition() != null ? new NutritionEmbeddable(meal.nutrition()) : null;

        MealEntity entity = new MealEntity(
                meal.username(),
                meal.name(),
                meal.type(),
                ingredientEntities,
                stepEntities,
                meal.calories(),
                nutritionEmb,
                meal.createdAt()
        );

        return entity;
    }

    public static Meal toRecord(MealEntity entity) {
        List<Ingredient> ingredients = entity.getIngredients().stream()
                .map(i -> new Ingredient(
                        i.getItem(),
                        i.getQuantity(),
                        i.getUnit(),
                        i.getNotes()
                ))
                .toList();

        // Use index as the step number (1-based)
        List<Step> steps = IntStream.range(0, entity.getInstructions().size())
                .mapToObj(i -> {
                    var s = entity.getInstructions().get(i);
                    int number = i + 1;          // 1, 2, 3, ...
                    return new Step(number, s.getText());
                })
                .toList();

        Nutrition nutrition = null;
        if (entity.getNutrition() != null) {
            var n = entity.getNutrition();
            nutrition = new Nutrition(
                    n.getProteinGrams(),
                    n.getCarbsGrams(),
                    n.getFatGrams(),
                    n.getFiberGrams(),
                    n.getSodiumMg()
            );
        }

        return new Meal(
                entity.getId(),
                entity.getUsername(),
                entity.getName(),
                entity.getType(),
                ingredients,
                steps,
                entity.getCalories(),
                nutrition,
                entity.getCreatedAt()
        );
    }
}
