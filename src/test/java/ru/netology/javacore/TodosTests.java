package ru.netology.javacore;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class TodosTests {
    private List<String> expected;
    private Todos actual;
    String[] valuesForExpected = {"aaa", "bbb", "ccc", "ddd"};
    String[] valuesForActual = {"bbb", "aaa", "ddd", "ccc"};
    private String expectedString;

    @BeforeEach
    public void setUp() {
        expected = new ArrayList<>();
        actual = new Todos();
        Collections.addAll(expected, valuesForExpected);
        for (String value : valuesForActual) {
            actual.addTask(value);
        }
        expectedString = "aaa bbb ccc ddd";
    }

    @Test
    public void testAddTask() {
        List<String> result = actual.getTodos();
        result.sort(Comparator.comparing(String::toString));
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void testRemoveTask() {
        expected.remove("aaa");
        actual.removeTask("aaa");
        List<String> result = actual.getTodos();
        result.sort(Comparator.comparing(String::toString));
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void testGetAllTasks() {
        String actualString = actual.getAllTasks();
        Assertions.assertEquals(expectedString, actualString);
    }
}
