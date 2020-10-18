package co.inventorsoft.demo.controllers;

import co.inventorsoft.demo.dao.UsersDao;
import co.inventorsoft.demo.forms.UserForm;
import co.inventorsoft.demo.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
public class UsersController {

    @Autowired
    private UsersDao usersDao;

    @RequestMapping(path = "/users", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView getAllUsers() {
        List<User> users = null;
        users = usersDao.findAll();
        ModelAndView modelAndView = new ModelAndView("users");
        modelAndView.addObject("usersFromServer", users);
        return modelAndView;
    }

    @RequestMapping(path = "/users/{user-id}", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView getUserById(@PathVariable("user-id") Long userId) {
        Optional<User> userCandidate = usersDao.find(userId);
        ModelAndView modelAndView = new ModelAndView("users");
        userCandidate.ifPresent(user -> modelAndView.addObject("usersFromServer",
                Collections.singletonList(user)));
        return modelAndView;
    }

    @RequestMapping(path = "/users", method = RequestMethod.POST)
    @ResponseBody
    public String addUser(UserForm userForm) {
        User newUser = User.from(userForm);
        usersDao.save(newUser);
        return "redirect:/users";
    }
}
