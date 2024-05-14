package com.questifyHub.app.Controllers;

import com.questifyHub.app.Entities.User;
import com.questifyHub.app.Repositories.UserRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
//TODO: Atualizar com utilização de Services
@RestController()
public class UserController {
    private final UserRepository userRepository;
    private final EntityManager entityManager;

    public UserController(UserRepository userRepository, EntityManager em, EntityManager entityManager) {this.userRepository = userRepository;
        this.entityManager = entityManager;
    }

    @CrossOrigin({"http://localhost:4200", "https://questfyhub.netlify.app"})
    @GetMapping("/users")
    public List<User> getUsers(){
        teste();
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

    public void teste(){
        Query q = entityManager.createQuery("select u from User u");
        List<User> users = q.getResultList();
        for(User u : users){
            System.out.println(u.toString());
        }
    }

}
