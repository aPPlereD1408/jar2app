package de.kruemmling.jar2app.javafx.jar2appapplered1408javafx.backend.gui;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class InstanceCounter {
    private static final IntegerProperty instanceCount = new SimpleIntegerProperty(0);

    public static int getInstanceCount() {
        return instanceCount.get();
    }

    public static void incrementInstanceCount() {
        instanceCount.set(instanceCount.get() + 1);
    }

    public static void decrementInstanceCount() {
        instanceCount.set(instanceCount.get() - 1);
    }
}

