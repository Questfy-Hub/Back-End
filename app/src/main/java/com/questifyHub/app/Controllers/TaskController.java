package com.questifyHub.app.Controllers;

import com.questifyHub.app.Entities.Task;
import com.questifyHub.app.Repositories.TaskRepository;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController()
public class TaskController {
        private final TaskRepository taskRepository;

    public TaskController(TaskRepository taskRepository) {this.taskRepository = taskRepository;}
    @CrossOrigin("http://localhost:4200")
    @GetMapping("/tasks")
    public List<Task> getTasks(){
        return this.taskRepository.findAll();
    }
    @PostMapping("/tasks")
    public String createTask(@RequestBody() Task task ){
        try {
            taskRepository.save(task);
            return "Tarefa criada" + task.toString();
        } catch (Exception e) {
           return e.getMessage();
        }
    }

}
