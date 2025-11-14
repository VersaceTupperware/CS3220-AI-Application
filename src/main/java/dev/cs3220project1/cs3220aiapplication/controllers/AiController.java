package dev.cs3220project1.cs3220aiapplication.controllers;

import dev.cs3220project1.cs3220aiapplication.DataStore;
import dev.cs3220project1.cs3220aiapplication.models.Meal;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.converter.BeanOutputConverter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Controller
public class AiController {

    private final ChatClient chat;
    private DataStore dataStore;

    public AiController(ChatClient.Builder builder, DataStore dataStore) {
        this.chat = builder.build();
        this.dataStore = dataStore;
    }

    @GetMapping("/assistant")
    public String showForm(Model model) {
        model.addAttribute("meal", null);
        model.addAttribute("status", "");
        return "ai/assistant";
    }

    @PostMapping("/ai")
    public String generate(
            @RequestParam(required = false) String mealType,
            @RequestParam(required = false) Integer servings,
            @RequestParam(required = false) Integer calories,
            @RequestParam(required = false, name = "pref") List<String> prefs,
            @RequestParam(required = false) String note,
            Model model
    ) {
        // Build a compact userInput string (same semantics as your old page)
        var sb = new StringBuilder();
        if (mealType != null && !mealType.isBlank()) sb.append("Meal type: ").append(mealType).append(" | ");
        if (servings != null && servings > 0) sb.append("Servings: ").append(servings).append(" | ");
        if (calories != null && calories > 0) sb.append("Max calories: ").append(calories).append(" | ");
        if (prefs != null && !prefs.isEmpty()) sb.append("Dietary: ").append(String.join(", ", prefs)).append(" | ");
        if (note != null && !note.isBlank()) sb.append("Note: ").append(note);
        var userInput = sb.length() == 0 ? "Suggest a healthy meal" : sb.toString();

        var converter = new BeanOutputConverter<>(Meal.class);
        var format = converter.getFormat();

        var prompt = """
            Suggest one healthy meal for the given meal type or preference.
            Use realistic amounts and steps.
            - ingredients must be a JSON array of objects: { item, quantity, unit, notes }
            - instructions must be an ordered JSON array of { number, text } (or at least a list of strings)
            - type must be one of: BREAKFAST, LUNCH, DINNER, SNACK
            - nutrition values are per serving
            Return ONLY valid JSON matching this schema:
            %s

            Input:
            ---
            %s
            ---
            """.formatted(format, userInput);

        try {
            var meal = chat.prompt()
                    .user(prompt)
                    .call()
                    .entity(converter);

            if (meal.id() == null) {
                meal = new Meal(
                        UUID.randomUUID(),
                        meal.name(),
                        meal.type(),
                        meal.ingredients(),
                        meal.instructions(),
                        meal.calories(),
                        meal.nutrition(),
                        Instant.now()
                );
            }

            dataStore.addMeal(meal);
            model.addAttribute("meal", meal);
            model.addAttribute("status", "Done");
        } catch (Exception e) {
            model.addAttribute("meal", null);
            model.addAttribute("status", "Failed to generate. " + e.getMessage());
        }

        return "ai/assistant";
    }
}
