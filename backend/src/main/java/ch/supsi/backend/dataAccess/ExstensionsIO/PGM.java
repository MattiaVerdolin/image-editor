package ch.supsi.backend.dataAccess.ExstensionsIO;

import ch.supsi.backend.dataAccess.ExstensionsIO.IOinterface;
import ch.supsi.backend.domain.PixelModel;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class PGM implements IOinterface {
    private final static String MAGIC_NUMBER = "P2";
    private final static String FORMAT = "pgm";

    @Override
    public String getMagicNumber() {
        return MAGIC_NUMBER;
    }

    @Override
    public String getFormat() {
        return FORMAT;
    }

    @Override
    // Metodo per leggere PGM (scala di grigi) in ASCII
    public PixelModel[][] getMatrix(Scanner scanner, int width, int height) {

        // Leggi il valore massimo di grigio
        int maxVal = scanner.nextInt();
        scanner.nextLine(); // Consuma la riga vuota dopo maxVal

        // Crea la matrice di pixel
        PixelModel[][] imageMatrix = new PixelModel[height][width];

        // Leggi i pixel
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                int gray = (scanner.nextInt() * 255) / maxVal;
                imageMatrix[i][j] = new PixelModel(gray);
            }
        }

        return imageMatrix;
    }

    @Override
    public void save(String path, PixelModel[][] matrix) {
        File newFilePGM = new File(path);
        int height = matrix.length;
        int width = matrix[0].length;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(newFilePGM))) {
            writer.write("P2\n");  // PGM formato ASCII
            writer.write(width + " " + height + "\n");
            writer.write("255\n");  // Massimo valore di grigio

            // Scrivi i valori di grigio per ogni pixel
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    PixelModel pixel = matrix[i][j];

                    // Se il pixel è un'immagine in scala di grigi (ovvero senza valori RGB)
                    if (pixel.getR() == -1 && pixel.getG() == -1 && pixel.getB() == -1) {
                        writer.write("0 ");  // Assume bianco o nero
                    } else {
                        // Calcolo della scala di grigi dai valori RGB
                        int grayscale = (int) (0.299 * pixel.getR() + 0.587 * pixel.getG() + 0.114 * pixel.getB());
                        writer.write(clamp(grayscale, 0, 255) + " ");  // Clamp the value to [0, 255]
                    }
                }
                writer.newLine();  // Aggiungi newline alla fine di ogni riga
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Immagine PGM salvata: " + path);
    }

    protected int clamp(int value, int min, int max) {
        return Math.max(min, Math.min(max, value));
    }
}
