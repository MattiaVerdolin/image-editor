package ch.supsi.backend.application;

import ch.supsi.backend.domain.TransformationsModel;
import ch.supsi.backend.domain.AvTransformationModelBackInterface;
import ch.supsi.backend.domain.transformation.AvTransformationInterface;

import java.util.List;
import java.util.Map;

public class TransformationsController implements ListInterface{
    AvTransformationModelBackInterface avTransformationModelBack;

    private static TransformationsController myself;

    public static TransformationsController getInstance() {
        if (myself == null) {
            myself = new TransformationsController();
        }
        return myself;
    }

    public TransformationsController() {
        avTransformationModelBack = TransformationsModel.getInstance();
    }

    @Override
    public List<String> getTransformationNames() {
        return avTransformationModelBack.getTransformationsNames();
    }

    public Map<String, String> getNamesId() {
        return avTransformationModelBack.getTransformationsNamesId();
    }

    protected List<AvTransformationInterface> getTransformations() {
        return avTransformationModelBack.getTransformationsItems();
    }
}
