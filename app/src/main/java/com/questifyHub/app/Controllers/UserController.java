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

/** Classe que faz o direcionamento das funções da entidade User
 * @author João Paulo Rezende de Oliveira
 */
public class UserController {


    @Autowired
    private UserService userService;
    @Autowired
    private CompanyService companyService;

    @Autowired
    private UserRepository userRepository;

    /** Método para fazer a requisição da função getUserById
     * 
     * @param id
     * @return Objeto da classe user que recebe id como parâmetro
     */
    @GetMapping("/id/{id}")
    public User getUserById(@PathVariable Long id){
        return userService.getUserById(id);
    }

    /** Método para fazer a requisição da função getAllUser
     * 
     * @return Lista de objetos da classe User
     */
    @GetMapping
    public List <User> getAllUser(){
        return userService.getAllUser();
    }

    /** Método para fazer a requisição da função getUserByUsername
     * 
     * @param username
     * @return Objeto da classe User que recebe username como parâmentro
     */
    @GetMapping("/{username}")
    public User getUserByUsername(@PathVariable String username){
        return userService.getUserByUsername(username);
    }

    /** Método para fazer a requisição da função getUserByEmail
     * 
     * @param login
     * @return Objeto da clase User que recebe login como parâmentro
     */
    @GetMapping("/mail")
    public User getUserByMail(@RequestParam String login){
        return userService.getUserByEmail(login);
    }

    /** Método para fazer a requisição da função createUser
     * 
     * @param user
     * @return Objeto da classe User que recebe user como parâmetro
     */
    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    /** Método para fazer a requisição da função updateUser
     * 
     * @param id
     * @param user
     * @return Objeto da classe user que recebe id e user como parâmetro
     */
    @PatchMapping("/patch/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User user){
        return userService.updateUser(id, user);
    }

    /** Método para fazer a requisição da função delete User
     * 
     * @param id
     */
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
    }

    /** Método para fazer a requisição da função authentication
     * 
     * @param request
     * @return //! n tendeu
     */
    @PostMapping("/auth")
    public AuthResponse authentication(@RequestBody Map<String, Object> request ){
        AuthResponse temp = userService.authentication((String) request.get("login"), (String) request.get("password"));
        return temp;
    }

    //region Area de teste

    /** Método para fazer a requisição da função getImage //! conferir, pq parece a função mesmo de colocar a img no perfil
     * 
     * @param username
     * @return
     */
    @GetMapping("/image/{username}")
    public ResponseEntity<Resource> getImage(@PathVariable String username) {
        var temp = userRepository.getUserByUsername(username);
        var body = new ByteArrayResource(temp.getImage());

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, "image/jpeg")
                .body(body);
    }

    /** //! ?
     * 
     * @return
     */
    @GetMapping("/teste")
    public ResponseEntity<List<UserDTO>> getSimpleUser() {
        List<UserDTO> users = userService.getAllJustUsers();
        return ResponseEntity.ok(users);
    }

    /** //! login?
     * 
     * @param fullname
     * @param username
     * @param email
     * @param cpf
     * @param role
     * @param password
     * @param img
     * @param company
     * @throws IOException
     */
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

    /** Método para fazer a requisição da função assignUser
     * 
     * @param id
     * @param taskId
     */
    @PostMapping("assign/{id}/{taskId}")
    public void assignUser(@PathVariable Long id, @PathVariable Long taskId){
        this.userService.assignTask(id, taskId);
    }



    
//    @GetMapping("/ranking/this-month")
//    public List<User> getUserRankingThisMonth() {
//        return userService.getUserRankingThisMonth();
//    }

    
    //endregion
}
