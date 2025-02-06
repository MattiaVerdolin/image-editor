package ch.supsi.os.frontend.controller;

import javafx.stage.Stage;

public interface QuitEventHandler extends MenuBarEventHandler{
    void quit();
    void setStage(Stage stage);
}
