package ch.supsi.backend.dataAccess;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

import static java.util.ResourceBundle.Control.FORMAT_DEFAULT;

public class TranslationDataAccess implements TranslationDataAccessInterface{
    private static TranslationDataAccess myself;

    private static final String translationsResourceBundlePath = "labels";
    private static final String supportedLanguagesPath = "/supported-languages.properties";

    private TranslationDataAccess() {}

    public static TranslationDataAccess getInstance() {
        if (myself == null) {
            myself = new TranslationDataAccess();
        }
        return myself;
    }

    private Properties loadSupportedLanguageTags() {
        Properties supportedLanguageTags = new Properties();
        try {
            InputStream supportedLanguageTagsStream = getClass().getResourceAsStream(supportedLanguagesPath);
            if (supportedLanguageTagsStream != null) {
                supportedLanguageTags.load(supportedLanguageTagsStream);
            }
        } catch (IOException ignored) {
            // Handle the exception as needed
        }
        return supportedLanguageTags;
    }

    @Override
    public List<String> getSupportedLanguageTags() {
        Properties props = loadSupportedLanguageTags();
        return new ArrayList<>(props.stringPropertyNames());
    }

    @Override
    public ResourceBundle getTranslations(Locale locale) {
        ResourceBundle bundle;
        try {
            bundle = ResourceBundle.getBundle(
                    translationsResourceBundlePath,
                    locale,
                    ResourceBundle.Control.getNoFallbackControl(FORMAT_DEFAULT)
            );
        } catch (MissingResourceException mrex) {
            // Handle the exception as needed
            bundle = ResourceBundle.getBundle(
                    translationsResourceBundlePath,
                    Locale.forLanguageTag("en-US"),
                    ResourceBundle.Control.getNoFallbackControl(FORMAT_DEFAULT)
            );
        }

        return bundle;
    }
}
