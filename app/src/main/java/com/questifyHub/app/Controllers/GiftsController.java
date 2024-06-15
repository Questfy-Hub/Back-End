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
import com.questifyHub.app.Entities.Gifts;
import com.questifyHub.app.Services.GiftsService;

/**
 * Classe que faz o direcionamento das funções da entidade Gifts (Itens
 * resgatáveis na loja de pontos)
 * 
 * @author João Paulo Rezende de Oliveira
 */
@RestController
@RequestMapping("/gifts")
@CrossOrigin({ "http://localhost:4200", "https://questfyhub.netlify.app/" })

public class GiftsController {

    @Autowired
    private GiftsService giftsService;

    /**
     * Método para fazer a requisição da função getGiftsById
     * 
     * @param id
     * @return Objeto da classe Gifts que recebe id como parâmetro
     */
    @GetMapping("/{id}")
    public Gifts getGiftsById(@PathVariable Long id) {
        return giftsService.getGiftsById(id);
    }

    /**
     * Método para fazer a requisição da função getAllGifts
     * 
     * @return Lista de objetos da classe Gifts
     */
    @GetMapping
    public List<Gifts> getAllGifts() {
        return giftsService.getAllGifts();
    }

    /**
     * Método para fazer a requisição da função createGifts
     * 
     * @param gifts
     * @return Objeto da classe Gifts que recebe gifts como parâmetro
     */
    @PostMapping
    public Gifts createGifts(@RequestBody Gifts gifts) {
        return giftsService.createGifts(gifts);
    }

    /**
     * Método para fazer a requisição da função updadeGifts
     * 
     * @param id
     * @param gifts
     * @return Objeto da classe Gifts que recebe id e gifts como parâmetros
     */
    @PatchMapping("/{id}")
    public Gifts updateGifts(@PathVariable Long id, @RequestBody Gifts gifts) {
        return giftsService.updateGifts(id, gifts);
    }

    /**
     * Método para fazer a requisição da função deleteGifts
     * 
     * @param id
     */
    @DeleteMapping("/{id}")
    public void deleteGifts(@PathVariable Long id) {
        giftsService.deleteGifts(id);
    }

}
