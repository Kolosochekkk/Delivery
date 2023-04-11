package com.courcework.delivery.controller;


import com.courcework.delivery.exception.UserNotFoundException;
import com.courcework.delivery.model.LoginDTO;
import com.courcework.delivery.model.User;
import com.courcework.delivery.repository.UserRepository;
import com.courcework.delivery.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000/")
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;

    @PostMapping("/loginuser")
    public ResponseEntity<?> login (@RequestBody LoginDTO loginDTO){

        User user = userService.loginUser(loginDTO);

        if(user == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(user);
    }

    /*@PostMapping("/user")
    User newUser(@RequestBody User newUser, @RequestParam(value = "role") int role) {
        newUser.setRole(role); // установка роли для нового пользователя
        return userRepository.save(newUser);
    }*/

    @PostMapping("/user")
    User newUser(@RequestBody User newUser){
        return userService.createEmployee(newUser);
    }

    @GetMapping("/users")
    List<User> getAllUsers(){
        return userRepository.findAll();
    }

    @GetMapping("/user/{id}")
    User getUserById(@PathVariable Long id){
        return userRepository.findById(id)
                .orElseThrow(()->new UserNotFoundException(id));
    }

    @PutMapping("/user/{id}")
    User updateUser(@RequestBody User newUser, @PathVariable Long id){
        return userRepository.findById(id)
                .map(user -> {
                    user.setUsername(newUser.getUsername());
                    user.setName(newUser.getName());
                    user.setEmail(newUser.getEmail());
                    return userRepository.save(user);
                }).orElseThrow(()->new UserNotFoundException(id));
    }

    @DeleteMapping("/user/{id}")
    String deleteUser(@PathVariable Long id){
        if(!userRepository.existsById(id)){
            throw new UserNotFoundException(id);
        }
        userRepository.deleteById(id);
        return "Пользователь с id "+id+" был успешно удален";
    }

}
