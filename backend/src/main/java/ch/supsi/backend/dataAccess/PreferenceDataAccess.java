package ch.supsi.backend.dataAccess;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

public class PreferenceDataAccess implements PreferencesDataAccessInterface {

    private static final String defaultPreferencesPath = "/default-user-preferences.properties";
    private static final String userHomeDirectory = System.getProperty("user.home");
    private static final String preferencesDirectory = ".2DImageEditor";
    private static final String preferencesFile = "preferences.properties";
    private static PreferenceDataAccess dao;
    private static Properties userPreferences;

    // protected default constructor to avoid a new instance being requested from clients
    protected PreferenceDataAccess() {}

    // singleton instantiation of this data access object
    // guarantees only a single instance exists in the life of the application
    public static PreferenceDataAccess getInstance() {
        if (dao == null) {
            dao = new PreferenceDataAccess();
        }

        return dao;
    }

    private Path getUserPreferencesDirectoryPath() {
        return Path.of(userHomeDirectory, preferencesDirectory);
    }

    private boolean userPreferencesDirectoryExists() {
        return Files.exists(this.getUserPreferencesDirectoryPath());
    }

    private Path createUserPreferencesDirectory() {
        try {
            return Files.createDirectories(this.getUserPreferencesDirectoryPath());

        } catch (IOException ignoredForDemoPurposes) {
            ;
        }

        return null;
    }

    private Path getUserPreferencesFilePath() {
        return Path.of(userHomeDirectory, preferencesDirectory, preferencesFile);
    }

    private boolean userPreferencesFileExists() {
        return Files.exists(this.getUserPreferencesFilePath());
    }

    private boolean createUserPreferencesFile(Properties defaultPreferences) {
        if (defaultPreferences == null) {
            return false;
        }

        if (!userPreferencesDirectoryExists()) {
            this.createUserPreferencesDirectory();
        }

        if (!userPreferencesFileExists()) {
            try {
                // create user preferences file (with default preferences)
                FileOutputStream outputStream = new FileOutputStream(String.valueOf(this.getUserPreferencesFilePath()));
                defaultPreferences.store(outputStream, null);
                return true;

            } catch (IOException ignoredForDemoPurposes) {
                return false;
            }
        }

        return true;
    }

    private Properties loadDefaultPreferences() {
        Properties defaultPreferences = new Properties();
        try {
            InputStream defaultPreferencesStream = this.getClass().getResourceAsStream(defaultPreferencesPath);
            defaultPreferences.load(defaultPreferencesStream);

        } catch (IOException ignored) {
            ;
        }

        return defaultPreferences;
    }

    private Properties loadPreferencesFromUserDirectory(Path path) {
        Properties preferences = new Properties();
        try {
            preferences.load(new FileInputStream(String.valueOf(path)));

        } catch (IOException ignoredForDemoPurposes) {
            return null;
        }

        return preferences;
    }

    @Override
    public Properties getPreferences() {
        if (userPreferences != null) {
            return userPreferences;
        }

        if (userPreferencesFileExists()) {
            userPreferences = this.loadPreferencesFromUserDirectory(this.getUserPreferencesFilePath());
            return userPreferences;
        }

        userPreferences = this.loadDefaultPreferences();

        // Controllo aggiunto: verifica se la creazione del file è riuscita
        if (!this.createUserPreferencesFile(userPreferences)) {
            // Puoi lanciare un'eccezione, loggare un messaggio o gestire l'errore come preferisci
            throw new RuntimeException("Errore nella creazione del file delle preferenze utente.");
        }
        // return the properties object with the loaded preferences
        return userPreferences;
    }

    //metodi per salvare e aggiornare le preference nel file
    private void savePreferences(Properties preferences) {
        try (FileOutputStream outputStream = new FileOutputStream(String.valueOf(getUserPreferencesFilePath()))) {
            preferences.store(outputStream, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updatePreference(String key, String value) {
        Properties preferences = getPreferences();
        preferences.setProperty(key, value);
        savePreferences(preferences);
    }

}
