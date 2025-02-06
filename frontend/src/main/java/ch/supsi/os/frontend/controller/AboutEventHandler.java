package ch.supsi.os.frontend.controller;

import javafx.stage.Stage;

public interface AboutEventHandler extends MenuBarEventHandler {
    void closeStage(Stage stage);
    void about();
}
