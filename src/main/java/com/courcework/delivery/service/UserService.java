package com.courcework.delivery.service;

import com.courcework.delivery.exception.UserNotFoundException;
import com.courcework.delivery.model.LoginDTO;
import com.courcework.delivery.model.User;
import com.courcework.delivery.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User loginUser(LoginDTO loginDTO){

        User user = null;
        try{
            user = userRepo.findByUsername(loginDTO.getUsername());
        }catch (Exception e){
            e.printStackTrace();
        }

        String pass = loginDTO.getPassword();
        if(user != null && passwordEncoder.matches(pass, user.getPassword())){
            return user;
        }
        return null;
    }

    public User getUserById(Long id) throws UserNotFoundException {
        return userRepo.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    public User createEmployee(User user){
        String userName = user.getUsername();
        if(userRepo.findByUsername(userName) != null){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "user exist!");
        }

        //user.getRoles().add(Role.USER);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        if (user.getUsername() == null || user.getUsername().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username is required");
        }

        return userRepo.save(user);
    }
    

}