package ch.supsi.backend.domain;

import ch.supsi.backend.dataAccess.TranslationDataAccess;
import ch.supsi.backend.dataAccess.TranslationDataAccessInterface;

import java.util.List;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

public class TranslationModel implements TranslationModelInterface {
    private static TranslationModel myself;

    private final TranslationDataAccessInterface dataAccess = TranslationDataAccess.getInstance();
    private final List<String> supportedLanguageTags = dataAccess.getSupportedLanguageTags();
    private Properties translations;
    private ResourceBundle translationBundle;

    TranslationModel() {
    }

    public boolean isSupportedLanguageTag(String languageTag) {
        if (!supportedLanguageTags.contains(languageTag)) {
            return false;
        }

        return true;
    }

    public static TranslationModel getInstance() {
        if (myself == null) {
            myself = new TranslationModel();
        }

        return myself;
    }

    @Override
    public void changeLanguage(String languageTag) {

        translations = new Properties();
        translationBundle = dataAccess.getTranslations(Locale.forLanguageTag(languageTag));

        if (translationBundle == null) {
            throw new RuntimeException("Translation bundle not found for language tag: " + languageTag);
        }

        for (String key : translationBundle.keySet()) {
            translations.put(key, translationBundle.getString(key));
        }
    }

    @Override
    public String translate(String key) {
        return translations.getProperty(key);
    }

    public ResourceBundle getTranslationBundle() {
        return translationBundle;
    }
}
