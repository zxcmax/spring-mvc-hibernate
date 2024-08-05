package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import web.models.User;
import web.service.UserService;

@Controller
@RequestMapping({"/", "/users"})
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public ModelAndView getUsers(@RequestParam(required = false) Integer id) {
        ModelAndView modelAndView = new ModelAndView();
        if (id == null) {
            modelAndView.addObject("users", userService.getAllUsers());
        } else {
            modelAndView.addObject("user", userService.getUser(id));
        }
        modelAndView.setViewName("users/users");
        return modelAndView;
    }

    @GetMapping("/add")
    public ModelAndView addUser() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("user", new User());
        modelAndView.setViewName("users/edit");
        return modelAndView;
    }

    @PostMapping("/add")
    public ModelAndView addUser(@ModelAttribute User user) {
        userService.addUser(user);
        return new ModelAndView("redirect:/users?id=" + user.getId());
    }

    @GetMapping("/edit")
    public ModelAndView editUser(@RequestParam Integer id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("user", userService.getUser(id));
        modelAndView.setViewName("users/edit");
        return modelAndView;
    }

    @PostMapping("/edit")
    public ModelAndView editUser(@ModelAttribute User user) {
        userService.editUser(user);
        return new ModelAndView("redirect:/users?id=" + user.getId());
    }

    @GetMapping("/delete")
    public ModelAndView deleteUser(@RequestParam Integer id) {
        userService.deleteUser(userService.getUser(id));
        return new ModelAndView("redirect:/");
    }
}