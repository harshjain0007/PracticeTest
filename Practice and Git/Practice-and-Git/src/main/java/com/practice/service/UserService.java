package com.practice.service;

import com.practice.entity.User;
import com.practice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public Optional<User> findById(Long id){
        return userRepository.findById(id);
    }

    public void deleteById(Long id){
        userRepository.deleteById(id);
    }

    public User update(Long id,User user){
        User usser= userRepository.getUserById(id).get();
        if(usser != null){
            usser.setName(user.getName());
            usser.setEmail(user.getEmail());
            return userRepository.save(usser);
        }else {
            return userRepository.save(user);
        }
    }
}
