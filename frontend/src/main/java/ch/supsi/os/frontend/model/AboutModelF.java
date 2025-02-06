package ch.supsi.os.frontend.model;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class AboutModelF extends AbstractModel implements AboutModelInterface{
    private static AboutModelF myself;
    private final Properties properties;
    private final TranslationService translationService;


    private AboutModelF() {
        translationService = TranslationService.getInstance();
        properties = new Properties();
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("application.properties")) {
            if (input == null) {
                System.out.println("Sorry, unable to find application.properties");
                return;
            }
            properties.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static AboutModelF getInstance() {
        if (myself == null) {
            myself = new AboutModelF();
        }
        return myself;
    }

    @Override
    public String getApplicationName() {
        return properties.getProperty("application.name");
    }

    @Override
    public String getVersion() {
        return properties.getProperty("application.version");
    }

    @Override
    public String getBuildTimestamp() {
        return properties.getProperty("build.timestamp") + " (UTC)";
    }

    @Override
    public String getDescription() {
        return translationService.translate("application.description");
    }

    @Override
    public String getDevelopers() {
        return properties.getProperty("developers");
    }
}
