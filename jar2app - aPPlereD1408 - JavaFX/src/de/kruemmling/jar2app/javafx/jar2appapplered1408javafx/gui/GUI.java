package de.kruemmling.jar2app.javafx.jar2appapplered1408javafx.gui;

import de.kruemmling.jar2app.javafx.jar2appapplered1408javafx.backend.executable.MakeLauncherExecutable;
import de.kruemmling.jar2app.javafx.jar2appapplered1408javafx.backend.executable.ModifyLauncherFile;
import de.kruemmling.jar2app.javafx.jar2appapplered1408javafx.backend.gui.CopyElements;
import de.kruemmling.jar2app.javafx.jar2appapplered1408javafx.backend.gui.InstanceCounter;
import de.kruemmling.jar2app.javafx.jar2appapplered1408javafx.backend.plist.CreateInfoPlistXML;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.geometry.Insets;

import java.io.File;
import java.io.IOException;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;
import java.nio.file.Path;
import java.nio.file.Paths;

public class GUI extends Parent {
    // Setup
    public static Color bgColor = Color.rgb(41, 49, 56);

    private static Stage stage;

    public static File appFolder;
    public static Path macosPath;
    public static Path resourcesPath;

    public static AtomicBoolean isLibFolderChosen = new AtomicBoolean(false);

    public static TextField inputAppName = new TextField();

    public static Button chooseJarButton = new Button("Select .jar file");
    public static FileChooser jarChooser = new FileChooser();
    public static File jarFile;

    public static Button chooseIcnsButton = new Button("Select .icns file");
    public static FileChooser icnsChooser = new FileChooser();
    public static File icnsFile;

    // Info.plist Properties
    public static ObservableList<String> optionsApplicationCategoryType = FXCollections.observableArrayList(
            "Business", "Developer Tools", "Education", "Entertainment", "Finance", "Games", "Action Games",
            "Adventure Games", "Arcade Games", "Board Games", "Card Games", "Casino Games", "Dice Games",
            "Educational Games", "Family Games", "Kids Games", "Music Games", "Puzzle Games", "Racing Games",
            "Role Playing Games", "Simulation Games", "Sports Games", "Strategy Games", "Trivia Games",
            "Word Games", "Graphics Design", "Healthcare Fitness", "Lifestyle", "Medical", "Music",
            "News", "Photography", "Productivity", "Reference", "Social Networking", "Sports", "Travel",
            "Utilities", "Video", "Weather"
    );

    public static TextField nativeDevelopmentRegion = new TextField();
    public static TextField bundleIdentifier = new TextField();
    public static TextField bundleVersion = new TextField();
    public static TextField bundleVersionShortString = new TextField();
    public static ComboBox<String> applicationCategoryType = new ComboBox<>(optionsApplicationCategoryType);

    public static TextField humanReadableCopyright = new TextField();

    public GUI(Stage stage) {
        // Setup
        GUI.stage = stage; // Assign the provided stage to the instance variable
        stage.setOnCloseRequest(event -> InstanceCounter.decrementInstanceCount());

        if (InstanceCounter.getInstanceCount() == 1) {
            stage.setOnCloseRequest(event -> stage.close());
        } else {
            stage.setOnCloseRequest(event -> stage.close());
        }

        stage.setTitle("jar2app by aPPlereD1408");
        stage.setMaxWidth(700);
        stage.setMaxHeight(400);
        stage.centerOnScreen();

        // Startfenster
        StackPane root = new StackPane();
        root.setBackground(new Background(new BackgroundFill(bgColor, null, null)));
        root.setAlignment(Pos.CENTER); // Zentrierung der Elemente

        VBox vbox = new VBox(20);
        vbox.setPadding(new Insets(20));

        Label title = new Label("jar2app by aPPlereD1408");
        title.setFont(new Font("Inter-Bold", 35));
        title.setTextFill(Color.WHITE);

        Label subTitle = new Label("Create a macOS app from a .jar file");
        subTitle.setFont(new Font("Inter-SemiBold", 18));
        subTitle.setTextFill(Color.WHITE);

        String textCreateAppButton = "START\nDetermine destination folder";
        Button createAppButton = new Button(textCreateAppButton);
        createAppButton.setFont(new Font("Inter-Regular", 18));
        createAppButton.setTextFill(Color.BLACK);

        createAppButton.setContentDisplay(ContentDisplay.CENTER);
        createAppButton.setAlignment(Pos.CENTER);
        createAppButton.setMinSize(200, 50);
        createAppButton.setCursor(Cursor.HAND);
        createAppButton.setStyle(
                "-fx-text-fill: black;" +
                        "-fx-background-color: linear-gradient(to bottom, #59b362, #4a8c51);"
        );
        createAppButton.setOnMouseEntered(event -> createAppButton.setStyle(
                "-fx-text-fill: white;" +
                        "-fx-background-color: linear-gradient(to bottom, #59b362, #4a8c51);"
        ));
        createAppButton.setOnMouseExited(event -> createAppButton.setStyle(
                "-fx-text-fill: black;" +
                        "-fx-background-color: linear-gradient(to bottom, #59b362, #4a8c51);"
        ));

        createAppButton.setOnAction(e -> {
            DirectoryChooser directoryChooser = new DirectoryChooser();
            directoryChooser.setTitle("Select destination folder of the app");
            File selectedFolder = directoryChooser.showDialog(stage);

            screenSelectDestinationFolder(selectedFolder);
        });

        vbox.getChildren().addAll(title, subTitle, createAppButton);
        root.getChildren().add(vbox);

        Scene scene = new Scene(root, 700, 400);
        stage.setScene(scene);

        stage.show();
    }

