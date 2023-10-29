package gui;

import backend.CopyElements;
import backend.executable.MakeLauncherExecutable;
import backend.executable.ModifyLauncherFile;
import backend.gui.InstanceCounter;
import backend.plist.CreateInfoPlistXML;
import menubar.MenubarMain;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.concurrent.atomic.AtomicBoolean;

import static backend.gui.Animationen.removeAnimationLeft;
import static javax.swing.JFileChooser.DIRECTORIES_ONLY;
import static menubar.MenubarMain.*;

public class GUI {
    // Setup
    public static JFrame frame = new JFrame("jar2app by aPPlereD1408");

    public Taskbar dock = Taskbar.getTaskbar();
    public Image dockImage = Toolkit.getDefaultToolkit().getImage("/Users/gereonkrummling/Documents/Sonstiges/jar2app code/jar2app - aPPlereD1408/asset/DockIcon.png");

    public static Color bgColor = new Color(41, 49, 56);

    public static JFileChooser chooseBuildPath = new JFileChooser();
    public static JFileChooser chooseJarFile = new JFileChooser();
    public static JFileChooser chooseIcnsFile = new JFileChooser();
    public static JFileChooser chooseLibFolder = new JFileChooser();

    static JButton createAppButton;

    public static JTextField inputAppName = new JTextField();
    public static File appFolder;

    public static Path macosPath;
    public static Path resourcesPath;

    public static AtomicBoolean isLibFolderChosen = new AtomicBoolean(false);

    public static JButton fertigstellenButton = new JButton("Finish your application!");

    // Info.plist Properties
    public static JTextField nativeDevelopmentRegion = new JTextField(15); // CFBundleDevelopmentRegion
    public static JTextField bundleIdentifier = new JTextField(15); // CFBundleIdentifier
    public static JTextField bundleVersion = new JTextField(15); // CFBundleVersion
    public static JTextField bundleVersionShortString = new JTextField(15); // CFBundleShortVersionString
    public static JComboBox applicationCategoryType; // LSApplicationCategoryType
    public static JTextField humanReadableCopyright = new JTextField(15); // NSHumanReadableCopyright

    // Schriftarten
    // Dateien
    public File interRegularFile = new File("/Users/gereonkrummling/Documents/Sonstiges/jar2app code/jar2app - aPPlereD1408/font/Inter-Regular.ttf");
    public File interMediumFile = new File("/Users/gereonkrummling/Documents/Sonstiges/jar2app code/jar2app - aPPlereD1408/font/Inter-Medium.ttf");
    public File interSemiBoldFile = new File("/Users/gereonkrummling/Documents/Sonstiges/jar2app code/jar2app - aPPlereD1408/font/Inter-SemiBold.ttf");
    public File interBoldFile = new File("/Users/gereonkrummling/Documents/Sonstiges/jar2app code/jar2app - aPPlereD1408/font/Inter-Bold.ttf");
    // Schriftarten
    public Font interTitel = Font.createFont(Font.TRUETYPE_FONT, interBoldFile).deriveFont(35f);
    public Font interSubTitel = Font.createFont(Font.TRUETYPE_FONT, interSemiBoldFile).deriveFont(18f);

    public Font interSectionTitle = Font.createFont(Font.TRUETYPE_FONT, interMediumFile).deriveFont(25f);
    public Font interButtonTitle = Font.createFont(Font.TRUETYPE_FONT, interRegularFile).deriveFont(15f);
    public Font interInput = Font.createFont(Font.TRUETYPE_FONT, interRegularFile).deriveFont(16f);
    public Font interReq = Font.createFont(Font.TRUETYPE_FONT, interMediumFile).deriveFont(12f);

    // Start-GUI
    public GUI() throws IOException, FontFormatException {
        // Setup
        dock.setIconImage(dockImage);

        frame.getRootPane().putClientProperty("apple.awt.windowTitleVisible", false);
        frame.getContentPane().setEnabled(false);

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                InstanceCounter.incrementInstanceCount();
            }

