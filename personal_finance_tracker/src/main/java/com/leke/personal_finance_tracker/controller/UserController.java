package com.leke.personal_finance_tracker.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.leke.personal_finance_tracker.model.User;
import com.leke.personal_finance_tracker.service.UserService;


@RestController

@RequestMapping("/user")

public class UserController {

    private UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllUsers(){
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PostMapping
    public ResponseEntity<?> signUp(@RequestBody User user) {
        
        return ResponseEntity.ok(userService.signUp(user));
    }
    

    
}
