package com.questifyHub.app.Controllers;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
@CrossOrigin({"http://localhost:4200", "https://questfyhub.netlify.app/"})

/** Classe que faz o direcionamento das funções da entidade Task (Tarefas)
 * @author João Paulo Rezende de Oliveira
 */
public class TaskController {

    @Autowired
    private TaskService taskService;

    /** Método para fazer a requisição da função getTaskById
    * 
    * @param id
    * @return Objeto da classe Task que recebe Id como parâmetro
    */
    @GetMapping("/id/{id}")
    public Task getTaskById(@PathVariable Long id){
        return taskService.getTaskById(id);
    }

    /** Método para fazer a requisição da função getAllTask
     * 
     * @return Lista de objetos da classe Task
     */
    @GetMapping
    public List <Task> getAllTask(){
        return taskService.getAllTask();
    }

    /** Método para fazer a requisição da função getTaksByUsername
     * 
     * @param username
     * @return Objeto da classe Task que recebe username como parâmetro
     */
    @GetMapping("/{username}")
    public List<Task> getTaskByUsername(@PathVariable String username){
        return taskService.getTaskByUserName(username);
    }

    /** Método para fazer a requisição da função createTask
     * 
     * @param task
     * @return Objeto da classe Task que recebe task como parâmentro
     */
    @PostMapping
    public Task createTask(@RequestBody Task task){
        return taskService.createTask(task);
    }

    /** Método para fazer a requisição da função updateTask
     * 
     * @param id
     * @param task
     * @return Objeto da classe Task que recebe id e task como parâmetros
     */
    @PatchMapping("/{id}")
    public Task updateTask(@PathVariable Long id, @RequestBody Task task){
        return taskService.updateTask(id, task);
    }

    /** Método para fazer a requisição da função deleteTask
     * 
     * @param id
     */
    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id){
        taskService.deleteTask(id);
    }

    //Area de teste

    /** Método para fazer a requisição da função getTaskById
     * 
     * @param id
     * @return Lista de objetos da classe Task que recebem id como parâmetro
     */
    @GetMapping("/users/{id}/tasks")
    public List<Task> getTaskByUserId(@PathVariable Long id){
        return taskService.getTaskByUserId(id);
    }

    /** Método para fazer a requisição da função getLastTasks
     * 
     * @param username
     * @return Lista de objetos da classe Task que recebem username como parâmetro
     */
    @GetMapping("/{username}/sorted")
    public List<Task> getLastTasks(@PathVariable String username){
        return taskService.getLastTasks(username);
    }

    @GetMapping("/{username}/newest")
    public List<Task> getNewestTasks(@PathVariable String username){
        return taskService.getNewestTasks(username);
    }
    /** Método para fazer a requisição da função completeTask
     * 
     * @param userId
     * @param taskCode
     */
    @PostMapping("/{userId}/{taskCode}/complete")
    public void completeTask(@PathVariable Long userId, @PathVariable Long taskCode) {
    taskService.completeTask(userId, taskCode);
    }

    @GetMapping("/user/{username}/month/{month}")
    public ResponseEntity<List<Task>> getTasksByUserForMonth(@PathVariable String username, @PathVariable int month) {
        List<Task> tasks = taskService.getTaskByUserNameForMonth(username, month);
        return ResponseEntity.ok(tasks);
    }
}