            @Override
            public void windowClosed(WindowEvent e) {
                InstanceCounter.decrementInstanceCount();
            }
        });

        // Setzen des Default-Close-Verhaltens basierend auf der Anzahl der laufenden Instanzen
        if (InstanceCounter.getInstanceCount() == 1) {
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        } else {
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }
        frame.setSize(700, 400);
        frame.setLocationRelativeTo(null);

        frame.getRootPane().putClientProperty("apple.awt.fullscreenable", true);
        frame.getRootPane().putClientProperty("apple.awt.fullWindowContent", true);
        frame.getRootPane().putClientProperty("apple.awt.transparentTitleBar", true);

        new MenubarMain(); // Menüleiste
        editMenu.setEnabled(false);

        // Startfenster
        startWindow();

        frame.setVisible(true);
    }

    private void startWindow() {
        // Setup
        chooseBuildPath.setDialogTitle("Choose your build path");

        String textCreateAppButton = "<html><head><style>" +
                "@font-face {" +
                "  font-family: 'Inter-Regular';" +
                "  src: url('/Users/gereonkrummling/Documents/Sonstiges/jar2appProgramm/font/Inter-Regular.ttf');" +
                "}" +
                ".inter-regular { font-family: 'Inter-Regular'; font-size: 10px; line-height: 14px; }" +
                ".custom-div { text-align: center; display: table-cell; vertical-align: middle; height: 100%; padding: 3px; }" +
                "</style></head>" +
                "<body>" +
                "<div class='custom-div'>" +
                "<span class: 'inter-regular' style='font-size: 18px;'>START</span><br>" +
                "<span class: 'inter-regular'>Choose the path for your app</span>" +
                "</div></body></html>";

        createAppButton = new JButton(textCreateAppButton);
        createAppButton.setEnabled(true);

        createAppButton.addActionListener(e -> {
            chooseBuildPath.setFileSelectionMode(DIRECTORIES_ONLY);
            int result = chooseBuildPath.showOpenDialog(frame);

            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFolder = chooseBuildPath.getSelectedFile();
                screenSelectDestinationFolder(selectedFolder);
            }
        });

        JLabel title = new JLabel("jar2app by aPPlereD1408");
        title.setFont(interTitel);
        title.setForeground(Color.white);
        JLabel subTitle = new JLabel("Create a macOS-Application from your .jar file");
        subTitle.setFont(interSubTitel);
        subTitle.setForeground(Color.white);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        panel.setBackground(bgColor);

        // Titel
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(title, gbc);
        // Untertitel
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(subTitle, gbc);

        // Button
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.insets = new Insets(100, 0, 20, 0); // Abstände
        panel.add(createAppButton, gbc);

        frame.getContentPane().add(panel);
    }

    private void screenSelectDestinationFolder(File selectedFolder) {
        frame.getContentPane().removeAll();
        editMenu.setEnabled(true);
        // Bearbeiten
        cutItem.addActionListener(e -> inputAppName.cut());
        copyItem.addActionListener(e -> inputAppName.copy());
        pasteItem.addActionListener(e -> inputAppName.paste());
        deleteItem.addActionListener(e -> inputAppName.setText(""));
        selectItem.addActionListener(e -> inputAppName.selectAll());

        createAppButton.setText(selectedFolder.getAbsolutePath());
        createAppButton.setFont(interButtonTitle);

        JLabel textAppName = new JLabel("What should the app be called?");
        textAppName.setFont(interSectionTitle);

        inputAppName.setFont(interInput);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(bgColor);
        GridBagConstraints gbc = new GridBagConstraints();

        // Zielordner
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(0, 0, -20, 0); // Abstände
        panel.add(createAppButton, gbc);
        createAppButton.setForeground(bgColor);

        // Sektionstitel
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(50, 0, 0, 0); // Abstände
        gbc.fill = GridBagConstraints.NONE;
        panel.add(textAppName, gbc);
        textAppName.setForeground(Color.WHITE);

        // Input
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(10, 0, 0, 0); // Abstände
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(inputAppName, gbc);

        // Button
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(10, 0, 80, 0); // Abstände
        gbc.fill = GridBagConstraints.NONE;
        JButton weiterButton = new JButton("Next: select your files");
        weiterButton.setFont(interButtonTitle);
        weiterButton.setForeground(Color.BLACK);

        // Deaktivieren Sie den Button zu Beginn
        weiterButton.setEnabled(false);

        // Erstellen Sie ein DocumentListener, um Änderungen im Textfeld zu überwachen
        DocumentListener documentListener = new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                checkInputAndEnableButton();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                checkInputAndEnableButton();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                checkInputAndEnableButton();
            }

            // Überprüfen Sie den Text und aktivieren/deaktivieren Sie den Button
            private void checkInputAndEnableButton() {
                String folderName = inputAppName.getText();
                boolean isEnabled = !folderName.isEmpty(); // Button aktivieren, wenn der Text nicht leer ist
                weiterButton.setEnabled(isEnabled);
            }
        };

        // Fügen Sie den DocumentListener dem Textfeld hinzu
        inputAppName.getDocument().addDocumentListener(documentListener);

        weiterButton.addActionListener(e -> {
            String folderName = inputAppName.getText();

            String appFolderPath = selectedFolder.getAbsolutePath() + File.separator + folderName + ".app";
            appFolder = new File(appFolderPath);
            appFolder.mkdir(); // .app-Ordner wird erstellt

            // contents, MacOS und Resources Ordner werden erstellt
            Path contentsPath = Paths.get(appFolderPath, "contents");
            contentsPath.toFile().mkdir();

            macosPath = Paths.get(contentsPath.toString(), "MacOS");
            macosPath.toFile().mkdir();

            resourcesPath = Paths.get(contentsPath.toString(), "Resources");
            resourcesPath.toFile().mkdir();

            removeAnimationLeft(panel);

            screenUploadJarAndIcns(macosPath, resourcesPath);
        });

        panel.add(weiterButton, gbc);

        frame.getContentPane().add(panel);

        frame.revalidate();
        frame.repaint();
    }

    private void screenUploadJarAndIcns(Path macosPath, Path resourcesPath) {
        editMenu.setEnabled(false);

        chooseJarFile = new JFileChooser();
        chooseIcnsFile = new JFileChooser();

        JButton chooseJarButton = new JButton("Select .jar file");
        JButton chooseIcnsButton = new JButton("Select .icns file");
        JButton chooseLibButton = new JButton("Select lib folder");

        JLabel requirements = new JLabel(".jar and .icns files are required!");
        requirements.setForeground(Color.RED);
        requirements.setFont(interReq);

        JButton weiterButton = new JButton("Next: build informations");
        weiterButton.setEnabled(false);

        AtomicBoolean isIcnsFileChosen = new AtomicBoolean(false);
        AtomicBoolean isJarFileChosen = new AtomicBoolean(false);

        chooseJarButton.setFont(interButtonTitle);
        chooseIcnsButton.setFont(interButtonTitle);
        chooseLibButton.setFont(interButtonTitle);
        weiterButton.setFont(interButtonTitle);

        chooseJarButton.setPreferredSize(new Dimension(500, 50));
        FileNameExtensionFilter jarFilter = new FileNameExtensionFilter("jar files", "jar");
        chooseJarFile.setFileFilter(jarFilter);
        chooseJarButton.addActionListener(e -> {
            int jarFileResult = chooseJarFile.showOpenDialog(null);

            if (jarFileResult == JFileChooser.APPROVE_OPTION) {
                File jarFile = chooseJarFile.getSelectedFile();
                chooseJarButton.setText(jarFile.getName());

                isJarFileChosen.set(true);

                // Überprüfen, ob beide Dateien ausgewählt sind und den "Weiter"-Button aktivieren
                if (isJarFileChosen.get() && isIcnsFileChosen.get()) {
                    weiterButton.setEnabled(true);
                }
            }
        });

        chooseIcnsButton.setPreferredSize(new Dimension(500, 50));
        FileNameExtensionFilter icnsFilter = new FileNameExtensionFilter("Apple Icon Image files", "icns");
        chooseIcnsFile.setFileFilter(icnsFilter);
        chooseIcnsButton.addActionListener(e -> {
            int icnsFileResult = chooseIcnsFile.showOpenDialog(null);

            if (icnsFileResult == JFileChooser.APPROVE_OPTION) {
                File icnsFile = chooseIcnsFile.getSelectedFile();
                chooseIcnsButton.setText(icnsFile.getName());

                isIcnsFileChosen.set(true);

                // Überprüfen, ob beide Dateien ausgewählt sind und den "Weiter"-Button aktivieren
                if (isJarFileChosen.get() && isIcnsFileChosen.get()) {
                    weiterButton.setEnabled(true);
                }
            }
        });

        chooseLibButton.setPreferredSize(new Dimension(500, 50));
        chooseLibFolder.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooseLibButton.addActionListener(e -> {
            int libFolderResult = chooseLibFolder.showOpenDialog(null);

            if (libFolderResult == JFileChooser.APPROVE_OPTION) {
                File libFolder = chooseLibFolder.getSelectedFile();
                chooseLibButton.setText(libFolder.getName());
            }
        });

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(bgColor);
        GridBagConstraints gbc = new GridBagConstraints();

        // Button zum Auswählen der .jar-Datei
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(chooseJarButton, gbc);

        // Button zum Auswählen der .incs-Datei
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.insets = new Insets(10, 0, 0, 0); // Abstände
        panel.add(chooseIcnsButton, gbc);

        // Button zum Auswählen des lib-Ordners
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.insets = new Insets(10, 0, 0, 0); // Abstände
        panel.add(chooseLibButton, gbc);

        // Requirements
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(50, 0, 0, 0); // Abstände
        panel.add(requirements, gbc);

        // Weiter-Button
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(0, 0, 20, 0); // Abstände
        gbc.fill = GridBagConstraints.NONE;

        weiterButton.addActionListener(e -> {
            String jarPath = chooseJarFile.getSelectedFile() != null ? chooseJarFile.getSelectedFile().getAbsolutePath() : null;
            String icnsPath = chooseIcnsFile.getSelectedFile() != null ? chooseIcnsFile.getSelectedFile().getAbsolutePath() : null;
            String libPath = chooseLibFolder.getSelectedFile() != null ? chooseLibFolder.getSelectedFile().getAbsolutePath() : null;

            CopyElements.copyNeededFiles(macosPath, resourcesPath, jarPath, icnsPath, libPath);
            removeAnimationLeft(panel);

            editMenu.setEnabled(true);
            screenInfoPlist();
        });
        panel.add(weiterButton, gbc);

        frame.add(panel);

        frame.revalidate();
        frame.repaint();
    }

    private void screenInfoPlist() {
        editMenu.setEnabled(true);
        // Bearbeiten
        Component focusedComponent = KeyboardFocusManager.getCurrentKeyboardFocusManager().getFocusOwner();
        cutItem.addActionListener(e -> {
            if (focusedComponent == nativeDevelopmentRegion) {
                nativeDevelopmentRegion.cut();
            } else if (focusedComponent == bundleIdentifier) {
                bundleIdentifier.cut();
            } else if (focusedComponent == bundleVersion) {
                bundleVersion.cut();
            } else if (focusedComponent == bundleVersionShortString) {
                bundleVersionShortString.cut();
            } else if (focusedComponent == humanReadableCopyright) {
                humanReadableCopyright.cut();
            }
        });
        copyItem.addActionListener(e -> {
            if (focusedComponent == nativeDevelopmentRegion) {
                nativeDevelopmentRegion.copy();
            } else if (focusedComponent == bundleIdentifier) {
                bundleIdentifier.copy();
            } else if (focusedComponent == bundleVersion) {
                bundleVersion.copy();
            } else if (focusedComponent == bundleVersionShortString) {
                bundleVersionShortString.copy();
            } else if (focusedComponent == humanReadableCopyright) {
                humanReadableCopyright.copy();
            }
        });
        pasteItem.addActionListener(e -> {
            if (focusedComponent == nativeDevelopmentRegion) {
                nativeDevelopmentRegion.paste();
            } else if (focusedComponent == bundleIdentifier) {
                bundleIdentifier.paste();
            } else if (focusedComponent == bundleVersion) {
                bundleVersion.paste();
            } else if (focusedComponent == bundleVersionShortString) {
                bundleVersionShortString.paste();
            } else if (focusedComponent == humanReadableCopyright) {
                humanReadableCopyright.paste();
            }
        });
        deleteItem.addActionListener(e -> {
            if (focusedComponent == nativeDevelopmentRegion) {
                nativeDevelopmentRegion.setText("");
            } else if (focusedComponent == bundleIdentifier) {
                bundleIdentifier.setText("");
            } else if (focusedComponent == bundleVersion) {
                bundleVersion.setText("");
            } else if (focusedComponent == bundleVersionShortString) {
                bundleVersionShortString.setText("");
            } else if (focusedComponent == humanReadableCopyright) {
                humanReadableCopyright.setText("");
            }
        });
        selectItem.addActionListener(e -> {
            if (focusedComponent == nativeDevelopmentRegion) {
                nativeDevelopmentRegion.selectAll();
            } else if (focusedComponent == bundleIdentifier) {
                bundleIdentifier.selectAll();
            } else if (focusedComponent == bundleVersion) {
                bundleVersion.selectAll();
            } else if (focusedComponent == bundleVersionShortString) {
                bundleVersionShortString.selectAll();
            } else if (focusedComponent == humanReadableCopyright) {
                humanReadableCopyright.selectAll();
            }
        });

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(bgColor);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Innenabstand

        // Zeile 1: Native Development Region
        JLabel textNativeDevelopementRegion = new JLabel("Native Development Region:");
        textNativeDevelopementRegion.setFont(interInput);
        textNativeDevelopementRegion.setForeground(Color.white);
        textNativeDevelopementRegion.setToolTipText("Default language or region (en, de, fr)");
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(textNativeDevelopementRegion, gbc);
        gbc.gridx = 1;
        panel.add(nativeDevelopmentRegion, gbc);
        nativeDevelopmentRegion.setFont(interInput);

        // Zeile 2: Bundle Identifier
        JLabel textBundleIdentifier = new JLabel("Bundle Identifier:");
        textBundleIdentifier.setFont(interInput);
        textBundleIdentifier.setForeground(Color.white);
        textBundleIdentifier.setToolTipText("Unique identifier for the application. Formatted in DNS style (\"com.example.myapp\")");
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(textBundleIdentifier, gbc);
        gbc.gridx = 1;
        panel.add(bundleIdentifier, gbc);
        bundleIdentifier.setFont(interInput);

        // Zeile 3: Bundle Version
        JLabel textBundleVersion = new JLabel("Bundle Version:");
        textBundleVersion.setFont(interInput);
        textBundleVersion.setForeground(Color.white);
        textBundleVersion.setToolTipText("Set version number of the application (\"10.14.5\")");
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(textBundleVersion, gbc);
        gbc.gridx = 1;
        panel.add(bundleVersion, gbc);
        bundleVersion.setFont(interInput);

        // Zeile 4: Bundle Version Short
        JLabel textBundleShortVersion = new JLabel("Bundle Short Version:");
        textBundleShortVersion.setFont(interInput);
        textBundleShortVersion.setForeground(Color.white);
        textBundleShortVersion.setToolTipText("Identification of the version to facilitate (\"Update 2023.2\")");
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(textBundleShortVersion, gbc);
        gbc.gridx = 1;
        panel.add(bundleVersionShortString, gbc);
        bundleVersionShortString.setFont(interInput);

        // Zeile 5: Copyright
        JLabel textHumanReadableCopyright = new JLabel("Copyright:");
        textHumanReadableCopyright.setFont(interInput);
        textHumanReadableCopyright.setForeground(Color.white);
        textHumanReadableCopyright.setToolTipText("Copyright of the creator (\"name of the company, developer\")");
        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(textHumanReadableCopyright, gbc);
        gbc.gridx = 1;
        panel.add(humanReadableCopyright, gbc);
        humanReadableCopyright.setFont(interInput);

        // Zeile 6: Application Category Type
        JLabel textApplicationCategoryType = new JLabel("Application Category Type:");
        String[] optionsApplicationCategoryType = {
                "Business", "Developer Tools", "Education", "Entertainment", "Finance", "Games", "Action Games",
                "Adventure Games", "Arcade Games", "Board Games", "Card Games", "Casino Games", "Dice Games",
                "Educational Games", "Family Games", "Kids Games", "Music Games", "Puzzle Games", "Racing Games",
                "Role Playing Games", "Simulation Games", "Sports Games", "Strategy Games", "Trivia Games",
                "Word Games", "Graphics Design", "Healthcare Fitness", "Lifestyle", "Medical", "Music",
                "News", "Photography", "Productivity", "Reference", "Social Networking", "Sports", "Travel",
                "Utilities", "Video", "Weather"
        };
        applicationCategoryType = new JComboBox(optionsApplicationCategoryType);
        textApplicationCategoryType.setFont(interInput);
        textApplicationCategoryType.setForeground(Color.white);
        textApplicationCategoryType.setToolTipText("The category that best describes your app for the App Store.");
        gbc.gridx = 0;
        gbc.gridy = 5;
        panel.add(textApplicationCategoryType, gbc);
        gbc.gridx = 1;
        panel.add(applicationCategoryType, gbc);
        applicationCategoryType.setFont(interInput);

        // Zeile 7: Button
        fertigstellenButton.setToolTipText("Bundle identifier, bundle version and copyright are required");
        fertigstellenButton.setFont(interButtonTitle);
        fertigstellenButton.setEnabled(false);

        fertigstellenButton.addActionListener(e -> {
            String getBundleIdentifier = bundleIdentifier.getText();
            String getBundleVersion = bundleVersion.getText();
            String getHumanReadableCopyright = humanReadableCopyright.getText();

            if (!getBundleIdentifier.isEmpty() && !getBundleVersion.isEmpty() && !getHumanReadableCopyright.isEmpty()) {
                CreateInfoPlistXML.createFile();
                ModifyLauncherFile.modifyFile();
                MakeLauncherExecutable.execute();
                JOptionPane.showMessageDialog(null, "Your application has been built successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                openNewApplication();

                // Deaktiviert den Button nach erfolgreicher Aktion
                fertigstellenButton.setEnabled(false);
            } else {
                // Wenn mindestens eines der Felder leer ist, bleibt der Button deaktiviert
                fertigstellenButton.setEnabled(false);
            }
        });

        DocumentListener textFieldsListener = new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updateButtonState();
            }
            @Override
            public void removeUpdate(DocumentEvent e) {
                updateButtonState();
            }
            @Override
            public void changedUpdate(DocumentEvent e) {
                updateButtonState();
            }
        };

        bundleIdentifier.getDocument().addDocumentListener(textFieldsListener);
        bundleVersion.getDocument().addDocumentListener(textFieldsListener);
        humanReadableCopyright.getDocument().addDocumentListener(textFieldsListener);

        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(50, 0, 20, 0);
        gbc.fill = GridBagConstraints.NONE;

        panel.add(fertigstellenButton, gbc);

        frame.add(panel);

        frame.revalidate();
        frame.repaint();
    }

    private void updateButtonState() {
        String getBundleIdentifier = bundleIdentifier.getText();
        String getBundleVersion = bundleVersion.getText();
        String getHumanReadableCopyright = humanReadableCopyright.getText();

        boolean fieldsAreFilled = !getBundleIdentifier.isEmpty() && !getBundleVersion.isEmpty() && !getHumanReadableCopyright.isEmpty();
        fertigstellenButton.setEnabled(fieldsAreFilled);
    }

    private static void openNewApplication() {
        String directoryPath = appFolder.getPath();

        try {
            File directory = new File(directoryPath);

            if (directory.exists() && directory.isDirectory()) {
                // Öffnen Sie den Finder im angegebenen Verzeichnis
                Runtime.getRuntime().exec("open " + directoryPath);
                System.out.println("App wurde geöffnet"); // Log
            } else {
                System.err.println("Das Verzeichnis existiert nicht oder ist kein Ordner.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
