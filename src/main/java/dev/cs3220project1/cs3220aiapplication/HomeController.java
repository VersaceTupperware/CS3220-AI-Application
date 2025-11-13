package dev.cs3220project1.cs3220aiapplication;


import dev.cs3220project1.cs3220aiapplication.User;
import dev.cs3220project1.cs3220aiapplication.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.UUID;

@Controller
public class HomeController {

    private final UserRepository userRepository;

    public HomeController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("isLoggedIn", false);
        model.addAttribute("username", "");
        model.addAttribute("year", 2025);
        return "index";
    }

    @GetMapping("/register")
    public String showRegister(Model model) {
        if (!model.containsAttribute("error")) {
            model.addAttribute("error", "");
        }
        if (!model.containsAttribute("success")) {
            model.addAttribute("success", "");
        }
        return "register";
    }

    @PostMapping("/register")
    public String handleRegister(
            @RequestParam String firstName,
            @RequestParam String lastName,
            @RequestParam String email,
            @RequestParam String password,
            @RequestParam String confirmPassword,
            Model model,
            RedirectAttributes redirectAttributes
    ) {
        if (firstName == null || firstName.isBlank()
                || lastName == null || lastName.isBlank()
                || email == null || email.isBlank()
                || password == null || password.isBlank()) {
            model.addAttribute("error", "All fields are required.");
            return "register";
        }

        if (!password.equals(confirmPassword)) {
            model.addAttribute("error", "Passwords do not match.");
            return "register";
        }

        if (userRepository.existsByEmail(email)) {
            model.addAttribute("error", "Email already registered.");
            return "register";
        }

        User user = new User(UUID.randomUUID().toString(), firstName.trim(), lastName.trim(), email.trim(), password);
        userRepository.save(user);

        redirectAttributes.addFlashAttribute("success", "Account created successfully. You can log in now.");
        return "redirect:/";
    }
}
