package dev.cs3220project1.cs3220aiapplication.controllers;

import dev.cs3220project1.cs3220aiapplication.DataStore;
import dev.cs3220project1.cs3220aiapplication.models.Meal;
import jakarta.servlet.http.HttpSession;
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
    private final DataStore dataStore;

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
            HttpSession session,
            Model model
    ) {
        // Ensures user is logged in
        String username = (String) session.getAttribute("user");
        if (username == null) {
            return "redirect:/login"; // change if the login URL is different
        }
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
            var mealFromAi = chat.prompt()
                    .user(prompt)
                    .call()
                    .entity(converter);

            // Ensure we have an ID and attach owner + timestamp
            UUID id = mealFromAi.id() != null ? mealFromAi.id() : UUID.randomUUID();

            Meal finalMeal = new Meal(
                    id,
                    username,                         // logged-in user
                    mealFromAi.name(),
                    mealFromAi.type(),
                    mealFromAi.ingredients(),
                    mealFromAi.instructions(),
                    mealFromAi.calories(),
                    mealFromAi.nutrition(),
                    Instant.now()
            );

            dataStore.addMeal(finalMeal);
            model.addAttribute("meal", finalMeal);
            model.addAttribute("status", "Done");
        } catch (Exception e) {
            model.addAttribute("meal", null);
            model.addAttribute("status", "Failed to generate. " + e.getMessage());
        }

        return "ai/assistant";
    }
}
