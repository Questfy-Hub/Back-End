package com.questifyHub.app.Controllers;

import com.questifyHub.app.Entities.User;
import com.questifyHub.app.Repositories.UserRepository;
import org.springframework.web.bind.annotation.*;

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


    @PostMapping("/users")
    public String createUser(@RequestBody User user){
        try{
            userRepository.save(user);
            return "User created" + user.toString();
        }catch(Exception e){
            return e.getMessage();
        }
    }



}
