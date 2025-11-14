package dev.cs3220project1.cs3220aiapplication.controllers;

import dev.cs3220project1.cs3220aiapplication.DataStore;
import dev.cs3220project1.cs3220aiapplication.models.Meal;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
public class MealController {

    private final DataStore dataStore;

    public MealController(DataStore dataStore) {
        this.dataStore = dataStore;
    }

    @GetMapping("/meals")
    public String listMeals(HttpSession session, Model model) {
        String username = (String) session.getAttribute("user");
        if (username == null) {
            return "redirect:/login";
        }

        List<Meal> allMeals = dataStore.getMeals(); // assumes this exists
        List<Meal> myMeals = allMeals.stream()
                .filter(m -> username.equals(m.username()))
                .toList();

        model.addAttribute("meals", myMeals);
        model.addAttribute("username", username);

        return "ai/meals";  // ai/meals.jte
    }

    @GetMapping("/meals/{id}")
    public String viewMeal(
            @PathVariable("id") UUID id,
            HttpSession session,
            Model model
    ) {
        String username = (String) session.getAttribute("user");
        if (username == null) {
            return "redirect:/login";
        }

        List<Meal> allMeals = dataStore.getMeals();
        Optional<Meal> mealOpt = allMeals.stream()
                .filter(m -> m.id().equals(id) && username.equals(m.username()))
                .findFirst();

        if (mealOpt.isEmpty()) {
            model.addAttribute("message", "Meal not found or you do not have access.");
            return "error"; // use an existing error page if you have one
        }

        model.addAttribute("meal", mealOpt.get());
        model.addAttribute("username", username);

        return "ai/meal";   // ai/meal.jte
    }
}
