package ch.supsi.os.frontend.controller;

import ch.supsi.os.frontend.model.ImageModelF;
import ch.supsi.os.frontend.model.ImageModelFrontInterface;
import ch.supsi.os.frontend.model.TranslationService;
import ch.supsi.os.frontend.util.ButtonsMediator;
import ch.supsi.os.frontend.view.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class OpenControllerF implements OpenEventHandler{

    static OpenControllerF myself;
    //MODEL
    private final ImageModelFrontInterface imageModelFront;

    //VIEW
    private final UncontrolledFxView imageView;
    private final ControlledFxView menuBarView;

    //SERVICE
    private final TranslationService translationService;
    private final LogControllerF logControllerF;

    //MEDIATOR
    private final ButtonsMediator buttonsMediator;

    public OpenControllerF() {
        this.imageModelFront = ImageModelF.getInstance();
        this.imageView = ImageViewerView.getInstance();
        this.menuBarView = MenuBarView.getInstance();
        this.logControllerF = LogControllerF.getInstance();
        this.translationService = TranslationService.getInstance();
        this.buttonsMediator = ButtonsMediator.getInstance();
    }

    public static OpenControllerF getInstance() {
        if (myself == null) {
            myself = new OpenControllerF();
        }
        return myself;
    }


    @Override
    public void open() {
        FileChooser fileChooser = new FileChooser();
        List<String> compatibilityExtensions = imageModelFront.getExtensions();

        List<String> formattedExtensions = new ArrayList<>();
        for (String ext : compatibilityExtensions) {
            formattedExtensions.add("*." + ext);  // Converte in "*.estensione"
        }

        // Imposta filtri opzionali per i file
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Immagini", formattedExtensions)
        );

        // Ottieni lo Stage dal MenuBarView
        Stage stage = (Stage) menuBarView.getNode().getScene().getWindow();

        // Mostra la finestra di dialogo per selezionare un file
        File selectedFile = fileChooser.showOpenDialog(stage);

        if (selectedFile != null) {

            String filePath = selectedFile.getAbsolutePath();
            byte[] fileContent;

            try {
                fileContent = Files.readAllBytes(selectedFile.toPath());
            } catch (IOException e) {
                return;
            }

            if(imageModelFront.ImageAbstraction(filePath)){
                try {
                    imageView.update();
                } catch (Exception e) {
                    System.out.println("immagine nulla");
                }
                logControllerF.addMessageToModel(translationService.translate("log.open") + filePath);

                //abilita i pulsanti al termine del caricamento dell'immagine
                buttonsMediator.enableButtons(false);
            } else {
                return;
            }

            if(fileContent.length == 0) {
                logControllerF.addMessageToModel(translationService.translate("log.emptyFile"));
            }

        } else {
            logControllerF.addMessageToModel(translationService.translate("log.fileNotSelected"));
        }
    }
}
