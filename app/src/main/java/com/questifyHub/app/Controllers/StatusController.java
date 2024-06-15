package com.questifyHub.app.Controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.questifyHub.app.Entities.Status;
import com.questifyHub.app.Services.StatusService;

@RestController
@RequestMapping("/status")
@CrossOrigin({ "http://localhost:4200", "https://questfyhub.netlify.app/" })

/**
 * Classe que faz o direcionamento das funções da entidade Status (situação das
 * tarefas)
 * 
 * @author João Paulo Rezende de Oliveira
 */
public class StatusController {

    @Autowired
    private StatusService statusService;

    /**
     * Método para fazer a requisição da função getStatusById
     * 
     * @param id
     * @return Objeto da classe Status que recebe id como parâmetro
     */
    @GetMapping("/{id}")
    public Status getStatusById(@PathVariable Long id) {
        return statusService.getStatusById(id);
    }

    /**
     * Método para fazer a requisição da função getAllStatus
     * 
     * @return Lista de Objetos da classe Status
     */
    @GetMapping
    public List<Status> getAllStatus() {
        return statusService.getAllStatus();
    }

    /**
     * Método para fazer a requisição da função createStatus
     * 
     * @param status
     * @return Objeto da classe Status que recebe status como parâmetro
     */
    @PostMapping
    public Status creatStatus(@RequestBody Status status) {
        return statusService.createStatus(status);
    }

    /**
     * Método para fazer a requisição da função updateStatus
     * 
     * @param id
     * @param status
     * @return Objeto da classe Status que recebe id e status como parâmetros
     */
    @PatchMapping("/{id}")
    public Status updateStatus(@PathVariable Long id, @RequestBody Status status) {
        return statusService.updateStatus(id, status);
    }

    /**
     * Método para fazer a requisição da função deleteStatus
     * 
     * @param id
     */
    @DeleteMapping("/{id}")
    public void deleteStatus(@PathVariable Long id) {
        statusService.deleteStatus(id);
    }

}