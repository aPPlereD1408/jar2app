module de.kruemmling.jar2app.javafx.jar2appapplered1408javafx {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens de.kruemmling.jar2app.javafx.jar2appapplered1408javafx to javafx.fxml;
    exports de.kruemmling.jar2app.javafx.jar2appapplered1408javafx;
}