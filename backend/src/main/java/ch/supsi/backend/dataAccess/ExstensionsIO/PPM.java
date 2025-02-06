package ch.supsi.backend.dataAccess.ExstensionsIO;

import ch.supsi.backend.domain.PixelModel;

import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


public class PPM implements IOinterface{
    private final static String MAGIC_NUMBER = "P3";
    private final static String FORMAT = "ppm";

    @Override
    public String getMagicNumber() {
        return MAGIC_NUMBER;
    }

    @Override
    public String getFormat() {
        return FORMAT;
    }

    @Override
    public PixelModel[][] getMatrix(Scanner scanner, int width, int height) {

        int maxVal = scanner.nextInt(); // Read the max grayscale or color value

        PixelModel[][] imageMatrix = new PixelModel[height][width];

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                int r = scanner.nextInt();
                int g = scanner.nextInt();
                int b = scanner.nextInt();
                imageMatrix[i][j] = new PixelModel(r, g, b);
            }
        }
        return imageMatrix;
    }

    @Override
    public void save(String path, PixelModel[][] matrix){

        // Crea un'immagine RGB
        File newFilePPM = new File(path);  //path file con aggiunta formato
        int height = matrix.length;
        int width = matrix[0].length;

        BufferedImage rgbImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);  //classe che rappresenta un'immagine composta da pixel memorizzati in memoria.
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                PixelModel pixel = matrix[i][j]; //Recupero il pixel dalla matrice di pixel in corrispondenza della posizione (i, j)
                int red = clamp(pixel.getR(), 0, 255);  //clamp è usata per assicurarsi che i valori siano limitati nel range compreso tra 0 e 255
                int green = clamp(pixel.getG(), 0, 255);
                int blue = clamp(pixel.getB(), 0, 255);
                int rgb = (red << 16) | (green << 8) | blue;
                        /*
                        Questa istruzione combina i tre valori di rosso, verde e blu in un singolo numero intero a 24 bit:
                        red << 16: Sposta i bit del valore rosso a sinistra di 16 posizioni, così che occupi i primi 8 bit del numero.
                        green << 8: Sposta i bit del valore verde a sinistra di 8 posizioni, così che occupi i bit centrali.
                        blue: Il valore blu occupa i bit meno significativi.
                         */
                rgbImage.setRGB(j, i, rgb);
            }
        }
        // Salva l'immagine PPM in formato ASCII
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(newFilePPM))) {
            writer.write("P3\n"); // PPM formato ASCII scrivo il magic number
            writer.write(width + " " + height + "\n"); //scrivo la seconda riga: dimensione immagine
            writer.write("255\n"); // Massimo valore di colore: terza riga
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    writer.write(clamp(matrix[i][j].getR(), 0, 255) + " "); //scrivo il valore R
                    writer.write(clamp(matrix[i][j].getG(), 0, 255) + " "); //scrivo il valore G
                    writer.write(clamp(matrix[i][j].getB(), 0, 255) + " "); //scrivo il valore B
                }
                writer.newLine();
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        System.out.println("Immagine PPM salvata: " + path);
    }

    private int clamp(int value, int min, int max) {
        return Math.max(min, Math.min(max, value));
    }

}
