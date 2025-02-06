package ch.supsi.os.frontend.model;

import ch.supsi.backend.application.PipelineController;

import java.util.List;


public class PipelineModelF extends AbstractModel implements PipelineModelFrontInterface{
    private static PipelineModelF myself;
    private final PipelineController pipelineController;

    public static PipelineModelF getInstance() {
        if (myself == null) {
            myself = new PipelineModelF();
        }
        return myself;
    }

    public PipelineModelF() {
        pipelineController = PipelineController.getInstance();
    }

    @Override
    public void addTransformation(String id) {
        pipelineController.addTransformation(id);
    }

    @Override
    public void clearPipeline(){
        pipelineController.clearPipeline();
    }

    @Override
    public List<String> transformationsInPipeline(){
        return pipelineController.getTransformationNames();
    }

    public void runPipeline(){
        pipelineController.runPipeline();
    }
}
