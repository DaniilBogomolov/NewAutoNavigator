package models;

import java.util.List;

public class User {
    private String username;
    private String password;
    private Long id;
    private Role role;
    private List<Car> favouriteCars;

    public User(String username, String password, Long id) {
        this.username = username;
        this.password = password;
        this.id = id;
    }

    public User(String username, String password, Long id, Role role) {
        this.username = username;
        this.password = password;
        this.id = id;
        this.role = role;
    }

    public User(String username, String password, Role role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public User(String username, String password, Long id, Role role, List<Car> favouriteCars) {
        this.username = username;
        this.password = password;
        this.id = id;
        this.role = role;
        this.favouriteCars = favouriteCars;
    }

    public User(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Car> getFavouriteCars() {
        return favouriteCars;
    }

    public void setFavouriteCars(List<Car> favouriteCars) {
        this.favouriteCars = favouriteCars;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
