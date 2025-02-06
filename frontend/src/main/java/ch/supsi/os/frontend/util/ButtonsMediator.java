package ch.supsi.os.frontend.util;

import ch.supsi.os.frontend.view.AvTrasformationView;
import ch.supsi.os.frontend.view.MenuBarView;
import ch.supsi.os.frontend.view.PipelineView;

public class ButtonsMediator {
    AvTrasformationView avTrasformationView;
    PipelineView pipelineView;
    MenuBarView menuBarView;

    private static ButtonsMediator myself;

    public static ButtonsMediator getInstance() {
        if (myself == null) {
            myself = new ButtonsMediator();
        }
        return myself;
    }

    public ButtonsMediator() {
        avTrasformationView = AvTrasformationView.getInstance();
        pipelineView = PipelineView.getInstance();
        menuBarView = MenuBarView.getInstance();
    }

    public void enableButtons(boolean enable){
        avTrasformationView.enableListView(enable);
        pipelineView.enableButtons(enable);
        menuBarView.enableSaveButtons(enable);
    }
}
