package ru.netology.javacore;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    protected final static String ADD = "ADD";
    protected final static String REMOVE = "REMOVE";

    private static void createRquestToServer(String action, String task) throws IOException {
        try (
                Socket socket = new Socket("localhost", 8989);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true)
        ) {
            out.println("{ \"type\": \"" + action + "\", \"task\": \"" + task + "\" }");
            System.out.println(in.readLine());
        }
    }

    public static void main(String[] args) throws IOException {
        createRquestToServer(ADD, "Подъем");
        createRquestToServer(ADD, "Зарядка");
        createRquestToServer(ADD, "Умывание");
        createRquestToServer(ADD, "Акробатика");
        createRquestToServer(REMOVE, "Зарядка");
        createRquestToServer("GET", "Акробатика");
    }
}
