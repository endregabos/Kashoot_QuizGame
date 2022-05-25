package com.project.kashoot.controller;

import com.project.kashoot.exception.ResourceNotFoundException;
import com.project.kashoot.model.User;
import com.project.kashoot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/")
public class UserController {
    SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    private UserRepository userRepository;

    public UserController(SimpMessagingTemplate simpMessagingTemplate){
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    // get users
    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping("users")
    public List<User> getAllUser(){
        return this.userRepository.findAll();
    }

    // get user by id
    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping("users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable(value = "id") Long user_id) throws ResourceNotFoundException {
        User user = userRepository.findById(user_id).orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + user_id));

        return ResponseEntity.ok().body(user);
    }

    // save user
    @CrossOrigin(origins = "http://localhost:8080")
    @PostMapping("users")
    public User createUser(@RequestBody User user){
        return this.userRepository.save(user);
    }

    // update user
    @CrossOrigin(origins = "http://localhost:8080")
    @PutMapping("users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable(value = "id") Long user_id, @Validated @RequestBody User userDetails) throws ResourceNotFoundException {
        User user = userRepository.findById(user_id).orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + user_id));

        user.setUser_name(userDetails.getUser_name());
        user.setUser_password(userDetails.getUser_password());
        user.setUser_isAdmin(userDetails.isUser_isAdmin());

        simpMessagingTemplate.convertAndSend("/websockets", "Admin edited a user.");

        return ResponseEntity.ok(this.userRepository.save(user));
    }

    // delete user
    @CrossOrigin(origins = "http://localhost:8080")
    @DeleteMapping("users/{id}")
    public Map<String, Boolean> deleteUser(@PathVariable(value = "id") Long user_id) throws ResourceNotFoundException {
        User user = userRepository.findById(user_id).orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + user_id));

        this.userRepository.delete(user);

        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);

        return response;
    }

    //login user
    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping("/login/{username}/{userpassword}")
    public Optional<User> getUserByUsernameAndUserpassword(@PathVariable(value = "username") String username, @PathVariable(value = "userpassword") String userpassword){
        return userRepository.findUserByUsernameAndUserpassword(username, userpassword);
    }

    @MessageMapping("/notification")
    @SendTo("/websockets")
    public String sendNotification(String message) throws Exception {
        return message;
    }

}
