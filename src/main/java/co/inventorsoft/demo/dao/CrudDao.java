package co.inventorsoft.demo.dao;

import co.inventorsoft.demo.models.User;

import java.util.List;
import java.util.Optional;

public interface CrudDao {
    Optional<User> find(Long id);
    void save(User model);
    void update(User model);
    void delete(Long id);
    List<User> findAll();
}
