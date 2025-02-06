package ch.supsi.backend.domain;

import ch.supsi.backend.domain.transformation.AvTransformationInterface;
import org.reflections.Reflections;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TransformationsModel implements AvTransformationModelBackInterface{

    private List<AvTransformationInterface> transformationsItems;
    private final static String TRANSFORMATIONS_PACKAGE = "ch.supsi.backend.domain.transformation";
    private static TransformationsModel myself;

    // Costruttore privato
    private TransformationsModel() {
        transformationsItems = new ArrayList<>();
        populateTransformations(); // Usa reflection per popolare la lista
    }

    // Metodo per ottenere l'istanza del singleton
    public static TransformationsModel getInstance() {
        if (myself == null) {
            myself = new TransformationsModel();
        }
        return myself;
    }

    // Metodo per ottenere la mappa dei nomi e degli id delle trasformazioni
    @Override
    public Map<String,String> getTransformationsNamesId() {
        Map <String, String> transformationsNamesId = new HashMap<>();
        for (AvTransformationInterface transformation : transformationsItems) {
            transformationsNamesId.put(transformation.getId(), transformation.getName());
        }
        return transformationsNamesId;
    }

    @Override
    public List<String> getTransformationsNames() {
        List <String> transformationsNames = new ArrayList<>();
        for (AvTransformationInterface transformation : transformationsItems) {
            transformationsNames.add(transformation.getName());
        }
        return transformationsNames;
    }

    public void setTransformationsItems(List<AvTransformationInterface> items) {
        this.transformationsItems = items;
    }

    @Override
    public List<AvTransformationInterface> getTransformationsItems() {
        return transformationsItems;
    }

    // Metodo per popolare la lista usando reflection
    public void populateTransformations() {
        try {
            // Usa la libreria Reflections per trovare tutte le classi che implementano AvTransformationInterface
            Reflections reflections = new Reflections(TRANSFORMATIONS_PACKAGE); // Sostituisci con il package corretto
            // Trova tutte le classi che implementano AvTransformationInterface
            for (Class<? extends AvTransformationInterface> clazz : reflections.getSubTypesOf(AvTransformationInterface.class)) {
                // Escludi classi astratte o interfacce
                if (!Modifier.isAbstract(clazz.getModifiers()) && !clazz.isInterface()) {
                    // Crea una nuova istanza della classe e aggiungila alla lista
                    transformationsItems.add(clazz.getDeclaredConstructor().newInstance());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

