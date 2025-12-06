package dev.cs3220project1.cs3220aiapplication.controllers;

import dev.cs3220project1.cs3220aiapplication.mappers.MealMapper;
import dev.cs3220project1.cs3220aiapplication.models.Meal;
import dev.cs3220project1.cs3220aiapplication.repositories.MealRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class MealController {

    private final MealRepository mealRepository;

    public MealController(MealRepository mealRepository) {
        this.mealRepository = mealRepository;
    }

    @GetMapping("/meals")
    public String listMeals(HttpSession session, Model model) {
        String username = (String) session.getAttribute("username");
        if (username == null) {
            return "redirect:/login";
        }

        //Gets the meals for this user from the database
        var mealEntities = mealRepository.findAllByUsernameOrderByCreatedAtDesc(username);

        // Will convert DB entities -> view model
        List<Meal> myMeals = mealEntities.stream()
                .map(MealMapper::toRecord)
                .toList();

        model.addAttribute("meals", myMeals);
        model.addAttribute("username", username);

         // ai/meals.jte
        return "ai/meals";
    }

    @GetMapping("/meals/{id}")
    public String viewMeal(
            @PathVariable("id") Long id,
            HttpSession session,
            Model model
    ) {
        String username = (String) session.getAttribute("username");
        if (username == null) {
            return "redirect:/login";
        }

        // Find by ID AND username (access control)
        var mealEntity = mealRepository.findByIdAndUsername(id, username)
                .orElse(null);

        if (mealEntity == null) {
            model.addAttribute("message", "Meal not found or you do not have access.");
            return "error";
        }

        // Map entity -> view model
        Meal meal = MealMapper.toRecord(mealEntity);

        model.addAttribute("meal", meal);
        model.addAttribute("username", username);

        // ai/meal.jte
        return "ai/meal";
    }
}
