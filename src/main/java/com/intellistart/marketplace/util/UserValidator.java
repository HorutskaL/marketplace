package com.intellistart.marketplace.util;

import com.intellistart.marketplace.model.User;
import com.intellistart.marketplace.service.UserService;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {
    private final UserService userService;

    public UserValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        User user = (User) o;
        if (userService.getUserByFirstName(user.getFirstName()).isPresent()&&userService.getUserByLastName(user.getLastName()).isPresent()) {
            errors.rejectValue("firstName", "", "User with such name already exists");
        }
    }
}
