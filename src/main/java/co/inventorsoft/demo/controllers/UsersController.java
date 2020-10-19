package co.inventorsoft.demo.controllers;

import co.inventorsoft.demo.dao.UsersDao;
import co.inventorsoft.demo.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UsersController {

    @Autowired
    private UsersDao usersDao;

    @GetMapping("/users")
    public ResponseEntity<List<User>> showAllUsers() {
        return ResponseEntity.ok(usersDao.findAll());
    }

    @DeleteMapping("users/{id}")
    public void deleteUser(@PathVariable Long id) {
        usersDao.delete(id);
    }

    @PostMapping("/users")
    @ResponseStatus(HttpStatus.CREATED)
    public void createUser(@RequestBody User user) {
        usersDao.save(new User(user.getId(), user.getFirstName(), user.getLastName()));
    }
}
