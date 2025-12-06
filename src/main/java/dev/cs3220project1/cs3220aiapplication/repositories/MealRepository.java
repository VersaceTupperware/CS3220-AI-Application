package dev.cs3220project1.cs3220aiapplication.repositories;

import dev.cs3220project1.cs3220aiapplication.entities.MealEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MealRepository extends JpaRepository<MealEntity, Long> {

    // For "My Meals" per logged-in user
    List<MealEntity> findAllByUsernameOrderByCreatedAtDesc(String username);

    Optional<MealEntity> findByIdAndUsername(Long id, String username);

}
