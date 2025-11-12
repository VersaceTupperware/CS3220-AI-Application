package dev.cs3220project1.cs3220aiapplication;

public class User {
    private final String id;
    private final String firstName;
    private final String lastName;
    private final String email;
    private final String password;

    public User(String id, String firstName, String lastName, String email, String password) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    public String getId() { return id; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }
}
