package mk.ukim.finki.wp.lab.repository;

import mk.ukim.finki.wp.lab.model.User;
import java.util.List;

public interface UserRepository {
    List<User> findAll();
    User findByUsername(String username);
    User save(User user);
    void deleteByUsername(String username);
}
