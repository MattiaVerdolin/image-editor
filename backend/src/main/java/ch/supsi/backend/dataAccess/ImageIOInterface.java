package ch.supsi.backend.dataAccess;

import ch.supsi.backend.dataAccess.ExstensionsIO.IOinterface;
import ch.supsi.backend.domain.PixelModel;

import java.util.Set;

public interface ImageIOInterface {
    PixelModel[][] load(String imagePath);

    IOinterface getIdentifiedClass(String input);
    Set<Class<? extends IOinterface>> getIoClasses();
}
