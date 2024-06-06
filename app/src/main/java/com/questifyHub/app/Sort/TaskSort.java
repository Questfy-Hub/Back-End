package com.questifyHub.app.Sort;

import com.questifyHub.app.Entities.Task;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TaskSort {
    public static List<Task> descendentTaskSort(List<Task> tasks) {
        if (tasks.size() <= 1) {
            return tasks;
        }
        Task pivot = tasks.get(tasks.size() / 2);
        List<Task> less = new ArrayList<>();
        List<Task> greater = new ArrayList<>();
        List<Task> equal = new ArrayList<>();

        for(Task task : tasks) {
            LocalDate date = task.getEndLineDate();
            LocalDate pivotDate = pivot.getEndLineDate();
            if (date.isAfter(pivotDate)) {
                greater.add(task);
            }else if (date.isBefore(pivotDate)) {
                less.add(task);
            }else{
                equal.add(task);
            }
        }
        List<Task> sorted = new ArrayList<>();
        sorted.addAll(descendentTaskSort(greater));
        sorted.addAll(equal);
        sorted.addAll(descendentTaskSort(less));
        return sorted;
    }

}
