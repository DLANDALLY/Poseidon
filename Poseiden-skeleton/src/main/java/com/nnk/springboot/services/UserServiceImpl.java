package com.nnk.springboot.services;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;
import com.nnk.springboot.services.interfaces.IUser;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl extends CrudServiceImpl<User, Integer> implements IUser {
    @Autowired
    private BCryptPasswordEncoder encoder;

    public UserServiceImpl(JpaRepository<User, Integer> repository, ModelMapper modelMapper) {
        super(repository, modelMapper);
    }

    @Override
    public void saveUser(User user) {
        if (user == null)
            throw new IllegalArgumentException("User cannot be null");

        user.setPassword(encoder.encode(user.getPassword()));
        saving(user);
    }

    @Override
    public List<User> getAllUsers() {
        return getAll();
    }

    @Override
    public void updateUser(Integer id, User user) {
        if (user == null)
            throw new IllegalArgumentException("User can't be null");

        user.setPassword(encoder.encode(user.getPassword()));
        update(id, user);
    }

    @Override
    public User getUserById(int id){
        return getById(id);
    }
}
