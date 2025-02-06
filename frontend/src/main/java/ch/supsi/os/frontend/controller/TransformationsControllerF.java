package ch.supsi.os.frontend.controller;


import ch.supsi.os.frontend.model.PipelineModelF;
import ch.supsi.os.frontend.model.PipelineModelFrontInterface;
import ch.supsi.os.frontend.model.TranslationService;
import ch.supsi.os.frontend.view.ControlledFxView;
import ch.supsi.os.frontend.view.PipelineView;

public class TransformationsControllerF implements AvTrasformationEventHandler{

    private static TransformationsControllerF myself;
    private final PipelineModelFrontInterface pipeline;
    private final LogControllerF logController;

    //SERVICE
    private final TranslationService translationService;

    private final ControlledFxView pipelineView;

    public static TransformationsControllerF getInstance() {
        if (myself == null) {
            myself = new TransformationsControllerF();
        }
        return myself;
    }

    public TransformationsControllerF() {
        this.pipeline = PipelineModelF.getInstance();
        this.pipelineView = PipelineView.getInstance();
        this.logController = LogControllerF.getInstance();
        this.translationService = TranslationService.getInstance();
    }

    @Override
    public void addTrasformation(String id, String transformation){
        logController.addMessageToModel(translationService.translate("log.transformation") + transformation);
        pipeline.addTransformation(id);
        pipelineView.update();
    }
}
