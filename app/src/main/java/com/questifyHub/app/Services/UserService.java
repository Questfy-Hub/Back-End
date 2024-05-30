package com.questifyHub.app.Services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


import com.questifyHub.app.Entities.Task;
import com.questifyHub.app.Model.AuthResponse;
import com.questifyHub.app.Repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.questifyHub.app.Entities.User;
import com.questifyHub.app.DTOs.UserDTO;

import com.questifyHub.app.Exceptions.ResourceNotFoundException;
import com.questifyHub.app.Regex.Regex;
import com.questifyHub.app.Repositories.UserRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;


@Service
public class UserService {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TaskRepository taskRepository;

    // TODO: Atualizar tratamento de excessão
    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado com o id" + id));

    }
    public User getUserByEmail(String email) {
        return userRepository.getUserByEmail(email);
    }
    public User getUserByUsername(String username) {
        return userRepository.getUserByUsername(username);
    }

    // TODO: Tratamento de Excessão
    public List<User> getAllUser() {
        return userRepository.findAll();

    }

    // TODO: Tratamento de Excessão
    public User createUser(User user) {
        user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
        return userRepository.save(user);
    }

    // TODO: Atualizar tratamento de excessão
    public User updateUser(Long id, User userDetails) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado com o id" + id));

        user.setFullname(userDetails.getFullname());
        user.setPassword(userDetails.getPassword());
        user.setEmail(userDetails.getEmail());
        user.setCpf(userDetails.getCpf());
        user.setRole(userDetails.getRole());
        user.setUsername(userDetails.getUsername());

        return userRepository.save(user);

    }

    // TODO: Tratamento de Excessão
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public List<UserDTO> getAllJustUsers() {
        Query query = entityManager.createQuery("select new com.questifyHub.app.DTOs.UserDTO(u.username, u.email, u.role, u.points) from User u");
        return query.getResultList();
    }



    public AuthResponse authentication(String login, String password) {

        try {
            if (Regex.validateEmail(login)) {
                User temp = userRepository.getUserByEmail(login);
                if(temp != null){
                    if (BCrypt.checkpw(password, temp.getPassword())) {
                        return new AuthResponse(true, "Autenticado com sucesso", temp.getUsername(), temp.getCompanyUser().getCompanyCode());
                    }else{
                        return new AuthResponse(false, "Senha Incorreta");
                    }
                }else{
                    return new AuthResponse(false, "Email incorreto");
                }

            }else{
                return new AuthResponse(false, "Formato de email incorreto");
            }
        }catch (Exception e) {
            System.err.println(e.getMessage());
            return new AuthResponse(false, e.getMessage());
        }
    }

    //region area de teste
    public void assignTask(Long id, Long task){
        //Pega o usuário
        User temp = this.userRepository.findById(id).orElse(null);
        //pega as infos da task
        Task taskTemp = this.taskRepository.findById(task).orElse(null);
        //coloca task dentro de uma lista
        List<Task> taskList = new ArrayList<>();

        //setTaskList(taks)
        taskList.add(taskTemp);
        temp.setTaskUser(taskList);
        //Salva usuário
        this.userRepository.save(temp);


    }
    //endregion


}
