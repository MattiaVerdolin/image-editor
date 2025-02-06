package ch.supsi.os.frontend.controller;

import ch.supsi.os.frontend.model.*;
import ch.supsi.os.frontend.view.ControlledFxView;
import ch.supsi.os.frontend.view.ImageViewerView;
import ch.supsi.os.frontend.view.PipelineView;
import ch.supsi.os.frontend.view.UncontrolledFxView;

public class PipelineControllerF implements PipelineEventHandler {
    
    private static PipelineControllerF myself;
    private final PipelineModelFrontInterface pipelineModel;
    private final ControlledFxView pipelineView;
    private final UncontrolledFxView imageViewer;
    private final LogControllerF logControllerF;
    private final TranslationService translationService;

    public static PipelineControllerF getInstance() {
        if (myself == null) {
            myself = new PipelineControllerF();
        }
        return myself;
    }

    public PipelineControllerF() {
        this.pipelineModel = PipelineModelF.getInstance();
        this.pipelineView = PipelineView.getInstance();
        this.imageViewer = ImageViewerView.getInstance();
        this.logControllerF = LogControllerF.getInstance();
        this.translationService = TranslationService.getInstance();

    }
    
    public void addPipelineLine(String transformation) {
        pipelineModel.addTransformation(transformation);
        if (pipelineView != null) {
            pipelineView.update();
        } else {
            System.err.println("pipelineView è null");
        }
    }

    @Override
    public void cancelPipeline() {
        pipelineModel.clearPipeline();
        pipelineView.update();
        logControllerF.addMessageToModel(translationService.translate("log.cancel"));
    }

    @Override
    public void run() {
        pipelineModel.runPipeline();
        imageViewer.update();
        logControllerF.addMessageToModel(translationService.translate("log.run"));
    }
}
