package ch.supsi.os.frontend.view;

import ch.supsi.os.frontend.controller.*;
import ch.supsi.os.frontend.model.AbstractModel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Modality;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MenuBarView implements ControlledFxView{

    @FXML
    private MenuBar menuBar;

    @FXML
    private Menu fileMenu;

    @FXML
    private Menu preferencesMenu;

    @FXML
    private Menu aboutMenu;

    @FXML
    private MenuItem preferencesMenuItem; // Corretto da Menu a MenuItem
    @FXML
    private MenuItem aboutMenuItem; // Corretto da Menu a MenuItem

    @FXML
    private MenuItem openMenuItem;
    @FXML
    private MenuItem saveMenuItem;
    @FXML
    private MenuItem saveAsMenuItem;
    @FXML
    private MenuItem quitMenuItem;


    private PersistenceEventHandler persistenceEventHandler;
    private OpenEventHandler openEventHandler;
    private QuitEventHandler quitEventHandler;
    private PreferencesEventHandler preferencesEventHandler;
    private AboutEventHandler aboutEventHandler;


    private static MenuBarView myself;

    public static MenuBarView getInstance() {
        if (myself == null) {
            myself = new MenuBarView();

            try {
                URL fxmlUrl = PipelineView.class.getResource("/fxml/MenuBar.fxml");
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
    public Node getNode() {
        return this.menuBar;
    }

    @Override
    public void initialize(AbstractModel model, EventHandler... eventHandler) {
        this.createBehaviour();
        this.persistenceEventHandler = (PersistenceEventHandler) eventHandler[0];
        this.openEventHandler = (OpenEventHandler) eventHandler[1];
        this.quitEventHandler = (QuitEventHandler) eventHandler[2];
        this.preferencesEventHandler = (PreferencesEventHandler) eventHandler[3];
        this.aboutEventHandler = (AboutEventHandler) eventHandler[4];

        this.saveMenuItem.setDisable(true);
        this.saveAsMenuItem.setDisable(true);
    }

    private void createBehaviour() {
        this.saveMenuItem.setOnAction(event -> this.persistenceEventHandler.save());
        this.saveAsMenuItem.setOnAction(event -> this.persistenceEventHandler.saveAs());
        this.openMenuItem.setOnAction(event -> this.openEventHandler.open());
        this.quitMenuItem.setOnAction(event -> this.quitEventHandler.quit());
        this.preferencesMenuItem.setOnAction(event -> this.preferencesEventHandler.preference());
        this.aboutMenuItem.setOnAction(event -> this.aboutEventHandler.about());
    }

    public void enableSaveButtons(boolean enable) {
        saveMenuItem.setDisable(enable);
        saveAsMenuItem.setDisable(enable);
    }

    public void setFileMenuText(String text) {
        fileMenu.setText(text);
    }

    public void setPreferencesMenu(String text) {
        preferencesMenu.setText(text);
    }

    public void setAboutMenu(String text) {
        aboutMenu.setText(text);
    }

    public void setEditMenuText(String text) {
        preferencesMenuItem.setText(text);
    }

   public void setHelpMenuText(String text) {
       aboutMenuItem.setText(text);
    }


    public void setOpenMenuItemText(String text) {
        openMenuItem.setText(text);
    }

    public void setSaveMenuItemText(String text) {
        saveMenuItem.setText(text);
    }

    public void setSaveAsMenuItemText(String text) {
        saveAsMenuItem.setText(text);
    }

    public void setQuitMenuItemText(String text) {
        quitMenuItem.setText(text);
    }

    public void setPreferencesMenuItemText(String text) {
        preferencesMenuItem.setText(text);
    }

    public void setAboutMenuItemText(String text) {
        aboutMenuItem.setText(text);
    }

    @Override
    public void update() {
        // get your data from the model, if needed
        // then update this view here
        System.out.println(this.getClass().getSimpleName() + " updated..." + System.currentTimeMillis());
    }
}
