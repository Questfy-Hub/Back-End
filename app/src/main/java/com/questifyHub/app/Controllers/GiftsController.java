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

import com.questifyHub.app.Entities.Gifts;
import com.questifyHub.app.Services.GiftsService;
@RestController
@RequestMapping("/gifts")
public class GiftsController {


    @Autowired
    private GiftsService giftsService;

    @GetMapping("/{id}")
    public Gifts getGiftsById(@PathVariable Long id){
        return giftsService.getGiftsById(id);
    }

    @GetMapping
    public List <Gifts> getAllGifts(){
        return giftsService.getAllGifts();
    }

    @PostMapping
    public Gifts creatGifts(@RequestBody Gifts gifts){
        return giftsService.creatGifts(gifts);
    }

    @PutMapping("/{id}")
    public Gifts updateGifts(@PathVariable Long id, @RequestBody Gifts gifts){
        return giftsService.updateGifts(id, gifts);
    }

    @DeleteMapping("/{id}")
    public void deleteGifts(@PathVariable Long id){
        giftsService.deleteGifts(id);
    }

}
