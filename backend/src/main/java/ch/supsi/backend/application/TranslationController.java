package ch.supsi.backend.application;

import ch.supsi.backend.domain.PreferenceModel;
import ch.supsi.backend.domain.PreferenceModelInterface;
import ch.supsi.backend.domain.TranslationModel;
import ch.supsi.backend.domain.TranslationModelInterface;

import java.util.ResourceBundle;

public class TranslationController {
    private static TranslationController myself;

    private final TranslationModelInterface translationsModel = TranslationModel.getInstance();
    private final PreferenceModelInterface preferencesModel = PreferenceModel.getInstance();

    protected TranslationController() {
        String currentLanguage = preferencesModel.getCurrentLanguage();
        translationsModel.changeLanguage(currentLanguage);
    }

    public static TranslationController getInstance() {
        if (myself == null) {
            myself = new TranslationController();
        }

        return myself;
    }

    /**
     * Translate the given key
     *
     * @param key
     *
     * @return String
     */
    public String translate(String key) {
        return translationsModel.translate(key);
    }

    public ResourceBundle getTranslationBundle() {
        return translationsModel.getTranslationBundle();
    }


}

