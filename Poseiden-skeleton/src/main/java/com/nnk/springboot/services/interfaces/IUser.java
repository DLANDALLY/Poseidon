package com.nnk.springboot.services.interfaces;

import com.nnk.springboot.domain.User;
import jakarta.validation.Valid;

import java.util.List;

public interface IUser {
    void saveUser(@Valid User user);

    List<User> getAllUsers();

    void updateUser(Integer id, User user);

    User getUserById(int id);

    User getUserByName(String name);
}
