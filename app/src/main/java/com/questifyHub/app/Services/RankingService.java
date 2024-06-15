package com.questifyHub.app.Services;

import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.questifyHub.app.Entities.Task;
import com.questifyHub.app.Entities.User;

/**
 * Classe RankingService que possui a logica das funcionalidades do objeto ranking
 */
@Service
public class RankingService {

    @Autowired
    private UserService userService;
    @Autowired
    private TaskService taskService;

    /**
     * Método para requisitar uma lista de todas os usuários e ordená-los de forma decrescente de acordo com a quantidade de pontos
     * 
     * @return Lista de usuários ordenada forma decrescente de acordo com a
     *         quantidade de pontos
     */
    public List<User> getMonthlyRanking() {
        List<User> users = userService.getAllUser();
        List<User> usersWithPoints = new ArrayList<>();

        for (User user : users) {
            int monthlyPoints = calculateMonthlyPoints(user);
            user.setPoints(monthlyPoints);
            usersWithPoints.add(user);
        }

        quickSort(usersWithPoints, 0, usersWithPoints.size() - 1);
        return usersWithPoints;
    }

    /**
     * Método para calcular a pontuação dos usuários no mês vigente
     * 
     * @param user
     * @return Total de pontos de cada susário no mês
     */
    private int calculateMonthlyPoints(User user) {
        YearMonth currentMonth = YearMonth.now();
        int totalPoints = 0;

        for (Task task : user.getTaskUser()) {
            if (task.getConclusionDate() != null && YearMonth.from(task.getConclusionDate()).equals(currentMonth)) {
                totalPoints += taskService.calculatePoints(task);
            }
        }

        return totalPoints;
    }

    /**
     * Quicksort para realizar as ordenações
     * 
     * @param users
     * @param low
     * @param high
     */
    private void quickSort(List<User> users, int low, int high) {
        if (low < high) {
            int pi = partition(users, low, high);
            quickSort(users, low, pi - 1);
            quickSort(users, pi + 1, high);
        }
    }

    private int partition(List<User> users, int low, int high) {
        int pivot = users.get(high).getPoints();
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            if (users.get(j).getPoints() > pivot) {
                i++;
                User temp = users.get(i);
                users.set(i, users.get(j));
                users.set(j, temp);
            }
        }

        User temp = users.get(i + 1);
        users.set(i + 1, users.get(high));
        users.set(high, temp);

        return i + 1;
    }
}
