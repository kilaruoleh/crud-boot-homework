package co.inventorsoft.demo.dao;

import co.inventorsoft.demo.models.User;

import java.util.List;

public interface UsersDao extends CrudDao{
    List<User> findAllByFirstName(String firstName);
}
