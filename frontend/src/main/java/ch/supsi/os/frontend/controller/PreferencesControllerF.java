package ch.supsi.os.frontend.controller;

import ch.supsi.os.frontend.model.EditorAppModelF;
import ch.supsi.os.frontend.model.EditorAppModelInterface;
import ch.supsi.os.frontend.model.TranslationService;
import ch.supsi.os.frontend.util.AlertManager;
import ch.supsi.os.frontend.view.AdditionalStageInterface;
import ch.supsi.os.frontend.view.PreferencesView;
import javafx.stage.Stage;

public class PreferencesControllerF implements PreferencesEventHandler{
    private static PreferencesControllerF myself;
    private final EditorAppModelInterface preferencesModel;
    private final AdditionalStageInterface preferencesView;
    private final TranslationService translationService;
    private final LogControllerF logController;
    private final AlertManager alertManager;

    public static PreferencesControllerF getInstance() {
        if (myself == null) {
            myself = new PreferencesControllerF();
        }
        return myself;
    }

    public PreferencesControllerF() {
        this.preferencesModel = EditorAppModelF.getInstance();
        this.preferencesView = PreferencesView.getInstance();
        this.translationService = TranslationService.getInstance();
        this.logController = LogControllerF.getInstance();
        this.alertManager = AlertManager.getInstance();
    }

    @Override
    public void savePreferences(String selectedLanguage, Stage stage) {

        if(alertManager.alertStage(translationService.translate("preferences.title"),
                translationService.translate("preferences.header"),
                translationService.translate("preferences.content"))) {

            // Salva le preferenze nel modello
            preferencesModel.savePreferences(selectedLanguage);
            // avvisa sul log
            logController.addMessageToModel(translationService.translate("log.preferencesSaved"));
            //chiudi stage
            stage.close();
        } else {
            logController.addMessageToModel(translationService.translate("log.preferencesNotSaved"));
        }

    }

    @Override
    public void closePreferences(Stage stage) {
        stage.close();
        logController.addMessageToModel(translationService.translate("log.preferences.close"));
    }


    @Override
    public void preference() {
        preferencesView.getStage().show();
    }
}
