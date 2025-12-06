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
    private final MealMapper mealMapper;

    public MealController(MealRepository mealRepository, MealMapper mealMapper) {
        this.mealRepository = mealRepository;
        this.mealMapper = mealMapper;
    }

    @GetMapping("/meals")
    public String listMeals(HttpSession session, Model model) {
        String username = (String) session.getAttribute("username");
        if (username == null) {
            return "redirect:/login";
        }

        //Gets the meals for this user from the database
        var mealEntities = mealRepository.findAllByUsername(username);

        // Will convert DB entities -> view model
        List<Meal> myMeals = mealEntities.stream()
                .map(mealMapper::toMeal)
                .toList();

        model.addAttribute("meals", myMeals);
        model.addAttribute("username", username);

         // ai/meals.jte
        return "ai/meals";
    }

    @GetMapping("/meals/{id}")
    public String viewMeal(
            @PathVariable("id") Integer id,
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
        Meal meal = mealMapper.toMeal(mealEntity);

        model.addAttribute("meal", meal);
        model.addAttribute("username", username);

        // ai/meal.jte
        return "ai/meal";
    }
}
