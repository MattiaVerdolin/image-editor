package ch.supsi.os.frontend.view;

import ch.supsi.backend.domain.PixelModel;
import ch.supsi.os.frontend.model.AbstractModel;
import ch.supsi.os.frontend.model.ImageModelF;
import ch.supsi.os.frontend.model.ImageModelFrontInterface;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.StackPane;


import java.io.IOException;
import java.net.URL;

public class ImageViewerView implements UncontrolledFxView {
    @FXML
    ImageView display;
    @FXML
    private StackPane stackPane;

    private static ImageViewerView myself;
    private ImageModelFrontInterface imageModelFront;

    public static ImageViewerView getInstance() {
        if (myself == null) {
            myself = new ImageViewerView();

            try {
                URL fxmlUrl = ImageViewerView.class.getResource("/fxml/ImageViewer.fxml");
                if (fxmlUrl != null) {
                    FXMLLoader fxmlLoader = new FXMLLoader(fxmlUrl);
                    fxmlLoader.setController(myself);
                    fxmlLoader.load();
                }

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        return myself;
    }

    @Override
    public void initialize(AbstractModel model) {
        imageModelFront = (ImageModelF) model;
    }

    @Override
    public Node getNode() {
        return this.stackPane;
    }

    @Override
    public void update() {
        PixelModel[][] matrix = imageModelFront.getImage();

        int height = matrix.length;
        int width = matrix[0].length;

        // Crea un WritableImage dalle dimensioni della matrice
        WritableImage writableImage = new WritableImage(width, height);
        PixelWriter pixelWriter = writableImage.getPixelWriter();

        // Scrive i pixel della matrice nell'immagine
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                PixelModel currentPixel = matrix[y][x];
                int argb;

                // Se i valori RGB sono definiti (non sono un'immagine in scala di grigi)
                if (currentPixel.getR() != -1 && currentPixel.getG() != -1 && currentPixel.getB() != -1) {
                    // Se è a colori, usa i valori RGB
                    int r = currentPixel.getR();
                    int g = currentPixel.getG();
                    int b = currentPixel.getB();
                    argb = (0xFF << 24) | (r << 16) | (g << 8) | b;
                } else {
                    // Se è un'immagine in scala di grigi (valori RGB non definiti), usa un valore predefinito
                    int grayscale = (currentPixel.getR() + currentPixel.getG() + currentPixel.getB()) / 3;
                    argb = (0xFF << 24) | (grayscale << 16) | (grayscale << 8) | grayscale;
                }

                pixelWriter.setArgb(x, y, argb);
            }
        }

        // Visualizza l'immagine in una ImageView
        display.setImage(writableImage);
    }




}
