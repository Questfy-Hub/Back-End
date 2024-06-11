package com.questifyHub.app.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.questifyHub.app.Entities.User;
import com.questifyHub.app.Services.RankingService;
import java.util.List;

@RestController
@RequestMapping("/ranking")
@CrossOrigin("http://localhost:4200")

/** Classe que faz o direcionamento das funções da entidade Ranking
 * @author João Paulo Rezende de Oliveira
 */
public class RankingController {

    @Autowired
    private RankingService rankingService;

    /** Método para fazer a requisição da função getMonthlyRanking
     * 
     * @return Lista de objetos //! O que retorna?
     */
    @GetMapping("/monthly")
    public List<User> getMonthlyRanking() {
        return rankingService.getMonthlyRanking();
    }
}