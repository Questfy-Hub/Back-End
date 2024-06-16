package com.questifyHub.app.Services;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import com.questifyHub.app.Sort.TaskSort;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.questifyHub.app.Entities.Task;
import com.questifyHub.app.Entities.User;
import com.questifyHub.app.Exceptions.ResourceNotFoundException;
import com.questifyHub.app.Repositories.StatusRepository;
import com.questifyHub.app.Repositories.TaskRepository;
import com.questifyHub.app.Repositories.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private StatusRepository statusRepository;
    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Método para requisitar as informações do objeto Task pelo Id
     * 
     * @param id Id da Task
     * @return Objeto Task
     */
    public Task getTaskById(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tarefa não encontrada com o id" + id));

    }

    /**
     * /**
     * Método para requisitar uma lista de todas as tarefas (Task)
     * 
     * @return Lista de todas as tarefas
     */
    public List<Task> getAllTask() {
        return taskRepository.findAll();

    }

    /**
     * Método para criar tarefa (Task)
     * 
     * @param task Tarefa a ser criada
     * @return Objeto Task criado
     */
    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    /**
     * Método para atualizar informações da tarefa
     * 
     * @param id,taskDetails Id da task e informações a ser atualizados
     * @return Objeto Task atualizado
     */
    public Task updateTask(Long id, Task taskDetails) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tarefa não encontrada com o id " + id));

        if (taskDetails.getDificulty() != 0) {
            task.setDificulty(taskDetails.getDificulty());
        }
        if (taskDetails.getInitialDate() != null) {
            task.setInitialDate(taskDetails.getInitialDate());
        }
        if (taskDetails.getEndLineDate() != null) {
            task.setEndLineDate(taskDetails.getEndLineDate());
        }
        if (taskDetails.getLongDescription() != null) {
            task.setLongDescription(taskDetails.getLongDescription());
        }
        if (taskDetails.getShortDescription() != null) {
            task.setShortDescription(taskDetails.getShortDescription());
        }
        if (taskDetails.getConclusionDate() != null) {
            task.setConclusionDate(taskDetails.getConclusionDate());
        }
        if (taskDetails.getPriority() != null) {
            task.setPriority(taskDetails.getPriority());
        }
        if (taskDetails.getStatusTask() != null) {
            task.setStatusTask(taskDetails.getStatusTask());
        }
        if (taskDetails.getUserTask() != null && !taskDetails.getUserTask().isEmpty()) {
            task.setUserTask(taskDetails.getUserTask());
        }

        return taskRepository.save(task);
    }

    // public Task updateTask(Long id, Task taskDetails){
    // Task task = taskRepository.findById(id)
    // .orElseThrow(() -> new ResourceNotFoundException("Tarefa não encontrada com o
    // id"+id));
    // task.setDificulty(taskDetails.getDificulty());
    // task.setInitialDate(taskDetails.getInitialDate());
    // task.setEndLineDate(taskDetails.getEndLineDate());
    // task.setLongDescription(taskDetails.getLongDescription());
    // task.setShortDescription(taskDetails.getShortDescription());
    // return taskRepository.save(task);
    //
    // }

    /**
     * Método para deletar tarefa
     * 
     * @param id Id da Task
     */
    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    // Area de teste
    /**
     * Método para requisitar todas as tarefas por id de usuário
     * 
     * @param userId
     * @return Lista de tarefas com o mesmo usuário
     */
    @Transactional
    public List<Task> getTaskByUserId(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        return user.getTaskUser();
    }

    /**
     * Método para requisitar todas as tarefas por username de usuário
     * 
     * @param username Username
     * @return Lista de tarefas com o mesmo usuário
     */
    public List<Task> getTaskByUserName(String username) {
        User temp = userRepository.getUserByUsername(username);
        return temp.getTaskUser();
    }

    /**
     * Método para requisitar todas as tarefas por username de usuário
     * 
     * @param username,month Username e mês a serem buscados
     * @return Lista de tarefas com o mesmo usuário dentro de um determinado mês
     */
    @Transactional
    public List<Task> getTaskByUserNameForMonth(String username, int month) {
        User user = userRepository.getUserByUsername(username);
        if (user == null) {
            throw new ResourceNotFoundException("Usuário não encontrado com o username " + username);
        }

        int currentYear = LocalDate.now().getYear();
        List<Task> tasksForMonth = new ArrayList<>();

        for (Task task : user.getTaskUser()) {
            if (task.getEndLineDate() != null &&
                    task.getEndLineDate().getMonthValue() == month &&
                    task.getEndLineDate().getYear() == currentYear) {
                tasksForMonth.add(task);
            }
        }

        return tasksForMonth;
    }

    /**
     * Método para fazer requisição de uma lista das ultimas tarefas criadas para um
     * certo usuário
     * 
     * @param username Username do usuário a ser pesquisado
     * @return Lista ordenada de tarefas
     */
    public List<Task> getLastTasks(String username) {
        return TaskSort.descendentTaskSort(this.getTaskByUserName(username));
    }

    /**
     * Método para fazer requisição de uma lista das novas tarefas
     * 
     * @param username Username do usuário a ser pesquisado
     * @return Lista ordenada de tarefas
     */
    public List<Task> getNewestTasks(String username) {
        return TaskSort.sort(this.getTaskByUserName(username));
    }

    /**
     * Método para completar uma tarefa
     * @param userId,taskCode Id do usuário a ser atribuido os pontos e Id da task a ser marcada como completa
     */
    @Transactional
    public void completeTask(Long userId, Long taskCode) {
        Task task = taskRepository.findById(taskCode)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        if (task.getStatusTask().getStatusCode() == 5) {
            throw new RuntimeException("Task already completed");
        }
        Long idStatus = (long) 5;
        task.setStatusTask(this.statusRepository.findById(idStatus).orElse(null));
        taskRepository.save(task);

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        int points = calculatePoints(task);
        user.setPoints(user.getPoints() + points);
        userRepository.save(user);
    }

    /**
     * Método para calcular o decaimento de pontos conforme o tempo
     * @param task Tarefa a ter os pontos calculados
     * @return Pontos da tarefa.
     */

    public int calculatePoints(Task task) {
        int dificulty = task.getDificulty();
        int points;

        switch (dificulty) {
            case 1:
                points = 10;
                // Aplica a redução de pontos para dificuldade 1
                points = applyReduction(points, task, 0.30, 0.90);
                break;
            case 2:
                points = 25;
                // Aplica a redução de pontos para dificuldade 2
                points = applyReduction(points, task, 0.20, 0.80);
                break;
            case 3:
                points = 50;
                // Aplica a redução de pontos para dificuldade 3
                points = applyReduction(points, task, 0.10, 0.70);
                break;
            case 5:
                points = 100;
                // Aplica a redução de pontos para dificuldade 5
                points = applyReduction(points, task, 0.10, 0.70);
                break;
            case 8:
                points = 150;
                // Aplica a redução de pontos para dificuldade 8
                points = applyReduction(points, task, 0.10, 0.70);
                break;
            case 13:
                points = 250;
                // Aplica a redução de pontos para dificuldade 13
                points = applyReduction(points, task, 0.10, 0.70);
                break;
            case 21:
                points = 500;
                // Aplica a redução de pontos para dificuldade 21
                points = applyReduction(points, task, 0.05, 0.70);
                break;
            default:
                points = 0;
        }

        return points;
    }

    /**
     * Método para aplicar a redução dos pontos
     * @param points,task,dailyReduction,maxReduction Pontos as serem modificados, Tarefa, taxa de redução, Redução maxima
     * @return Pontos após a redução
     */
    private int applyReduction(int points, Task task, double dailyReduction, double maxReduction) {
        // Calcula a quantidade de dias que passaram após a conclusionDate
        LocalDate endLineDate = task.getEndLineDate();
        long daysPast = ChronoUnit.DAYS.between(task.getConclusionDate(), endLineDate);

        // Calcula a redução de pontos
        double reductionFactor = dailyReduction * (daysPast * -1);
        // Limita a redução ao máximo especificado
        reductionFactor = Math.min(reductionFactor, maxReduction);
        // Aplica a redução nos pontos
        return (int) (points * (1 - reductionFactor));
    }
}
