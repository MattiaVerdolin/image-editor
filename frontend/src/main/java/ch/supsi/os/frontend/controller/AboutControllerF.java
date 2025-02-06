package ch.supsi.os.frontend.controller;

import ch.supsi.os.frontend.view.AboutView;
import ch.supsi.os.frontend.view.AdditionalStageInterface;
import javafx.stage.Stage;

public class AboutControllerF implements AboutEventHandler {

    private static AboutControllerF myself;
    private AdditionalStageInterface aboutView;

    public static AboutControllerF getInstance() {
        if (myself == null) {
            myself = new AboutControllerF();
        }
        return myself;
    }

    public AboutControllerF() {
        aboutView = AboutView.getInstance();
    }

    @Override
    public void closeStage(Stage stage) {
        stage.close();
    }

    @Override
    public void about() {
        aboutView.getStage().show();
    }

    public AdditionalStageInterface getAboutView() {
        return aboutView;
    }
}
