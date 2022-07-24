package com.intellistart.marketplace.controller;

import com.intellistart.marketplace.model.User;
import com.intellistart.marketplace.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public String index(Model model) {
        model.addAttribute("users", userService.findAll());
        return "users/index";
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userService.findOne(id));
        model.addAttribute("products", userService.findOne(id));
        return "users/show";
    }

    @ResponseStatus(HttpStatus.CREATED)
    @GetMapping("/new")
    public String newUser(@ModelAttribute("user") User user) {
        return "users/new";
    }

    @PostMapping
    public String create(@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "users/new";

        userService.save(user);
        return "redirect:/users";
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("user", userService.findOne(id));
        return "users/edit";
    }

    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/{id}")
    public String update(@ModelAttribute("users") @Valid User user, BindingResult bindingResult,
                         @PathVariable("id") int id) {
        if (bindingResult.hasErrors())
            return "users/edit";
        userService.update(id, user);
        return "redirect:/users";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        userService.delete(id);
        return "redirect:/users";
    }

}
