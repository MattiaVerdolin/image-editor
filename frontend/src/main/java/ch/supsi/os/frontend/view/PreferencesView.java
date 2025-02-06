package ch.supsi.os.frontend.view;

import ch.supsi.os.frontend.controller.EventHandler;
import ch.supsi.os.frontend.controller.PreferencesEventHandler;
import ch.supsi.os.frontend.model.AbstractModel;
import ch.supsi.os.frontend.model.EditorAppModelInterface;
import ch.supsi.os.frontend.util.StageManager;
import ch.supsi.os.frontend.util.StyleManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;

public class PreferencesView implements AdditionalStageInterface{
    @FXML
    private BorderPane preferencesPane;
    @FXML
    private RadioButton lightThemeRadio;
    @FXML
    private RadioButton darkThemeRadio;
    private ToggleGroup themeToggleGroup;
    @FXML
    private ComboBox<String> languageComboBox;
    @FXML
    private Button saveButton;
    @FXML
    private Button cancelButton;
    @FXML
    private Label languageText;

    private static Stage stage;

    private EditorAppModelInterface preferencesModel;
    private PreferencesEventHandler preferencesEventHandler;
    private static StyleManager styleManager;
    private static StageManager stageManager;

    private static PreferencesView myself;

    public static PreferencesView getInstance() {
        if (myself == null) {
            myself = new PreferencesView();

            try {
                URL fxmlUrl = PreferencesView.class.getResource("/fxml/Preferences.fxml");
                if (fxmlUrl != null) {
                    FXMLLoader fxmlLoader = new FXMLLoader(fxmlUrl);
                    fxmlLoader.setController(myself);
                    fxmlLoader.load();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        return myself;
    }

    private PreferencesView() {
        styleManager = StyleManager.getInstance();
        stageManager = StageManager.getInstance();
    }

    @Override
    public Stage getStage() {
        if(stage == null) {
            stage = new Stage();
            Scene preferencesScene = new Scene(preferencesPane);
            preferencesScene.getStylesheets().add(styleManager.getStyle());
            stage.setScene(preferencesScene);
            stage.getIcons().add(styleManager.getSettingsIcon());
            stage.resizableProperty().setValue(Boolean.FALSE);

            stageManager.registerStage(stage);
        }

        return stage;
    }

    @Override
    public void initialize(AbstractModel model, EventHandler... eventHandler) {
        preferencesEventHandler = (PreferencesEventHandler) eventHandler[0];
        preferencesModel = (EditorAppModelInterface) model;

        // Imposta il valore predefinito del ComboBox per la lingua
        languageComboBox.setValue("Italiano");

        this.createBehaviour();
        update();
    }

    private void createBehaviour() {
        // Gestione del pulsante "Chiudi"
        this.saveButton.setOnAction(event ->
                this.preferencesEventHandler.savePreferences(languageComboBox.getValue(), stage));

        this.cancelButton.setOnAction(event
                -> this.preferencesEventHandler.closePreferences(stage));
    }

    public void setSaveButton(String text) {
        saveButton.setText(text);
    }
    public void setCancelButton(String text) {
        cancelButton.setText(text);
    }

    public void setLanguage(String text) {
        languageText.setText(text);
    }



    @Override
    public void update() {
    }
}
