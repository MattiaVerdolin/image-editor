package ch.supsi.os.frontend.model;

import ch.supsi.backend.application.TranslationController;
import ch.supsi.os.frontend.view.*;

public class TranslationService {
    private static TranslationService myself;
    private final TranslationController translationController;

    public static TranslationService getInstance() {
        if (myself == null) {
            myself = new TranslationService();
        }

        return myself;
    }

    public TranslationService() {
        this.translationController = TranslationController.getInstance();
    }

    public void updateMenuBar(MenuBarView menuBarView) {

        menuBarView.setFileMenuText(translationController.translate("menu.file"));
        menuBarView.setPreferencesMenu(translationController.translate("menu.edit"));
        menuBarView.setAboutMenu(translationController.translate("menu.help"));
        menuBarView.setOpenMenuItemText(translationController.translate("menu.open"));
        menuBarView.setSaveMenuItemText(translationController.translate("menu.save"));
        menuBarView.setSaveAsMenuItemText(translationController.translate("menu.saveAs"));
        menuBarView.setQuitMenuItemText(translationController.translate("menu.quit"));
        menuBarView.setPreferencesMenuItemText(translationController.translate("menu.preferences"));
        menuBarView.setAboutMenuItemText(translationController.translate("menu.about"));
    }

    public void updatePipeLine(PipelineView pipelineView){
        pipelineView.setCancelButton(translationController.translate("pipeline.cancel"));
        pipelineView.setRunButton(translationController.translate("pipeline.run"));
    }

    public void updateAvTrans(AvTrasformationView avTrasformationView){
        avTrasformationView.setAvTransformationLabel(translationController.translate("av.trans"));
    }

    public void updateAbout(AboutView aboutView){
        aboutView.getStage().setTitle(translationController.translate("about.title"));
        aboutView.setName(translationController.translate("about.name"));
        aboutView.setBuildDate(translationController.translate("about.build"));
        aboutView.setDescription(translationController.translate("about.description"));
        aboutView.setVersion(translationController.translate("about.version"));
        aboutView.setClose(translationController.translate("button.close"));
        aboutView.setDevelopers(translationController.translate("about.developers"));
    }

    public void updatePreferences(PreferencesView preferencesView){
        preferencesView.getStage().setTitle(translationController.translate("preference.title"));
        preferencesView.setSaveButton(translationController.translate("button.save"));
        preferencesView.setCancelButton(translationController.translate("button.cancel"));
        preferencesView.setLanguage(translationController.translate("preference.language"));
    }

    public String translate(String string) {
        return translationController.translate(string);
    }

}
