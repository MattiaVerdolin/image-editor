package ch.supsi.backend.domain.transformation;

import ch.supsi.backend.application.TranslationController;
import ch.supsi.backend.domain.PixelModel;

import java.util.UUID;

public class NegativeTransformation implements AvTransformationInterface{

    private final static String NAME = "transformation.negative";
    private final String ID;
    TranslationController controller;


    public NegativeTransformation() {
        controller = TranslationController.getInstance();
        ID = UUID.randomUUID().toString();
    }

    @Override
    public PixelModel[][] apply(PixelModel[][] matrix) {
        PixelModel[][] negativeMatrix = new PixelModel[matrix.length][matrix[0].length];

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                PixelModel currentPixel = matrix[i][j];

                // Per ogni pixel, invertiamo i valori RGB
                int r = currentPixel.getR();
                int g = currentPixel.getG();
                int b = currentPixel.getB();

                // Controlla se il pixel è stato creato per un'immagine PGM/PBM (dove RGB non è usato)
                if (r == -1 && g == -1 && b == -1) {
                    // Per immagini in scala di grigi (PGM), possiamo semplicemente invertire su scala 0-255
                    negativeMatrix[i][j] = new PixelModel(255);  // Inversione di scala di grigi (bianco)
                } else {
                    // Inverti i valori per i canali R, G, B
                    int newR = 255 - r;
                    int newG = 255 - g;
                    int newB = 255 - b;

                    // Crea un nuovo PixelModel con i valori RGB invertiti
                    negativeMatrix[i][j] = new PixelModel(newR, newG, newB);
                }
            }
        }
        return negativeMatrix;
    }

    @Override
    public String getName() {
        return controller.translate(NAME);
    }

    @Override
    public String getId() {
        return ID;
    }


}
