package ch.supsi.os.frontend.view;

import ch.supsi.os.frontend.controller.EventHandler;
import ch.supsi.os.frontend.model.AbstractModel;

public interface ControlledView extends DataView{
    void initialize(AbstractModel model, EventHandler... eventHandler);
}
