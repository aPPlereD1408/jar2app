package menubar;

import menubar.popup.AboutDeveloper;
import menubar.popup.Function;
import menubar.popup.ShortcutDialog;
import menubar.popup.Tutorial;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import javax.swing.KeyStroke;

import static gui.GUI.frame;

public class MenubarMain {
    public static JMenuBar menuBar = new JMenuBar();

    // Public Menüpunkte
    // Bearbeiten
    public static JMenu editMenu = new JMenu("Edit");
    // Bearbeiten
    public static JMenuItem cutItem = new JMenuItem("Cut");
    public static JMenuItem copyItem = new JMenuItem("Copy");
    public static JMenuItem pasteItem = new JMenuItem("Paste");
    public static JMenuItem deleteItem = new JMenuItem("Delete");
    public static JMenuItem selectItem = new JMenuItem("Select All");

    public MenubarMain()  {
        // Menüpunkte
        JMenu fileMenu = new JMenu("File");
        JMenu windowMenu = new JMenu("Window");
        JMenu helpMenu = new JMenu("Help");

        // Unterpunkte
        // Ablage
        JMenuItem exitItem = new JMenuItem("Close");

        // Fenster
        JMenuItem minimizeItem = new JMenuItem("Minimize");
        JMenuItem zoomItem = new JMenuItem("Zoom");

        // Hilfe
        JMenuItem tutorialItem = new JMenuItem("How do I use the converter?");
        JMenuItem functionItem = new JMenuItem("How does the converter work?");
        JMenuItem shortcutItem = new JMenuItem("Shortcuts");
        JMenuItem aboutDeveloperItem = new JMenuItem("About the developer");


        // Buttonfunktionen
        // Ablage
        exitItem.addActionListener(e -> System.exit(0));

        // Fenster
        minimizeItem.addActionListener(e -> frame.setState(Frame.ICONIFIED));
        zoomItem.addActionListener(e -> {
            int extendedState = frame.getExtendedState();
            if (extendedState == JFrame.MAXIMIZED_BOTH) {
                frame.setExtendedState(Frame.NORMAL);
            } else {
                frame.setExtendedState(Frame.MAXIMIZED_BOTH);
            }
        });

        // Hilfe
        tutorialItem.addActionListener(e -> new Tutorial());
        functionItem.addActionListener(e -> new Function());
        aboutDeveloperItem.addActionListener(e -> new AboutDeveloper());
        shortcutItem.addActionListener(e -> new ShortcutDialog());


        // Tastenkombinationen
        exitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W, Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()));

        cutItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()));
        pasteItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()));
        copyItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()));
        selectItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()));

        minimizeItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M, Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()));

        tutorialItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_T, Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()));
        functionItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()));

        // Setup
        fileMenu.add(exitItem);
        fileMenu.addSeparator();

        editMenu.add(cutItem);
        editMenu.add(copyItem);
        editMenu.add(pasteItem);
        editMenu.addSeparator();
        editMenu.add(deleteItem);
        editMenu.add(selectItem);

        windowMenu.add(minimizeItem);
        windowMenu.add(zoomItem);

        helpMenu.add(tutorialItem);
        helpMenu.add(functionItem);
        helpMenu.addSeparator();
        helpMenu.add(shortcutItem);
        helpMenu.addSeparator();
        helpMenu.add(aboutDeveloperItem);

        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        menuBar.add(windowMenu);
        menuBar.add(helpMenu);

        frame.setJMenuBar(menuBar);
    }
}
