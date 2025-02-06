package ch.supsi.os.frontend.util;

import javafx.scene.image.Image;

import java.util.Objects;

public class StyleManager {
    private static final String STYLE_PATH = "/style/moonlight.css";
    private static final String EDITOR_ICON_PATH = "/icons/editorIcon.png";
    private static final String ABOUT_ICON_PATH = "/icons/aboutIcon.png";
    private static final String SETTINGS_ICON_PATH = "/icons/settingsIcon.png";
    private static StyleManager myself;

    public static StyleManager getInstance() {
        if (myself == null) {
            myself = new StyleManager();
        }
        return myself;
    }

    public StyleManager() {
    }

    public String getStyle() {
        return Objects.requireNonNull(getClass().getResource(STYLE_PATH)).toExternalForm();
    }

    public Image getEditorIcon(){
        return new Image(Objects.requireNonNull(getClass().getResourceAsStream(EDITOR_ICON_PATH)));
    }

    public Image getAboutIcon(){
        return new Image(Objects.requireNonNull(getClass().getResourceAsStream(ABOUT_ICON_PATH)));
    }

    public Image getSettingsIcon(){
        return new Image(Objects.requireNonNull(getClass().getResourceAsStream(SETTINGS_ICON_PATH)));
    }
}
