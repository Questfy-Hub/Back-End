package com.questifyHub.app.Controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

import com.questifyHub.app.Entities.Company;
import com.questifyHub.app.Model.AuthResponse;
import com.questifyHub.app.Repositories.UserRepository;
import com.questifyHub.app.Services.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.questifyHub.app.DTOs.UserDTO;
import com.questifyHub.app.Entities.User;
import com.questifyHub.app.Services.UserService;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/users")
@CrossOrigin("http://localhost:4200")
public class UserController {


    @Autowired
    private UserService userService;
    @Autowired
    private CompanyService companyService;

    @Autowired
    private UserRepository userRepository;


    @GetMapping("/id/{id}")
    public User getUserById(@PathVariable Long id){
        return userService.getUserById(id);
    }

    @GetMapping
    public List <User> getAllUser(){
        return userService.getAllUser();
    }
    @GetMapping("/{username}")
    public User getUserByUsername(@PathVariable String username){
        return userService.getUserByUsername(username);
    }

    @GetMapping("/mail")
    public User getUserByMail(@RequestParam String login){
        return userService.getUserByEmail(login);
    }

    @PostMapping
    public User creatUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @PatchMapping("/patch/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User user){
        return userService.updateUser(id, user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
    }

    @PostMapping("/auth")
    public AuthResponse authentication(@RequestBody Map<String, Object> request ){
        AuthResponse temp = userService.authentication((String) request.get("login"), (String) request.get("password"));
        return temp;
    }

    //region Area de teste
    @GetMapping("/image/{username}")
    public ResponseEntity<Resource> getImage(@PathVariable String username) {
        var temp = userRepository.getUserByUsername(username);
        var body = new ByteArrayResource(temp.getImage());

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, "image/jpeg")
                .body(body);
    }

    @GetMapping("/teste")
    public ResponseEntity<List<UserDTO>> getSimpleUser() {
        List<UserDTO> users = userService.getAllJustUsers();
        return ResponseEntity.ok(users);
    }


    @CrossOrigin("http://localhost:4200")
    @PostMapping("try")
    public void tryteste(@RequestParam String fullname ,@RequestParam String username, @RequestParam String email,
                         @RequestParam String cpf, @RequestParam String role, @RequestParam String password ,
                         @RequestParam(required = false) MultipartFile img, @RequestParam String company) throws IOException {
        User temp;
        Company c = companyService.getCompanyById(Long.parseLong(company));
        if(img == null){

            temp = new User(fullname, username, email, cpf, role, password, Files.readAllBytes(Paths.get("clipart1297398.png")) ,c);
        }else {
            temp = new User(fullname, username, email, cpf, role, password, img.getBytes(), c);
        }

        System.out.println(temp);
        userService.createUser(temp);
    }

    @PostMapping("assign/{id}/{taskId}")
    public void assignUser(@PathVariable Long id, @PathVariable Long taskId){
        this.userService.assignTask(id, taskId);
    }
    //endregion
}
