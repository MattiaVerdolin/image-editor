package ch.supsi.os.frontend.controller;
import javafx.stage.Stage;

public interface PreferencesEventHandler extends MenuBarEventHandler {
    void savePreferences(String selectedLanguage, Stage stage);
    void closePreferences(Stage stage);
    void preference();
}
