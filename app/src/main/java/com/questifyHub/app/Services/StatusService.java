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
    //TODO: Atualizar tratamento de excessão
    public Status getStatusById(Long id){
        return statusRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Status não encontrado com o id"+id));

    }
    //TODO: Tratamento de Excessão
    public List<Status> getAllStatus(){
        return statusRepository.findAll();
            
    }
    //TODO: Tratamento de Excessão
    public Status createStatus(Status status){
        return statusRepository.save(status);
    }
    //TODO: Atualizar tratamento de excessão
    public Status updateStatus(Long id, Status statusDetails){
        Status status = statusRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Status não encontrado com o id"+id));

            status.setStatusName(statusDetails.getStatusName());
            status.setDescription(statusDetails.getDescription());

        return statusRepository.save(status);

    }
    //TODO: Tratamento de Excessão
    public void deleteStatus(Long id){
        statusRepository.deleteById(id);   
    }
}
