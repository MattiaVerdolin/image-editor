package ch.supsi.os.frontend.view;

import ch.supsi.os.frontend.controller.EventHandler;
import ch.supsi.os.frontend.model.AbstractModel;
import ch.supsi.os.frontend.model.TransformationsModelF;
import ch.supsi.os.frontend.controller.AvTrasformationEventHandler;
import ch.supsi.os.frontend.util.EmptySelectionModel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.*;

import java.io.IOException;
import java.net.URL;
import java.util.Map;

public class AvTrasformationView implements ControlledFxView {

    @FXML
    private ListView<HBox> transformations_ListView;
    @FXML
    private VBox vbox;
    @FXML
    Label avTransformationLabel;

    private TransformationsModelF avTrasformationModel;
    private AvTrasformationEventHandler avTrasformationEventHandler;

    private static AvTrasformationView myself;

    public static AvTrasformationView getInstance() {
        if (myself == null) {
            myself = new AvTrasformationView();

            try {
                URL fxmlUrl = AvTrasformationView.class.getResource("/fxml/AvTransformation.fxml");
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

    @Override
    public void initialize(AbstractModel model, EventHandler... eventHandler) {
        transformations_ListView.setSelectionModel(new EmptySelectionModel<>());
        avTrasformationEventHandler = (AvTrasformationEventHandler) eventHandler[0];
        avTrasformationModel = (TransformationsModelF) model;
        update();

        //disabilita la list view all'avvio (impossibilità di aggiungere trasformazioni alla pipeline)
        this.transformations_ListView.setDisable(true);
    }

    private HBox createTransformationHBox(String id, String transformation) {
        HBox hbox = new HBox();
        Label label = new Label(transformation);
        Button addButton = new Button("+");
        addButton.getStyleClass().add("plus-button");

        addButton.setOnAction(event -> this.avTrasformationEventHandler.addTrasformation(id, transformation));

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        hbox.getChildren().addAll(label, spacer, addButton);

        hbox.setStyle("-fx-alignment: CENTER_LEFT;"); // Allinea la label a sinistra
        return hbox;
    }

    public void setAvTransformationLabel(String text) {
        avTransformationLabel.setText(text);
    }

    public void enableListView(boolean enable) {
        transformations_ListView.setDisable(enable);
    }

    @Override
    public Node getNode() {
        return this.vbox;
    }

    @Override
    public void update() {
        transformations_ListView.getItems().clear();
        Map<String,String> transformations = avTrasformationModel.getNamesId(); //recupero una mappa con id e nome delle trasformazioni

        for (Map.Entry<String, String> transformation : transformations.entrySet()) {
            String id = transformation.getKey();
            String name = transformation.getValue();
            HBox hbox = createTransformationHBox(id, name);
            hbox.getStyleClass().add("with-divider"); // Visualizza il divisore
            transformations_ListView.getItems().add(hbox);
        }
    }
}
