package ru.netology.javacore;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Todos {
    protected final List<String> todos = new ArrayList<>();

    public void addTask(String task) {
        todos.add(task);
    }

    public void removeTask(String task) {
        todos.remove(task);
    }

    public String getAllTasks() {
        return todos.stream().sorted(Comparator.comparing(String::toString))
                .collect(Collectors.joining(" "));
    }

    public List<String> getTodos() {
        return todos;
    }
}
