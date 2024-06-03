package com.questifyHub.app.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.questifyHub.app.Entities.Status;
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



    //TODO: Atualizar tratamento de excessão
    public Task getTaskById(Long id){
        return taskRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Tarefa não encontrada com o id"+id));

    }
    //TODO: Tratamento de Excessão
    public List<Task> getAllTask(){
        return taskRepository.findAll();
            
    }
    //TODO: Tratamento de Excessão
    public Task createTask(Task task){
        return taskRepository.save(task);
    }
    //TODO: Atualizar tratamento de excessão
    public Task updateTask(Long id, Task taskDetails){
        Task task = taskRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Tarefa não encontrada com o id"+id));
            task.setDificulty(taskDetails.getDificulty());
            task.setInitialDate(taskDetails.getInitialDate());
            task.setEndLineDate(taskDetails.getEndLineDate());
            task.setLongDescription(taskDetails.getLongDescription());
            task.setShortDescription(taskDetails.getShortDescription());

        return taskRepository.save(task);

    }
    //TODO: Tratamento de Excessão
    public void deleteTask(Long id){
        taskRepository.deleteById(id);   
    }

    //Area de teste
    @Transactional
    public List<Task> getTaskByUserId(Long userId) {
        User user = userRepository.findById(userId)
                                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        return user.getTaskUser();
    }
    public List<Task> getTaskByUserName(String userName) {
        User temp = userRepository.getUserByUsername(userName);
        return temp.getTaskUser();
    }
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
}
