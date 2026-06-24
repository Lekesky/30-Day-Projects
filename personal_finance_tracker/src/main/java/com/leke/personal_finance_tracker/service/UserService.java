package com.leke.personal_finance_tracker.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.leke.personal_finance_tracker.model.Users;
import com.leke.personal_finance_tracker.repository.UserRepository;

@Service
public class UserService {

    private UserRepository userRepository;
    
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    
    

    public Users signUp(Users userData){
        Users createNewUser = Users.builder()
            .firstName(userData.getFirstName())
            .lastName(userData.getLastName())
            .email(userData.getEmail())
            .password(userData.getPassword())
            .build();
        return userRepository.save(createNewUser);
    }

    public List<Users> getAllUsers(){
        return userRepository.findAll();
    }
}
