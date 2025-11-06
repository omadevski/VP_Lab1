package mk.ukim.finki.wp.lab.repository.impl;

import mk.ukim.finki.wp.lab.bootstrap.DataHolder;
import mk.ukim.finki.wp.lab.model.User;
import mk.ukim.finki.wp.lab.repository.UserRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class InMemoryUserRepository implements UserRepository {
    @Override public List<User> findAll() { return DataHolder.users; }

    @Override public User findByUsername(String username) {
        return DataHolder.users.stream()
                .filter(u -> u.getUsername().equals(username))
                .findFirst().orElse(null);
    }

    @Override public User save(User user) {
        User existing = findByUsername(user.getUsername());
        if (existing != null) DataHolder.users.remove(existing);
        DataHolder.users.add(user);
        return user;
    }

    @Override public void deleteByUsername(String username) {
        User u = findByUsername(username);
        if (u != null) DataHolder.users.remove(u);
    }
}
