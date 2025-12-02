package dev.cs3220project1.cs3220aiapplication.controllers;

import dev.cs3220project1.cs3220aiapplication.entities.MealEntity;
import dev.cs3220project1.cs3220aiapplication.mappers.MealMapper;
import dev.cs3220project1.cs3220aiapplication.models.Meal;
import dev.cs3220project1.cs3220aiapplication.repositories.MealRepository;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.converter.BeanOutputConverter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.UUID;

@RestController
public class MyController {

    private final ChatClient chatClient;
    private MealRepository mealRepository;

    public MyController(ChatClient.Builder chatClientBuilder, MealRepository mealRepository) {
        this.chatClient = chatClientBuilder.build();
        this.mealRepository = mealRepository;
    }

    @GetMapping("/ai-rest")
    public Meal generation(@RequestParam String userInput) {
        var converter = new BeanOutputConverter<>(Meal.class);
        var format = converter.getFormat();

        var prompt = """
        Suggest one healthy meal for the given meal type or preference.
        Use realistic amounts and steps.
        - ingredients must be a JSON array of objects: { item, quantity, unit, notes }
        - instructions must be an ordered JSON array of { number, text }
        - type must be one of: BREAKFAST, LUNCH, DINNER, SNACK
        - nutrition values are per serving
        - Return ONLY valid JSON matching this schema:
        %s

        Input:
        ---
        %s
        ---
        """.formatted(format, userInput);

        var meal = this.chatClient.prompt()
                .user(prompt)
                .call()
                .entity(converter);

        // Optional: fill server-side fields if model didn't set them
        if (meal.id() == null) {
            meal = new Meal(
                    null,
                    new String("Test"),
                    meal.name(),
                    meal.type(),
                    meal.ingredients(),
                    meal.instructions(),
                    meal.calories(),
                    meal.nutrition(),
                    Instant.now()
            );
        }

        MealEntity entity = MealMapper.toEntity(meal);
        mealRepository.save(entity);

        return meal;
    }
}
