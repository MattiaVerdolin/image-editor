package ch.supsi.os.frontend.view;

import ch.supsi.os.frontend.controller.EventHandler;
import ch.supsi.os.frontend.model.AbstractModel;
import ch.supsi.os.frontend.controller.PipelineEventHandler;
import ch.supsi.os.frontend.model.PipelineModelFrontInterface;
import ch.supsi.os.frontend.util.EmptySelectionModel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.net.URL;

public class PipelineView implements ControlledFxView {
    PipelineEventHandler eventHandler;
    @FXML
    private ListView<HBox> pipeline_ListView;
    @FXML
    private Button cancelButton;
    @FXML
    private Button runButton;
    @FXML
    private AnchorPane anchorPane;

    private PipelineEventHandler pipelineEventHandler;
    private PipelineModelFrontInterface pipelineModelFront;
    private static PipelineView myself;

    public static PipelineView getInstance() {
        if (myself == null) {
            myself = new PipelineView();

            try {
                URL fxmlUrl = PipelineView.class.getResource("/fxml/Pipeline.fxml");
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

    public PipelineView() {
    }

    @Override
    public void initialize(AbstractModel model, EventHandler... eventHandler) {
        createBehaviour();
        pipeline_ListView.setSelectionModel(new EmptySelectionModel<>());
        this.pipelineEventHandler = (PipelineEventHandler) eventHandler[0];
        this.pipelineModelFront = (PipelineModelFrontInterface) model;

        //disabilita pulsante cancella e run all'avvio
        this.cancelButton.setDisable(true);
        this.runButton.setDisable(true);
        this.pipeline_ListView.setDisable(true);
    }

    private void createBehaviour() {
        this.cancelButton.setOnAction(event -> this.pipelineEventHandler.cancelPipeline());
        this.runButton.setOnAction(event ->  this.pipelineEventHandler.run());
    }

    @Override
    public Node getNode() {
        return this.anchorPane;
    }

    public void setCancelButton(String text) {
        this.cancelButton.setText(text);
    }

    public void setRunButton(String text) {
        this.runButton.setText(text);
    }

    public void enableButtons(boolean enable) {
        cancelButton.setDisable(enable);
        runButton.setDisable(enable);
        pipeline_ListView.setDisable(enable);
    }

    @Override
    public void update() {
        pipeline_ListView.getItems().clear();

        for (String transformation : pipelineModelFront.transformationsInPipeline()) {
            HBox hbox = new HBox(new Label(transformation)); // Usa getName per visualizzare il nome
            hbox.getStyleClass().add("with-divider");
            pipeline_ListView.getItems().add(hbox);
        }


    }
}
