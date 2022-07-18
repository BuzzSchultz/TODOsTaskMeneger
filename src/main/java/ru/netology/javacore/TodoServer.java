package ru.netology.javacore;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class TodoServer {
    protected final int port;
    protected final Todos todos;
    protected final String ADD = "ADD";
    protected final String REMOVE = "REMOVE";
    protected final String invalidRequest = "Параметры задачи введены некорректно!";

    public TodoServer(int port, Todos todos) {
        this.port = port;
        this.todos = todos;
    }

    public void start() throws IOException {
        System.out.println("Starting server at " + port + "....");
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            while (true) {
                try (
                        Socket socket = serverSocket.accept();
                        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                        PrintWriter out = new PrintWriter(socket.getOutputStream())
                ) {
                    GsonBuilder builder = new GsonBuilder();
                    Gson gson = builder.create();
                    JSONParser parser = new JSONParser();
                    try {
                        JSONObject task = (JSONObject) parser.parse(in.readLine());
                        IncomingTask incomingTask = gson.fromJson(task.toString(), IncomingTask.class);
                        String action = incomingTask.getType();
                        switch (action) {
                            case ADD: {
                                System.out.println("Получен запрос на добавление задачи: \"" + incomingTask.getTask() + "\"");
                                todos.addTask(incomingTask.getTask());
                                System.out.println("Задача: \"" + incomingTask.getTask() + "\" добавлена в срисок");
                                break;
                            }
                            case REMOVE: {
                                System.out.println("Получен запрос на удаление задачи: \"" + incomingTask.getTask() + "\"");
                                todos.removeTask(incomingTask.getTask());
                                System.out.println("Задача: \"" + incomingTask.getTask() + "\" удалена из списка");
                                break;
                            }
                            default: {
                                System.out.println(invalidRequest);
                                out.println(invalidRequest);
                                break;
                            }
                        }
                    } catch (ParseException ex) {
                        ex.printStackTrace();
                    }
                    out.println(todos.getAllTasks());
                }
            }
        }
    }
}
