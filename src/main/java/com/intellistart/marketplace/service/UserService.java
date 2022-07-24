package com.intellistart.marketplace.service;

import com.intellistart.marketplace.model.User;
import com.intellistart.marketplace.repository.UserRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findOne(int id) {
        Optional<User> foundUser = userRepository.findById(id);
        return foundUser.orElse(null);
    }

    @Transactional
    public void save(User user) {
        userRepository.save(user);
    }

    public void update(int id, User updatedUser) {
        updatedUser.setId(id);
        userRepository.save(updatedUser);
    }

    public void delete(int id) {
        userRepository.deleteById(id);
    }

    public Optional<User> getUserByFullName(String fullName) {
        return userRepository.findByFullName(fullName);
    }

    public Optional<Object> getUserByFirstName(String firstName) {
        return userRepository.findUserByFirstName(firstName);
    }
    public Optional<Object> getUserByLastName(String lastName) {
        return userRepository.findUserByLastName(lastName);
    }

}
