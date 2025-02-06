package ch.supsi.os.frontend.view;

import ch.supsi.backend.domain.LogObserver;
import ch.supsi.os.frontend.model.AbstractModel;
import ch.supsi.os.frontend.model.LogModelF;
import ch.supsi.os.frontend.model.LogModelInterface;
import ch.supsi.os.frontend.util.EmptySelectionModel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class LogView implements UncontrolledFxView, LogObserver {

    @FXML
    ListView<String> log_listView;
    @FXML
    private VBox vbox;

    LogModelInterface logModel;
    private static LogView myself;

    public static LogView getInstance() {
        if (myself == null) {
            myself = new LogView();

            try {
                URL fxmlUrl = LogView.class.getResource("/fxml/Log.fxml");
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
    public void initialize(AbstractModel model) {
        log_listView.setSelectionModel(new EmptySelectionModel<>());
        logModel = (LogModelF) model;
    }

    @Override
    public Node getNode() {
        return this.vbox;
    }

    @Override
    public void update() {
        ArrayList<String> messages = logModel.getMessagesList();
        // Cancella gli elementi esistenti e aggiorna con i nuovi in ordine inverso
        log_listView.getItems().clear();
        for (int i = messages.size() - 1; i >= 0; i--) {
            log_listView.getItems().add(messages.get(i));
        }
    }


    @Override
    public void onLogUpdate(String newLogMessage) {
        update();
    }
}
