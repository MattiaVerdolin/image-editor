package ch.supsi.os.frontend.view;

import ch.supsi.os.frontend.controller.MenuBarEventHandler;

import javax.xml.crypto.Data;
import java.util.Set;

public interface MenuBarInitialization extends DataView {
    void initialize(Set<MenuBarEventHandler> controllers);
}
