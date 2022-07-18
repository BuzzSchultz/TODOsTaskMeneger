package ru.netology.javacore;

public class IncomingTask {
    protected final String type;
    protected final String task;
    public IncomingTask(String type, String task) {
        this.type = type;
        this.task = task;
    }

    @Override
    public String toString() {
        return "IncomingTask{" +
                "type='" + type + '\'' +
                ", task='" + task + '\'' +
                '}';
    }

    public String getType() {
        return type;
    }

    public String getTask() {
        return task;
    }
}
