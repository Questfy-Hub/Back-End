package com.questifyHub.app.Controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import com.questifyHub.app.Entities.Company;
import com.questifyHub.app.Services.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.questifyHub.app.Entities.Gifts;
import com.questifyHub.app.Repositories.GiftsRepository;
import com.questifyHub.app.Repositories.UserRepository;
import com.questifyHub.app.Services.GiftsService;
import org.springframework.web.multipart.MultipartFile;

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
    private GiftsRepository giftsRepository;

    @Autowired
    private GiftsService giftsService;
    @Autowired
    private CompanyService companyService;

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
     *
     * @return Objeto da classe Gifts que recebe gifts como parâmetro
     */
    @PostMapping
    public Gifts createGifts(@RequestParam String giftName, @RequestParam Double price ,@RequestParam String category,
                             @RequestParam int company, @RequestParam(required = false) MultipartFile img) throws IOException {
        Gifts gifts;
        Company tempC = companyService.getCompanyById((long) company);
        if(img == null){
            gifts = new Gifts(giftName, price, category, Files.readAllBytes(Paths.get("generic item.png")), tempC);
        }else{
            gifts = new Gifts(giftName, price, category, img.getBytes(), tempC);
        }
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

    @GetMapping("/image/{giftname}")
    public ResponseEntity<Resource> getImage(@PathVariable String giftname) {
        var temp = giftsRepository.getGiftByGiftName(giftname);
        var body = new ByteArrayResource(temp.getImage());

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, "image/jpeg")
                .body(body);
    }

}
