package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.exceptions.InvalidUserCredentialsException;
import mk.ukim.finki.wp.lab.exceptions.UsernameAlreadyExistsException;
import mk.ukim.finki.wp.lab.model.User;
import mk.ukim.finki.wp.lab.repository.UserRepository;
import mk.ukim.finki.wp.lab.service.AuthService;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    public AuthServiceImpl(UserRepository userRepository) { this.userRepository = userRepository; }

    @Override
    public User login(String username, String password) {
        User u = userRepository.findByUsername(username);
        if (u == null || !u.getPassword().equals(password))
            throw new InvalidUserCredentialsException();
        return u;
    }

    @Override
    public User register(String firstName, String lastName, String username, String password) {
        if (userRepository.findByUsername(username) != null)
            throw new UsernameAlreadyExistsException(username);
        return userRepository.save(new User(firstName, lastName, username, password));
    }

    @Override
    public void delete(String username) { userRepository.deleteByUsername(username); }
}
