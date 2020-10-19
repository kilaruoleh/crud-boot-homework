package co.inventorsoft.demo.dao;

import co.inventorsoft.demo.models.User;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class UsersDao implements CrudDao{

    private static final Map<Long, User> userMap = new HashMap<>();

    UsersDao() {
        User user1 = new User(1L, "Luke", "Skywalker");
        User user2 = new User(2L, "Han", "Solo");
        User user3 = new User(3L, "Anakin", "Skywalker");

        userMap.put(user1.getId(), user1);
        userMap.put(user2.getId(), user2);
        userMap.put(user3.getId(), user3);
    }


    @Override
    public Optional<User> find(Long id) {
        return Optional.empty();
    }

    @Override
    public void save(User user) {
        if (user.getId() == null) {
            Long id = (long) (userMap.size() + 1);
            user.setId(id);
            userMap.put(id, user);
        } else userMap.put(user.getId(), user);
    }

    @Override
    public void update(User user) {
        userMap.put(user.getId(), user);
    }

    @Override
    public void delete(Long id) {
        userMap.remove(id);
    }

    @Override
    public List<User> findAll() {
        return new ArrayList<>(userMap.values());
    }
}
