package dev.cs3220project1.cs3220aiapplication.repositories;

import dev.cs3220project1.cs3220aiapplication.entities.MealEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MealRepository extends JpaRepository<MealEntity, Long> {

    // For "My Meals" per logged-in user
    List<MealEntity> findByUsernameOrderByCreatedAtDesc(String username);
}
