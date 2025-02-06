package ch.supsi.os.frontend.util;

import ch.supsi.os.frontend.model.TranslationService;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;

import java.util.Optional;

public class AlertManager {
    static AlertManager myself;
    private final TranslationService translationService;

    public static AlertManager getInstance() {
        if (myself == null) {
            myself = new AlertManager();
        }
        return myself;
    }

    public AlertManager() {
        translationService = TranslationService.getInstance();
    }

    public boolean alertStage(String title, String header, String content) {
        // Crea una finestra di dialogo di conferma
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);

        // Aggiungi pulsanti OK e Cancel
        ButtonType okButton = new ButtonType("Ok", ButtonBar.ButtonData.YES);
        ButtonType cancelButton = new ButtonType(translationService.translate("button.close"), ButtonBar.ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(okButton, cancelButton);

        // Mostra la finestra e attendi l'input dell'utente
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == okButton) {
            // Se l'utente ha scelto "Ok", ritorna true (delego la logica al chiamante)
            return true;
        } else {
            // Se l'utente ha scelto "Cancel" o chiude il dialogo, non fare nulla
            alert.close();
        }

        return false;
    }


}
