package ch.supsi.os.frontend.model;

import ch.supsi.backend.application.TransformationsController;

import java.util.Map;

public class TransformationsModelF extends AbstractModel implements TransformationsModelFrontInterface {
    private TransformationsController controller;
    private static TransformationsModelF myself;

    public TransformationsModelF() {
        this.controller = TransformationsController.getInstance();
    }

    public static TransformationsModelF getInstance() {
        if (myself == null) {
            myself = new TransformationsModelF();
        }
        return myself;
    }

    public void setController(TransformationsController controller) {
        this.controller = controller;
    }

    public Map<String, String> getNamesId(){
        return controller.getNamesId();
    }


}
