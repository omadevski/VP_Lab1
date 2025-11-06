package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.User;

public interface AuthService {
    User login(String username, String password);
    User register(String firstName, String lastName, String username, String password);
    void delete(String username);
}
