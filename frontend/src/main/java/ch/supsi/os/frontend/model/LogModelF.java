package ch.supsi.os.frontend.model;

import ch.supsi.backend.application.LogController;

import java.util.ArrayList;

public class LogModelF extends AbstractModel implements LogModelInterface{
    private static LogModelF myself;
    private LogController logController;

    public static LogModelF getInstance() {
        if (myself == null) {
            myself = new LogModelF();
        }
        return myself;
    }

    public LogModelF() {
        logController = LogController.getInstance();
    }

    @Override
    public void addMessage(String message) {
        logController.addMessage(message);
    }

    @Override
    public ArrayList<String> getMessagesList() {
        return logController.getMessagesList();
    }
}
