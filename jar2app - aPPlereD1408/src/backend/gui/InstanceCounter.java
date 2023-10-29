package backend.gui;

public class InstanceCounter {
    private static int instanceCount = 0;

    public static int getInstanceCount() {
        return instanceCount;
    }

    public static void incrementInstanceCount() {
        instanceCount++;
    }

    public static void decrementInstanceCount() {
        instanceCount--;}
}
