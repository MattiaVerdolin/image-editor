package ch.supsi.os.frontend.model;

import ch.supsi.backend.application.PreferencesController;

import java.util.HashMap;
import java.util.Map;

public class EditorAppModelF extends AbstractModel implements EditorAppModelInterface {
    private static EditorAppModelF myself;

    private PreferencesController preferencesController;

    public static EditorAppModelF getInstance() {
        if (myself == null) {
            myself = new EditorAppModelF();
        }
        return myself;
    }

    public void setPreferencesController(PreferencesController preferencesController) {
        this.preferencesController = preferencesController;
    }

    public EditorAppModelF() {
        preferencesController = PreferencesController.getInstance();
    }

    @Override
    public void savePreferences(String language) {
        Map<String, String> preferencesMap = new HashMap<>();

        switch (language.toLowerCase()) {
            case "english":
                preferencesMap.put("language", "en-US");
                break;
            case "italiano":
                preferencesMap.put("language", "it-IT");
                break;
            default:
                return;
        }

        preferencesController.savePreferences(preferencesMap);
    }
}
