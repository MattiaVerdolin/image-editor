package ch.supsi.backend.domain;

import java.util.Map;

public interface PreferenceModelInterface {
    Object getPreference(String key);
    String getCurrentLanguage();
    Map<String, String> loadPreferences();
    void updatePreference(Map<String, String> preferencesMap);
}
