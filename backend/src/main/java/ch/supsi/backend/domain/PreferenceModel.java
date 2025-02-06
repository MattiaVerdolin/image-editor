package ch.supsi.backend.domain;


import ch.supsi.backend.dataAccess.PreferenceDataAccess;
import ch.supsi.backend.dataAccess.PreferencesDataAccessInterface;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class PreferenceModel implements PreferenceModelInterface {
    private static PreferenceModel myself;

    private final Properties userPreferences;
    private final PreferencesDataAccessInterface preferencesDao;

    public PreferenceModel() {
        preferencesDao = PreferenceDataAccess.getInstance();
        userPreferences = preferencesDao.getPreferences();
    }

    public static PreferenceModel getInstance() {
        if (myself == null) {
            myself = new PreferenceModel();
        }

        return myself;
    }

    @Override
    public String getCurrentLanguage() {
        return userPreferences.getProperty("language");
    }

    @Override
    public Object getPreference(String key) {
        if (key == null || key.isEmpty()) {
            return null;
        }

        if (userPreferences == null) {
            return null;
        }

        return userPreferences.getOrDefault(key, null);
    }

    @Override
    //carica le preferenze presenti nel file preferences nelle istanze di giocatori
    public Map<String, String> loadPreferences() {
        Map<String, String> preferencesMap = new HashMap<>();

        // Ottiene tutte le chiavi dalle preferenze
        Set<String> keys = userPreferences.stringPropertyNames();

        // Cicla su tutte le chiavi e le aggiunge alla mappa con il rispettivo valore
        for (String key : keys) {
            preferencesMap.put(key, userPreferences.getProperty(key));
        }

        return preferencesMap;
    }

    @Override
    public void updatePreference(Map<String, String> preferencesMap) {
        for (Map.Entry<String, String> entry : preferencesMap.entrySet()) {
            preferencesDao.updatePreference(entry.getKey(), entry.getValue());
        }
    }
}
