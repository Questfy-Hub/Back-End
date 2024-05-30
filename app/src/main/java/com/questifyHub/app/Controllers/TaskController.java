package com.questifyHub.app.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.questifyHub.app.Entities.Task;
import com.questifyHub.app.Services.TaskService;
@RestController
@RequestMapping("/task")
@CrossOrigin("http://localhost:4200")
public class TaskController {


    @Autowired
    private TaskService taskService;

    @GetMapping("/id/{id}")
    public Task getTaskById(@PathVariable Long id){
        return taskService.getTaskById(id);
    }

    @GetMapping
    public List <Task> getAllTask(){
        return taskService.getAllTask();
    }
    @GetMapping("{username}")
    public List<Task> getTaskByUsername(@PathVariable String username){
        return taskService.getTaskByUserName(username);
    }
    @PostMapping
    public Task creatTask(@RequestBody Task task){
        return taskService.createTask(task);
    }

    @PatchMapping("/{id}")
    public Task updateTask(@PathVariable Long id, @RequestBody Task task){
        return taskService.updateTask(id, task);
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id){
        taskService.deleteTask(id);
    }

    //Area de teste

    @GetMapping("/users/{id}/tasks")
    public List<Task> getTaskByUserId(@PathVariable Long id){
        return taskService.getTaskByUserId(id);
    }

    @PostMapping("/{taskCode}/complete")
    public void completeTask(@RequestParam Long userId, @PathVariable Long taskCode) {
    taskService.completeTask(userId, taskCode);
    }
}

