package ch.supsi.backend.domain;

public class PixelModel {
    private int red;
    private int green;
    private int blue;

    // Costruttore per immagini PPM (RGB)
    public PixelModel(int red, int green, int blue) {
        this.red = clamp(red, 0, 255);
        this.green = clamp(green, 0, 255);
        this.blue = clamp(blue, 0, 255);
    }

    // Costruttore per immagini PGM/PBM (scala di grigi)
    public PixelModel(int grayscale) {
        this.red = grayscale;
        this.green = grayscale;
        this.blue = grayscale;
    }

    public int getR() {
        return red;
    }

    public int getG() {
        return green;
    }

    public int getB() {
        return blue;
    }


    // Funzione di supporto per evitare che i valori escano dal range [0, 255]
    private int clamp(int value, int min, int max) {
        return Math.max(min, Math.min(max, value));
    }

    @Override
    public String toString() {
        return "R: " + red + " G: " + green + " B: " + blue;
    }
}
