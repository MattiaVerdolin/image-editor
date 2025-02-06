package ch.supsi.backend.domain;

import java.util.ArrayList;
import java.util.List;

public class LogModel {
    private static LogModel myself;
    private final ArrayList<String> messagesList;
    private final List<LogObserver> observers = new ArrayList<>();

    public static LogModel getInstance() {
        if (myself == null) {
            myself = new LogModel();
        }
        return myself;
    }

    public LogModel() {
        messagesList = new ArrayList<>();
    }

    public void addMessage(String message) {
        messagesList.add(message);
        notifyObservers(message);
    }

    public ArrayList<String> getMessagesList() {
        return messagesList;
    }

    public void addObserver(LogObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(LogObserver observer) {
        observers.remove(observer);
    }

    private void notifyObservers(String message) {
        for (LogObserver observer : observers) {
            observer.onLogUpdate(message);
        }
    }

    //metodi per test
    public static void setInstance(LogModel mockInstance) {
        myself = mockInstance;
    }
}
