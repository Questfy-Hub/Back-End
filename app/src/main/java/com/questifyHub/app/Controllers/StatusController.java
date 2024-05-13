package com.questifyHub.app.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.questifyHub.app.Entities.Status;
import com.questifyHub.app.Services.StatusService;
@RestController
@RequestMapping("/status")
public class StatusController {


    @Autowired
    private StatusService statusService;

    @GetMapping("/status/{id}")
    public Status getStatusById(@PathVariable Long id){
        return statusService.getStatusById(id);
    }

    @GetMapping
    public List <Status> getAllStatus(){
        return statusService.getAllStatus();
    }

    @PostMapping
    public Status creatStatus(@RequestBody Status status){
        return statusService.creatStatus(status);
    }

    @PutMapping("/status/{id}")
    public Status updateStatus(@PathVariable Long id, @RequestBody Status status){
        return statusService.updateStatus(id, status);
    }

    @DeleteMapping("/status/{id}")
    public void deleteStatus(@PathVariable Long id){
        statusService.deleteStatus(id);
    }

}