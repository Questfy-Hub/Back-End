package com.questifyHub.app.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.questifyHub.app.Entities.User;
import com.questifyHub.app.Services.UserService;
@RestController
@RequestMapping("/user")
public class UserController {


    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id){
        return userService.getUserById(id);
    }

    @GetMapping
    public List <User> getAllUser(){
        return userService.getAllUser();
    }

    @PostMapping
    public User creatUser(@RequestBody User user){
        return userService.createUser(user);
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User user){
        return userService.updateUser(id, user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
    }

    @GetMapping
    public boolean authentication(@RequestBody String email, String password){
        return userService.authentication(email, password);
    }

    /* 
    @PostMapping("/users")
    public String createUser(@RequestBody User user){
        try{
            userRepository.save(user);
            return "User created" + user.toString();
        }catch(Exception e){
            return e.getMessage();
        }
    }

    public void teste(){
        Query q = entityManager.createQuery("select u from User u");
        List<User> users = q.getResultList();
        for(User u : users){
            System.out.println(u.toString());
        }
    }*/

}
