package menubar.popup;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class ShortcutDialog {
    public ShortcutDialog() {
        // Erstellen Sie ein neues JFrame
        JFrame frameShortcut = new JFrame("Shortcuts");
        frameShortcut.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frameShortcut.setSize(400, 300);

        // Erstellen Sie eine DefaultTableModel mit 2 Spalten und 6 Zeilen
        DefaultTableModel tableModel = new DefaultTableModel(12, 2);
        tableModel.setColumnIdentifiers(new Object[]{"Function", "Shortcut"});
        JTable table = new JTable(tableModel);

        // Inhalte
        tableModel.setValueAt("Settings", 0, 0);
        tableModel.setValueAt("command + ,", 0, 1);

        tableModel.setValueAt("Quit", 1, 0);
        tableModel.setValueAt("command + q", 1, 1);

        tableModel.setValueAt("Closer", 2, 0);
        tableModel.setValueAt("command + w", 2, 1);

        tableModel.setValueAt("Cut", 3, 0);
        tableModel.setValueAt("command + x", 3, 1);

        tableModel.setValueAt("Copy", 4, 0);
        tableModel.setValueAt("command + v", 4, 1);

        tableModel.setValueAt("Paste", 5, 0);
        tableModel.setValueAt("command + v", 5, 1);

        tableModel.setValueAt("Select All", 6, 0);
        tableModel.setValueAt("command + a", 6, 1);

        tableModel.setValueAt("Minimize", 7, 0);
        tableModel.setValueAt("command + m", 7, 1);

        tableModel.setValueAt("How do I use the converter?", 8, 0);
        tableModel.setValueAt("command + t", 8, 1);

        tableModel.setValueAt("How does the converter work?", 9, 0);
        tableModel.setValueAt("command + f", 9, 1);

        // Fügen Sie die Tabelle in ein JScrollPane ein, um das Scrollen zu ermöglichen
        JScrollPane scrollPane = new JScrollPane(table);
        // Fügen Sie das JScrollPane zum Hauptfenster hinzu
        frameShortcut.add(scrollPane);

        // Setzen Sie das JFrame auf sichtbar
        frameShortcut.setVisible(true);
    }
}
