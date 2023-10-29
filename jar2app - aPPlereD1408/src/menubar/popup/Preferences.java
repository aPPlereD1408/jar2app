package menubar.popup;

import javax.swing.*;

public class Preferences extends JFrame {
    public static void settings() {
        JFrame settingsFrame = new JFrame("Preferences");
        settingsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        settingsFrame.setSize(400, 200);

        JPanel panel = new JPanel();

        panel.add(new JLabel("Available soon!"));

        settingsFrame.add(panel);
        settingsFrame.setVisible(true);
    }
}

