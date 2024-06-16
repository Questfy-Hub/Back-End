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

    /**
     * Método para pegar Status pelo ID
     * @param id Id do Status
     * @return Objeto Status
     */
    public Status getStatusById(Long id){
        return statusRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Status não encontrado com o id"+id));

    }

    /**
     * Método para buscar todos os tipos de Status
     * @return Lista de Status
     */
    public List<Status> getAllStatus(){
        return statusRepository.findAll();
            
    }
    /**
     * Método para criar Status
     * @param status Status a ser criado
     * @return Objeto Status criado
     */
    public Status createStatus(Status status){
        return statusRepository.save(status);
    }

    /**
     * Método para atualizar informações dos Status
     * @param id,statusDetails Id do Status e informações a ser atualizado
     * @return Objeto Status atualizado
     */
    public Status updateStatus(Long id, Status statusDetails){
        Status status = statusRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Status não encontrado com o id"+id));

            status.setStatusName(statusDetails.getStatusName());
            status.setDescription(statusDetails.getDescription());

        return statusRepository.save(status);

    }
    /**
     * Método para deletar um status
     * @param id Id do Status a ser deletado
     */
    public void deleteStatus(Long id){
        statusRepository.deleteById(id);   
    }
}
