package com.questifyHub.app.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.questifyHub.app.Entities.Gifts;
import com.questifyHub.app.Exceptions.ResourceNotFoundException;
import com.questifyHub.app.Repositories.GiftsRepository;

@Service
public class GiftsService {
    
    @Autowired
    private GiftsRepository giftsRepository;

    public Gifts getGiftsById(Long id){
        return giftsRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Prêmio não encontradp com o id"+id));

    }
    //TODO: Tratamento de Excessão
    public List<Gifts> getAllGifts(){
        return giftsRepository.findAll();
    }
    //TODO: Tratamento de Excessão
    public Gifts creatGifts(Gifts gifts){
        return giftsRepository.save(gifts);
    }
    //TODO: Tratamento de Excessão
    public Gifts updateGifts(Long id, Gifts giftsDetails){
        Gifts gifts = giftsRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Prêmio não encontrado com o id"+id));

            gifts.setGiftName(giftsDetails.getGiftName());
            gifts.setPrice(giftsDetails.getPrice());
            gifts.setCategory(gifts.getCategory());

        return giftsRepository.save(gifts);

    }
    //TODO: Tratamento de Excessão
    public void deleteGifts(Long id){
        giftsRepository.deleteById(id);
    }
}
