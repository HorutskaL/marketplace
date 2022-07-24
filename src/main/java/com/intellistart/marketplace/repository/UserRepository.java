package com.intellistart.marketplace.repository;

import com.intellistart.marketplace.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByFullName(String fullName);
    public Optional<Object> findUserByFirstName(String firstName);
    public Optional<Object> findUserByLastName(String firstName);

}
