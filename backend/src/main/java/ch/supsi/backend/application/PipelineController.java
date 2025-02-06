package ch.supsi.backend.application;

import ch.supsi.backend.domain.PipelineModel;
import ch.supsi.backend.domain.PipelineModelInterface;
import ch.supsi.backend.domain.PixelModel;
import ch.supsi.backend.domain.transformation.AvTransformationInterface;

import java.util.List;

public class PipelineController implements ListInterface{
    private final PipelineModelInterface pipelineModel;
    private final TransformationsController transformationsController;
    private final ImageController imageController;

    private static PipelineController myself;

    public static PipelineController getInstance() {
        if (myself == null) {
            myself = new PipelineController();
        }
        return myself;
    }

    public PipelineController() {
        pipelineModel = PipelineModel.getInstance();
        transformationsController = TransformationsController.getInstance();
        imageController = ImageController.getInstance();
    }

    @Override
    public List<String> getTransformationNames() {
        return pipelineModel.getTransformationsNames();
    }


    public void addTransformation(String id) {
        List<AvTransformationInterface> avTransformations = transformationsController.getTransformations();

        for (AvTransformationInterface avTransformation : avTransformations) {
            if (avTransformation.getId().equals(id)) {
                pipelineModel.addTransformation(avTransformation);
                break;
            }
        }
    }

    public void clearPipeline(){
        pipelineModel.clearPipeline();
    }

    public void runPipeline(){
        PixelModel[][] newImage = pipelineModel.runPipeline(imageController.getImage());
        imageController.setNewMatrix(newImage);
    }

}
