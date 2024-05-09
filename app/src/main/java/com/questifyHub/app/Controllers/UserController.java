package com.questifyHub.app.Controllers;

import com.questifyHub.app.Entities.User;
import com.questifyHub.app.Repositories.UserRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController()
public class UserController {
    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {this.userRepository = userRepository;}
    @CrossOrigin("http://localhost:4200")
    @GetMapping("/users")
    public List<User> getUsers(){
        return this.userRepository.findAll();
    }

}
