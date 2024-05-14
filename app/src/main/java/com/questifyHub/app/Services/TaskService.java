package com.questifyHub.app.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.questifyHub.app.Entities.Task;
import com.questifyHub.app.Exceptions.ResourceNotFoundException;
import com.questifyHub.app.Repositories.TaskRepository;

@Service
public class TaskService {
    
    @Autowired
    private TaskRepository taskRepository;
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
}
