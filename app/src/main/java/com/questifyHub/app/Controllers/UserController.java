package com.questifyHub.app.Controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.questifyHub.app.Entities.User;
import com.questifyHub.app.Services.UserService;
@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/users")
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

    @GetMapping("/auth")
    public boolean authentication(@RequestBody Map<String, Object> request ){
        return userService.authentication((String) request.get("email"), (String) request.get("password"));
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
