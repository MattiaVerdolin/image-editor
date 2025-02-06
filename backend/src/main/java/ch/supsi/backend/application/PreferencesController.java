package ch.supsi.backend.application;

import ch.supsi.backend.domain.PreferenceModel;
import ch.supsi.backend.domain.PreferenceModelInterface;

import java.util.Map;

public class PreferencesController {
    private static PreferencesController myself;
    private final PreferenceModelInterface preferenceModel;

    public static PreferencesController getInstance() {
        if (myself == null) {
            myself = new PreferencesController();
        }
        return myself;
    }

    public PreferencesController() {
        preferenceModel = PreferenceModel.getInstance();
    }

    public void savePreferences(Map<String, String> preferences) {
        preferenceModel.updatePreference(preferences);
    }
}
