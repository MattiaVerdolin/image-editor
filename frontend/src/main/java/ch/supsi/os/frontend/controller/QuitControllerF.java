package ch.supsi.os.frontend.controller;


import ch.supsi.os.frontend.model.TranslationService;
import ch.supsi.os.frontend.util.AlertManager;
import ch.supsi.os.frontend.util.StageManager;
import javafx.stage.Stage;

public class QuitControllerF implements QuitEventHandler {

    static QuitControllerF myself;
    private final TranslationService translationService;
    private final AlertManager alert;
    private final StageManager stageManager;

    public static QuitControllerF getInstance() {
        if (myself == null) {
            myself = new QuitControllerF();
        }
        return myself;
    }

    public QuitControllerF() {
        this.translationService = TranslationService.getInstance();
        this.alert = AlertManager.getInstance();
        this.stageManager = StageManager.getInstance();
    }

    @Override
    public void quit() {
        if(alert.alertStage(translationService.translate("quit.confirm"),
                translationService.translate("quit.header"),
                translationService.translate("quit.content"))) {

            stageManager.closeAllStages();
        }
    }

    @Override
    public void setStage(Stage stage) {
    }
}
