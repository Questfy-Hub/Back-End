package com.questifyHub.app.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.questifyHub.app.Entities.User;
import com.questifyHub.app.Services.RankingService;

import java.util.List;

@RestController
@RequestMapping("/ranking")
public class RankingController {

    @Autowired
    private RankingService rankingService;

    @GetMapping("/monthly")
    public List<User> getMonthlyRanking() {
        return rankingService.getMonthlyRanking();
    }
}