    private void screenSelectDestinationFolder(File selectedFolder) {
        // Setup
        StackPane root = new StackPane();
        root.setBackground(new Background(new BackgroundFill(bgColor, null, null)));

        VBox vbox = new VBox(20);
        vbox.setAlignment(Pos.CENTER); // Zentrierung der Elemente in vertikaler Richtung

        String buttonText = selectedFolder.getAbsolutePath();
        Button createAppButton = new Button(buttonText);
        createAppButton.setFont(new Font("Inter-Regular", 18));
        createAppButton.setTextFill(Color.BLACK);
        VBox.setMargin(createAppButton, new Insets(-160, 0, 40, 0)); // Oben, Rechts, Unten, Links

        Label textAppName = new Label("What should the app be called?");
        textAppName.setFont(new Font("Inter-Medium", 25));
        textAppName.setTextFill(Color.WHITE);
        VBox.setMargin(textAppName, new Insets(0, 0, 10, 0)); // Oben, Rechts, Unten, Links

        HBox hbox = new HBox(10);
        hbox.setAlignment(Pos.CENTER); // Zentrierung der Elemente in horizontaler Richtung
        inputAppName.setFont(new Font("Inter-Regular", 16));
        Button weiterButton = new Button("Next step: select files");
        weiterButton.setFont(new Font("Inter-Regular", 18));

        weiterButton.setOnAction(e -> {
            String folderName = inputAppName.getText();

            String appFolderPath = selectedFolder.getAbsolutePath() + File.separator + folderName + ".app";
            appFolder = new File(appFolderPath);
            appFolder.mkdir();

            Path contentsPath = Paths.get(appFolderPath, "contents");
            contentsPath.toFile().mkdir();

            macosPath = Paths.get(contentsPath.toString(), "MacOS");
            macosPath.toFile().mkdir();

            resourcesPath = Paths.get(contentsPath.toString(), "Resources");
            resourcesPath.toFile().mkdir();

            screenUploadJarAndIcns(macosPath, resourcesPath);
        });
        weiterButton.setCursor(Cursor.HAND);
        weiterButton.setStyle(
                "-fx-text-fill: black;" +
                        "-fx-background-color: linear-gradient(to bottom, #b3b062, #ada936);"
        );
        weiterButton.setOnMouseEntered(event -> weiterButton.setStyle(
                "-fx-text-fill: white;" +
                        "-fx-background-color: linear-gradient(to bottom, #b3b062, #ada936);"
        ));
        weiterButton.setOnMouseExited(event -> weiterButton.setStyle(
                "-fx-text-fill: black;" +
                        "-fx-background-color: linear-gradient(to bottom, #b3b062, #ada936);"
        ));

        inputAppName.textProperty().addListener((observable, oldValue, newValue) -> weiterButton.setDisable(newValue.isEmpty()));

        hbox.getChildren().addAll(inputAppName, weiterButton);

        VBox.setMargin(weiterButton, new Insets(30, 0, 0, 0)); // Oben, Rechts, Unten, Links

        vbox.getChildren().addAll(createAppButton, textAppName, hbox);

        root.getChildren().add(vbox);

        // Erstellen einer Scene und Anzeigen des Hauptfensters
        Scene scene = new Scene(root, 700, 400);
        stage.setScene(scene);
    }

