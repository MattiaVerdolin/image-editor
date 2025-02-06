package ch.supsi.os.frontend.controller;

import ch.supsi.os.frontend.model.ImageModelF;
import ch.supsi.os.frontend.model.ImageModelFrontInterface;
import ch.supsi.os.frontend.model.TranslationService;
import ch.supsi.os.frontend.view.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class PersistenceControllerF implements PersistenceEventHandler{
    private static PersistenceControllerF myself;

    //MODEL
    private final ImageModelFrontInterface imageModelFront;

    //VIEW
    private final ControlledFxView menuBarView;
    private final LogControllerF logControllerF;

    //SERVICE
    private final TranslationService translationService;

    public static PersistenceControllerF getInstance() {
        if (myself == null) {
            myself = new PersistenceControllerF();
        }
        return myself;
    }

    public PersistenceControllerF() {
        this.imageModelFront = ImageModelF.getInstance();

        this.menuBarView = MenuBarView.getInstance();
        this.logControllerF = LogControllerF.getInstance();
        this.translationService = TranslationService.getInstance();
    }
    @Override
    public void save() {
        imageModelFront.saveImage(imageModelFront.getImagePath());
        logControllerF.addMessageToModel(translationService.translate("log.save"));
    }

    @Override
    public void saveAs() {
        List<String> compatibleExtensions = imageModelFront.getExtensions();

        // Crea il file chooser
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Salva come");

        // Aggiungi i formati compatibili come tipi di file
        if (!compatibleExtensions.isEmpty()) {
            // Utilizza un Set per evitare duplicati se le estensioni compatibili sono uguali
            Set<String> uniqueExtensions = new LinkedHashSet<>(compatibleExtensions);

            for (String ext : uniqueExtensions) {
                fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter(ext.toUpperCase() + " files", "*." + ext));
            }
        }

        // Imposta il tipo di file di default
        if (!fileChooser.getExtensionFilters().isEmpty()) {
            fileChooser.setSelectedExtensionFilter(fileChooser.getExtensionFilters().get(0));
        }

        // Ottieni lo Stage dal MenuBarView
        Stage stage = (Stage) menuBarView.getNode().getScene().getWindow();

        // Mostra il file chooser per il salvataggio
        File fileToSave = fileChooser.showSaveDialog(stage);

        if (fileToSave != null) {
            // Costruisci la path completa con l'estensione selezionata
            String filePath = fileToSave.getAbsolutePath();
            String selectedExtension = fileChooser.getSelectedExtensionFilter().getExtensions().get(0).replace("*.", "");

            // Assicurati che la path finale contenga l'estensione corretta
            if (!filePath.endsWith("." + selectedExtension)) {
                filePath += "." + selectedExtension;
            }

            // Salva l'immagine
            try {
                imageModelFront.saveImage(filePath);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Salvataggio annullato.");
        }
    }

}
