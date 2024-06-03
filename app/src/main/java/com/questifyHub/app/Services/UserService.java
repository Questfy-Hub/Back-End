package com.questifyHub.app.Services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
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
//        List<Task> taskList = new ArrayList<>();
//
//        //setTaskList(taks)
//        taskList.add(taskTemp);
        //temp.setTaskUser(taskList);
        temp.getTaskUser().add(taskTemp);
        //Salva usuário
        this.userRepository.save(temp);
    }

   /* public List<User> getUserRankingThisMonth() {
        LocalDate startOfMonth = LocalDate.now().with(TemporalAdjusters.firstDayOfMonth());
        LocalDateTime start = startOfMonth.atStartOfDay();
        LocalDateTime end = LocalDateTime.now();

        List<User> users = userRepository.findUsersWithTasksCompletedBetween(start, end);
        users.forEach(user -> user.setPoints(calculateUserPoints(user)));
        
        return quickSort(users);
    }*/

    private int calculateUserPoints(User user) {
        int points = 0;
        LocalDate startOfMonth = LocalDate.now().with(TemporalAdjusters.firstDayOfMonth());

        for (Task task : user.getTaskUser()) {
            if (task.getConclusionDate() != null && task.getConclusionDate().isAfter(startOfMonth)) {
                points += calculatePoints(task);
            }
        }
        return points;
    }

    private int calculatePoints(Task task) {
        int difficulty = task.getDificulty();
        int points;

        switch (difficulty) {
            case 1:
                points = 10;
                break;
            case 2:
                points = 25;
                break;
            case 3:
                points = 50;
                break;
            case 5:
                points = 100;
                break;
            case 8:
                points = 150;
                break;
            case 13:
                points = 250;
                break;
            case 21:
                points = 500;
                break;
            default:
                points = 0;
        }

        return points;
    }

    private List<User> quickSort(List<User> users) {
        if (users.size() <= 1) {
            return users;
        }
        User pivot = users.get(users.size() / 2);
        List<User> less = new ArrayList<>();
        List<User> equal = new ArrayList<>();
        List<User> greater = new ArrayList<>();
        
        for (User user : users) {
            int userPoints = user.getPoints();
            int pivotPoints = pivot.getPoints();
            if (userPoints > pivotPoints) {
                greater.add(user);
            } else if (userPoints < pivotPoints) {
                less.add(user);
            } else {
                equal.add(user);
            }
        }
        
        List<User> sorted = new ArrayList<>();
        sorted.addAll(quickSort(greater));
        sorted.addAll(equal);
        sorted.addAll(quickSort(less));
        return sorted;
    }
    
    //endregion


}