    private void screenUploadJarAndIcns(Path macosPath, Path resourcesPath) {
        // Setup
        StackPane root = new StackPane();
        root.setBackground(new Background(new BackgroundFill(bgColor, null, null)));

        VBox vbox = new VBox(20);
        vbox.setAlignment(Pos.CENTER); // Zentrierung der Elemente in vertikaler Richtung
        vbox.setPadding(new Insets(20));

        chooseJarButton.setFont(new Font("Inter-Regular", 15));
        chooseIcnsButton.setFont(new Font("Inter-Regular", 15));

        Button weiterButton = new Button("Next step: informations");
        weiterButton.setDisable(true);
        weiterButton.setFont(new Font("Inter-Regular", 15));

        AtomicBoolean isIcnsFileChosen = new AtomicBoolean(false);
        AtomicBoolean isJarFileChosen = new AtomicBoolean(false);

        chooseJarButton.setOnAction(e -> {
            jarChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("JAR-Files", "*.jar"));
            jarChooser.setTitle("Select your .jar file");
            jarFile = jarChooser.showOpenDialog(stage);

            if (jarFile != null) {
                chooseJarButton.setText(jarFile.getName());
                isJarFileChosen.set(true);

                if (isJarFileChosen.get() && isIcnsFileChosen.get()) {
                    weiterButton.setDisable(false);
                }
            }
        });

        chooseIcnsButton.setOnAction(e -> {
            icnsChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Apple Icon Image-Files", "*.icns"));
            icnsChooser.setTitle("Select your icon");
            icnsFile = icnsChooser.showOpenDialog(stage);

            if (icnsFile != null) {
                chooseIcnsButton.setText(icnsFile.getName());
                isIcnsFileChosen.set(true);

                if (isJarFileChosen.get() && isIcnsFileChosen.get()) {
                    weiterButton.setDisable(false);
                }
            }
        });

        weiterButton.setOnAction(e -> {
            CopyElements.copyNeededFiles(macosPath, resourcesPath, jarFile.getAbsolutePath(), icnsFile.getAbsolutePath(), null);
            screenInfoPlist();
        });

        weiterButton.setCursor(Cursor.HAND);
        weiterButton.setStyle(
                "-fx-text-fill: black;" +
                        "-fx-background-color: linear-gradient(to bottom, #db3939, #912c2c);"
        );
        weiterButton.setOnMouseEntered(event -> weiterButton.setStyle(
                "-fx-text-fill: white;" +
                        "-fx-background-color: linear-gradient(to bottom, #db3939, #912c2c);"
        ));
        weiterButton.setOnMouseExited(event -> weiterButton.setStyle(
                "-fx-text-fill: black;" +
                        "-fx-background-color: linear-gradient(to bottom, #db3939, #912c2c);"
        ));

        vbox.getChildren().addAll(chooseJarButton, chooseIcnsButton, weiterButton);
        VBox.setMargin(weiterButton, new Insets(20, 0, 0, 0)); // Oben, Rechts, Unten, Links

        root.getChildren().add(vbox);

