package com.questifyHub.app.Services;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;
import com.questifyHub.app.Entities.Task;
import com.questifyHub.app.Model.AuthResponse;
import com.questifyHub.app.Repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

@Service
public class UserService {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TaskRepository taskRepository;

    /**
     * Método para requisitar as informações do objeto User pelo Id
     * @param id Id do Usuário
     * @return Objeto User
     */
    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado com o id" + id));
    }

    /**
     * Método para requisitar as informações do objeto User pelo email
     * @param email Email do usuário
     * @return Objeto User
     */
    public User getUserByEmail(String email) {
        return userRepository.getUserByEmail(email);
    }

    /**
     * Método para requisitar as informações do objeto User pelo username
     * @param username Username do Usuário
     * @return Objeto User
     */
    public User getUserByUsername(String username) {
        return userRepository.getUserByUsername(username);
    }

    /**
     * /**
     * Método para requisitar uma lista de todos os usuários (User)
     * @return Lista de todos os usuários
     */
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    /**
     * Método para criar um usuário (User)
     * @param user Objeto com os dados do usuário a ser criado
     * @return Objeto User
     */
    public User createUser(User user) {
        user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
        return userRepository.save(user);
    }

    /**
     * Método para atualizar informações do usuário
     * @param id,userDetails Id do usuário e informações a ser atualizados
     * @return Objeto User atualizado
     */
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

    /**Método para deletar usuário
     * @param id Id do Usuário
     */
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    /**
     * Método para requisitar todas as informações da classe UserDTO
     * @return informações selecionadas do Usuário
     */
    public List<UserDTO> getAllJustUsers() {
        Query query = entityManager.createQuery(
                "select new com.questifyHub.app.DTOs.UserDTO(u.username, u.email, u.role, u.points) from User u ");
        return query.getResultList();
    }
/**
     * Método para requisitar todas as informações da classe UserDTO
     * @return informações selecionadas do Usuário
     */
    public List<UserDTO> getUsersByGestor(String gestor) {
        Query query = entityManager.createQuery(
                "select new com.questifyHub.app.DTOs.UserDTO(u.fullname, u.username, u.points, u.email, u.role) " +
                        "from User u where u.gestor = :gestor");
        query.setParameter("gestor", gestor);
        return query.getResultList();
    }

    /**
     * Método de autenticação de informações do Usuário
     * @param login,password
     * @return Resposta da autenticação com as informações adicionais necessárias
     */
    public AuthResponse authentication(String login, String password) {

        try {
            if (Regex.validateEmail(login)) {
                User temp = userRepository.getUserByEmail(login);
                if (temp != null) {
                    if (BCrypt.checkpw(password, temp.getPassword())) {
                        return new AuthResponse(true, "Autenticado com sucesso", temp.getUsername(),
                                temp.getCompanyUser().getCompanyCode());
                    } else {
                        return new AuthResponse(false, "Senha Incorreta");
                    }
                } else {
                    return new AuthResponse(false, "Email incorreto");
                }

            } else {
                return new AuthResponse(false, "Formato de email incorreto");
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return new AuthResponse(false, e.getMessage());
        }
    }

    /**
     * Método para atribuir uma tarefa a um usuário
     * @param userId,taskId Id da task e do usuário a serem atribuidos.
     */
    public void assignTask(Long userId, Long taskId) {
        // Pega o usuário
        User temp = this.userRepository.findById(userId).orElse(null);
        // pega as infos da task
        Task taskTemp = this.taskRepository.findById(taskId).orElse(null);
        // coloca task dentro de uma lista
        temp.getTaskUser().add(taskTemp);
        // Salva usuário
        this.userRepository.save(temp);
    }

    /**
     * Método para calcular os pontos mensais de um usuário
     * @param user Usuário a ter os calcular os pontos
     */
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

    /**
     * Método para calcular os pontos de uma tarefa
     * @param task Tarefa a ter os pontos calculados
     */
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

    /**
     * Método de ordenação de usuários por pontos
     * @param users Lista de usuários a ser ordenada
     * @return Lista ordenada
     */
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
}
