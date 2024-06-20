package com.questifyHub.app.Services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.questifyHub.app.Entities.Company;
import com.questifyHub.app.Entities.Gifts;
import com.questifyHub.app.Entities.Task;
import com.questifyHub.app.Entities.User;
import com.questifyHub.app.Exceptions.ResourceNotFoundException;
import com.questifyHub.app.Repositories.CompanyRepository;
import com.questifyHub.app.Repositories.GiftsRepository;

import jakarta.transaction.Transactional;

/**
 * Classe GiftsService que possui a logica das funcionalidades do objeto gift (presentes/Itens resgatáveis na loja de pontos)
 */
@Service
public class GiftsService {
    
    @Autowired
    private GiftsRepository giftsRepository;

    @Autowired
    private CompanyRepository companyRepository;



    /**
     * Método para requisitar as informações do objeto Gift pelo Id
     * @param id
     * @return Objeto da classe Gifts
     */
    public Gifts getGiftsById(Long id){
        return giftsRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Prêmio não encontrado com o id"+id));
    }

    /**
     * Metodo para requisitar uma lista de todas os gifts
     * @return Lista de todas os Gifts
     */
    public List<Gifts> getAllGifts(){
        return giftsRepository.findAll();
    }

    /**
     * Método para criar os gifts
     * @param gifts
     * @return Objeto gift
     */
    public Gifts createGifts(Gifts gifts){
        return giftsRepository.save(gifts);
    }

    /**
     * Método para alterar o cadastro dos gifts
     * @param id
     * @param giftsDetails
     * @return objeto gift com a modificação realizada
     */
    public Gifts updateGifts(Long id, Gifts giftsDetails){
        Gifts gifts = giftsRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Prêmio não encontrado com o id"+id));

            gifts.setGiftName(giftsDetails.getGiftName());
            gifts.setPrice(giftsDetails.getPrice());
            gifts.setCategory(gifts.getCategory());

        return giftsRepository.save(gifts);

    }

    /**
     * Método para deletar o cadastro de um gift
     * @param id
     */
    public void deleteGifts(Long id){
        giftsRepository.deleteById(id);
    }

    /**
     * Método para requisitar todos os Itens da loja por código da empresa
     * 
     * @param companyCode
     * @return Lista de itens da loja com a mesma empresa
     */
    @Transactional
    public List<Gifts> getGiftsByCompanyCode(Long companyCode) {
        Company company = companyRepository.getCompanyByCompanyCode(companyCode);
            //   .orElseThrow(() -> new RuntimeException("Empresa não encontrada"));
        return company.getGiftsList();
    }
}
