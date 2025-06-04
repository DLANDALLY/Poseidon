package com.nnk.springboot.services;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;
import com.nnk.springboot.services.interfaces.IUser;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl implements IUser {
    private UserRepository userRepository;
    private BCryptPasswordEncoder encoder;
    private ModelMapper modelMapper;

    @Override
    public void saveUser(User user) {
        if (validateUserExists(user.getId()))
            throw new EntityNotFoundException("User already existing");

        user.setPassword(encoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return List.of();
    }

    @Override
    public void updateUser(User user) {
        if (user == null) throw new IllegalArgumentException("User can't be null");

        User userBDD = getUserById(user.getId());
        modelMapper.map(user, userBDD);
        userBDD.setPassword(encoder.encode(userBDD.getPassword()));
    }

    @Override
    public User getUserById(int id){
        if (id == 0) throw new IllegalArgumentException("ID can't be null");

        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No one user found with this ID ="+ id));
    }

    private boolean validateUserExists(int id){
        return userRepository.existsById(id);
    }
}
