package ch.supsi.backend.dataAccess.ExstensionsIO;

import ch.supsi.backend.domain.PixelModel;

import java.util.Scanner;

public interface IOinterface {
    String getMagicNumber();
    String getFormat();
    PixelModel[][] getMatrix(Scanner scanner, int width, int height);
    void save(String path, PixelModel[][] matrix);
}
