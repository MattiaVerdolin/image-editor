package ch.supsi.os.frontend;

import ch.supsi.backend.domain.LogModel;
import ch.supsi.backend.domain.LogObserver;
import ch.supsi.os.frontend.controller.*;
import ch.supsi.os.frontend.model.*;
import ch.supsi.os.frontend.util.StageManager;
import ch.supsi.os.frontend.util.StyleManager;
import ch.supsi.os.frontend.view.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


public class MainFX extends Application {

    public static final String APP_TITLE = "2D image editor";

    private final AbstractModel pipelineModel;
    private final AbstractModel avTrasformationModel;
    private final AbstractModel imageModel;
    private final AbstractModel logModel;
    private final AbstractModel aboutModel;
    private final AbstractModel preferencesModel;

    private final ControlledFxView avTransformationView;
    private final ControlledFxView pipelineView;
    private final ControlledFxView menuBarView;
    private final UncontrolledFxView imageViewerView;
    private final UncontrolledFxView logView;
    private final AdditionalStageInterface aboutView;
    private final AdditionalStageInterface preferencesView;

    private final PipelineEventHandler pipelineEventHandler;
    private final AvTrasformationEventHandler avTrasformationEventHandler;
    private final OpenEventHandler openEventHandler;
    private QuitEventHandler quitEventHandler;
    private final PersistenceEventHandler persistenceEventHandler;
    private final AboutEventHandler aboutEventHandler;
    private final PreferencesEventHandler preferencesEventHandler;

    private final TranslationService translationService;
    private final StyleManager styleManager;
    private final StageManager stageManager;

    public MainFX() {
        //MODELS
        pipelineModel = PipelineModelF.getInstance();
        avTrasformationModel = TransformationsModelF.getInstance();
        imageModel = ImageModelF.getInstance();
        logModel = LogModelF.getInstance();
        aboutModel = AboutModelF.getInstance();
        preferencesModel = EditorAppModelF.getInstance();

        //VIEWS
        this.avTransformationView = AvTrasformationView.getInstance();
        this.pipelineView = PipelineView.getInstance();
        this.imageViewerView = ImageViewerView.getInstance();
        this.logView = LogView.getInstance();
        this.menuBarView = MenuBarView.getInstance();
        this.aboutView = AboutView.getInstance();
        this.preferencesView = PreferencesView.getInstance();

        //CONTROLLERS

        this.pipelineEventHandler = PipelineControllerF.getInstance();
        this.avTrasformationEventHandler = TransformationsControllerF.getInstance();
        this.openEventHandler = OpenControllerF.getInstance();
        this.quitEventHandler = QuitControllerF.getInstance();
        this.persistenceEventHandler = PersistenceControllerF.getInstance();
        this.aboutEventHandler = AboutControllerF.getInstance();
        this.preferencesEventHandler = PreferencesControllerF.getInstance();

        //SERVICES
        this.translationService = TranslationService.getInstance();
        this.styleManager = StyleManager.getInstance();
        this.stageManager = StageManager.getInstance();


        //SCAFFOLDING of M-V-C
        this.pipelineView.initialize(pipelineModel, this.pipelineEventHandler);
        this.avTransformationView.initialize(avTrasformationModel, this.avTrasformationEventHandler);
        this.imageViewerView.initialize(this.imageModel);
        this.logView.initialize(this.logModel);
        this.menuBarView.initialize(null,persistenceEventHandler, openEventHandler, quitEventHandler,preferencesEventHandler, aboutEventHandler);
        this.aboutView.initialize(aboutModel, this.aboutEventHandler);
        this.preferencesView.initialize(preferencesModel, preferencesEventHandler);

        translationService.updateMenuBar((MenuBarView) menuBarView);
        translationService.updatePipeLine((PipelineView) pipelineView);
        translationService.updateAvTrans((AvTrasformationView) avTransformationView);
        translationService.updateAbout((AboutView) aboutView);
        translationService.updatePreferences((PreferencesView) preferencesView);

        LogModel.getInstance().addObserver((LogObserver) logView);

    }


    @Override
    public void start(Stage stage){
        quitEventHandler.setStage(stage);
        // Registra lo stage principale nel StageManager
        stageManager.registerStage(stage);

        stage.setOnCloseRequest(
                windowEvent -> {
                    windowEvent.consume();
                    stageManager.closeAllStages();
                }
        );

        //SCAFFOLDING of MAIN PANE
        BorderPane root = new BorderPane();
        root.setTop(this.menuBarView.getNode());
        root.setCenter(this.imageViewerView.getNode());
        root.setBottom(this.logView.getNode());
        root.setLeft(this.avTransformationView.getNode());
        root.setRight(this.pipelineView.getNode());

        Scene scene = new Scene(root);
        stage.getIcons().add(styleManager.getEditorIcon());
        scene.getStylesheets().add(styleManager.getStyle());

        stage.setTitle(APP_TITLE);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.toFront();
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