        // Erstellen einer Szene und Anzeigen des Hauptfensters
        Scene scene = new Scene(root, 700, 400);
        stage.setScene(scene);
    }

    private void screenInfoPlist() {
        // Setup
        VBox vbox = new VBox(20);
        vbox.setPadding(new Insets(20));
        vbox.setBackground(new Background(new BackgroundFill(bgColor, null, null)));
        vbox.setAlignment(Pos.CENTER); // Zentrierung der Elemente in vertikaler Richtung

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setAlignment(Pos.CENTER); // Zentrierung der Elemente in horizontaler Richtung

        Label textNativeDevelopmentRegion = new Label("Native Development Region:");
        textNativeDevelopmentRegion.setLabelFor(nativeDevelopmentRegion);
        textNativeDevelopmentRegion.setTextFill(Color.WHITE);
        textNativeDevelopmentRegion.setFont(new Font("Inter-Regular", 20));
        GridPane.setHalignment(textNativeDevelopmentRegion, HPos.RIGHT); // Text rechtsbündig

        Label textBundleIdentifier = new Label("Bundle Identifier:");
        textBundleIdentifier.setLabelFor(bundleIdentifier);
        textBundleIdentifier.setTextFill(Color.WHITE);
        textBundleIdentifier.setFont(new Font("Inter-Regular", 20));
        GridPane.setHalignment(textBundleIdentifier, HPos.RIGHT); // Text rechtsbündig

        Label textBundleVersion = new Label("Bundle Version:");
        textBundleVersion.setLabelFor(bundleVersion);
        textBundleVersion.setTextFill(Color.WHITE);
        textBundleVersion.setFont(new Font("Inter-Regular", 20));
        GridPane.setHalignment(textBundleVersion, HPos.RIGHT); // Text rechtsbündig

        Label textBundleShortVersion = new Label("Bundle Short Version:");
        textBundleShortVersion.setLabelFor(bundleVersionShortString);
        textBundleShortVersion.setTextFill(Color.WHITE);
        textBundleShortVersion.setFont(new Font("Inter-Regular", 20));
        GridPane.setHalignment(textBundleShortVersion, HPos.RIGHT); // Text rechtsbündig

        Label textHumanReadableCopyright = new Label("Copyright:");
        textHumanReadableCopyright.setLabelFor(humanReadableCopyright);
        textHumanReadableCopyright.setTextFill(Color.WHITE);
        textHumanReadableCopyright.setFont(new Font("Inter-Regular", 20));
        GridPane.setHalignment(textHumanReadableCopyright, HPos.RIGHT); // Text rechtsbündig

        Label textApplicationCategoryType = new Label("Application Category Type:");
        textApplicationCategoryType.setLabelFor(applicationCategoryType);
        textApplicationCategoryType.setTextFill(Color.WHITE);
        textApplicationCategoryType.setFont(new Font("Inter-Regular", 20));
        GridPane.setHalignment(textApplicationCategoryType, HPos.RIGHT); // Text rechtsbündig

        Button fertigstellenButton = new Button("Finish app!");
        fertigstellenButton.setDisable(false);
        fertigstellenButton.setFont(new Font("Inter-Regular", 18));
        fertigstellenButton.setCursor(Cursor.HAND);
        fertigstellenButton.setStyle(
                "-fx-text-fill: black;" +
                        "-fx-background-color: linear-gradient(to bottom, #475fb3, #354375);"
        );
        fertigstellenButton.setOnMouseEntered(event -> fertigstellenButton.setStyle(
                "-fx-text-fill: white;" +
                        "-fx-background-color: linear-gradient(to bottom, #475fb3, #354375);"
        ));
        fertigstellenButton.setOnMouseExited(event -> fertigstellenButton.setStyle(
                "-fx-text-fill: black;" +
                        "-fx-background-color: linear-gradient(to bottom, #475fb3, #354375);"
        ));

        fertigstellenButton.setOnAction(e -> {
            CreateInfoPlistXML.createFile();
            ModifyLauncherFile.modifyFile();
            MakeLauncherExecutable.execute();
            openNewApplication();

            // Deaktivieren Sie den Button nach erfolgreicher Aktion
            fertigstellenButton.setDisable(true);

            // Hier wird das Pop-up-Fenster angezeigt
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText(null);
            alert.setContentText("Application was built successfully!.");

            ButtonType closeButton = new ButtonType("Close", ButtonBar.ButtonData.OK_DONE);
            alert.getButtonTypes().setAll(closeButton);

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == closeButton) {
                alert.close();
            }
        });


        HBox hbox = new HBox(10);
        hbox.setAlignment(Pos.CENTER);
        hbox.getChildren().add(fertigstellenButton);

        VBox.setMargin(hbox, new Insets(30, 0, 0, 0)); // Oben, Rechts, Unten, Links

        grid.addRow(0, textNativeDevelopmentRegion, nativeDevelopmentRegion);
        grid.addRow(1, textBundleIdentifier, bundleIdentifier);
        grid.addRow(2, textBundleVersion, bundleVersion);
        grid.addRow(3, textBundleShortVersion, bundleVersionShortString);
        grid.addRow(4, textHumanReadableCopyright, humanReadableCopyright);
        grid.addRow(5, textApplicationCategoryType, applicationCategoryType);

        vbox.getChildren().addAll(grid, hbox); // Die HBox wird in die VBox verschoben

        // Erstellen einer Scene und Anzeigen des Hauptfensters
        Scene scene = new Scene(vbox, 700, 400);
        stage.setScene(scene);
    }

    private void openNewApplication() {
        String directoryPath = appFolder.getAbsolutePath();
        try {
            File directory = new File(directoryPath);

            if (directory.exists() && directory.isDirectory()) {
                // Öffnen Sie den Finder im angegebenen Verzeichnis
                Runtime.getRuntime().exec("open " + directoryPath);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

