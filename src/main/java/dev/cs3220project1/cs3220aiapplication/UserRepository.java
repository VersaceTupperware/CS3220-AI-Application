package dev.cs3220project1.cs3220aiapplication;

import dev.cs3220project1.cs3220aiapplication.User;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class UserRepository {
    private final Map<String, User> usersByEmail = new ConcurrentHashMap<>();

    public boolean existsByEmail(String email) {
        return usersByEmail.containsKey(email.toLowerCase());
    }

    public void save(User user) {
        usersByEmail.put(user.getEmail().toLowerCase(), user);
    }

    public Optional<User> findByEmail(String email) {
        return Optional.ofNullable(usersByEmail.get(email.toLowerCase()));
    }
}
