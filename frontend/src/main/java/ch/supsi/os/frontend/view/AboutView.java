package ch.supsi.os.frontend.view;

import ch.supsi.os.frontend.controller.AboutEventHandler;
import ch.supsi.os.frontend.controller.EventHandler;
import ch.supsi.os.frontend.model.AboutModelInterface;
import ch.supsi.os.frontend.model.AbstractModel;
import ch.supsi.os.frontend.util.StageManager;
import ch.supsi.os.frontend.util.StyleManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;

public class AboutView implements AdditionalStageInterface {
    @FXML
    private AnchorPane aboutPane;
    @FXML
    private Label version;
    @FXML
    private Label buildDate;
    @FXML
    private Label developers;
    @FXML
    private Label description;
    @FXML
    private Button close;
    @FXML
    private Label versionText;
    @FXML
    private Label buildText;
    @FXML
    private Label developersText;
    @FXML
    private Label descriptionText;
    @FXML
    private Label nameText;
    @FXML
    private Label name;

    private static Stage stage;

    private AboutModelInterface aboutModel;
    private AboutEventHandler aboutEventHandler;
    private static StyleManager styleManager;
    private static StageManager stageManager;

    private static AboutView myself;

    public static AboutView getInstance() {
        if (myself == null) {
            myself = new AboutView();

            try {
                URL fxmlUrl = AboutView.class.getResource("/fxml/About.fxml");
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

    private AboutView() {
        styleManager = StyleManager.getInstance();
        stageManager = StageManager.getInstance();
    }

    @Override
    public Stage getStage() {
        if(stage == null) {
            stage = new Stage();
            Scene aboutScene = new Scene(aboutPane);
            aboutScene.getStylesheets().add(styleManager.getStyle());
            stage.setScene(aboutScene);
            stage.getIcons().add(styleManager.getAboutIcon());
            stage.resizableProperty().setValue(Boolean.FALSE);

            stageManager.registerStage(stage);
        }

        return stage;
    }

    @Override
    public void initialize(AbstractModel model, EventHandler... eventHandler) {
        aboutEventHandler = (AboutEventHandler) eventHandler[0];
        aboutModel = (AboutModelInterface) model;
        this.createBehaviour();
        update();
    }

    private void createBehaviour() {
        // Gestione del pulsante "Chiudi"
        this.close.setOnAction(event -> this.aboutEventHandler.closeStage(stage));
    }

    public void setName(String text) {
        nameText.setText(text);
    }

    public void setVersion(String text) {
        versionText.setText(text);
    }

    public void setBuildDate(String text) {
        buildText.setText(text);
    }

    public void setDevelopers(String text) {
        developersText.setText(text);
    }

    public void setDescription(String text) {
        descriptionText.setText(text);
    }

    public void setClose(String text) {
        close.setText(text);
    }

    public Label getVersion() {
        return version;
    }

    public Label getBuildDate() {
        return buildDate;
    }

    public Label getDevelopers() {
        return developers;
    }

    public Label getDescription() {
        return description;
    }

    public Label getVersionText() {
        return versionText;
    }

    public Label getBuildText() {
        return buildText;
    }

    public Label getDevelopersText() {
        return developersText;
    }

    public Label getDescriptionText() {
        return descriptionText;
    }

    public Button getClose() {
        return close;
    }

    @Override
    public void update() {
        this.name.setText(this.aboutModel.getApplicationName());
        this.version.setText(this.aboutModel.getVersion());
        this.buildDate.setText(this.aboutModel.getBuildTimestamp());
        this.developers.setText(this.aboutModel.getDevelopers());
        this.description.setText(this.aboutModel.getDescription());
    }
}
