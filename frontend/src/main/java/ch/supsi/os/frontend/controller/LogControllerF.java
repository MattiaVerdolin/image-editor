package ch.supsi.os.frontend.controller;

import ch.supsi.os.frontend.model.LogModelF;
import ch.supsi.os.frontend.model.LogModelInterface;
import ch.supsi.os.frontend.view.LogView;
import ch.supsi.os.frontend.view.UncontrolledFxView;

public class LogControllerF {
    private static LogControllerF myself;
    private final UncontrolledFxView logView;
    private final LogModelInterface logModel;

    public static LogControllerF getInstance() {
        if (myself == null) {
            myself = new LogControllerF();
        }
        return myself;
    }

    public LogControllerF() {
        logView = LogView.getInstance();
        logModel = LogModelF.getInstance();
    }

    public void addMessageToModel(String message){
        logModel.addMessage(message);
        logView.update();
    }
}

