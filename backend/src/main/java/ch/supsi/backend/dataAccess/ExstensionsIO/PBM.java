package ch.supsi.backend.dataAccess.ExstensionsIO;

import ch.supsi.backend.domain.PixelModel;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


public class PBM implements IOinterface{
    private final static String MAGIC_NUMBER = "P1";
    private final static String FORMAT = "pbm";

    @Override
    public String getMagicNumber() {
        return MAGIC_NUMBER;
    }

    @Override
    public String getFormat() {
        return FORMAT;
    }

    @Override
    // Metodo per leggere PBM (bianco e nero) in ASCII
    public PixelModel[][] getMatrix(Scanner scanner, int width, int height) {
        PixelModel[][] imageMatrix = new PixelModel[height][width];

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                int pixelValue = scanner.nextInt();
                int colorValue = (pixelValue == 1) ? 0 : 255;
                imageMatrix[i][j] = new PixelModel(colorValue);
            }
        }

        return imageMatrix;
    }

    @Override
    public void save(String path, PixelModel[][] matrix) {

        File newFilePBM = new File(path);
        int height = matrix.length;
        int width = matrix[0].length;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(newFilePBM))) {
            writer.write("P1\n");
            writer.write(width + " " + height + "\n");

            // Scrivi i valori binari (bianco e nero) per ogni pixel
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    PixelModel pixel = matrix[i][j];

                    // Se il valore del rosso è 255, trattiamo come nero, altrimenti bianco
                    int binaryValue = pixel.getR() < 127 ? 1 : 0;
                    writer.write(binaryValue + " ");
                }
                writer.newLine();  // Aggiungi newline alla fine di ogni riga
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Immagine PBM salvata: " + path);
    }

}
