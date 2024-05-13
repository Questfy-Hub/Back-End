package com.questifyHub.app.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.questifyHub.app.Entities.Status;
import com.questifyHub.app.Exceptions.ResourceNotFoundException;
import com.questifyHub.app.Repositories.StatusRepository;

@Service
public class StatusService {
    
    @Autowired
    private StatusRepository statusRepository;

    public Status getStatusById(Long id){
        return statusRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Prêmio não encontradp com o id"+id));

    }

    public List<Status> getAllStatus(){
        return statusRepository.findAll();
    }

    public Status creatStatus(Status status){
        return statusRepository.save(status);
    }

    public Status updateStatus(Long id, Status statusDetails){
        Status status = statusRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Prêmio não encontradp com o id"+id));

            status.setStatusName(statusDetails.getStatusName());
            status.setDescription(statusDetails.getDescription());

        return statusRepository.save(status);

    }

    public void deleteStatus(Long id){
        statusRepository.deleteById(id);
    }
}
