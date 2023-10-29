package menubar.popup;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

import static gui.GUI.bgColor;

public class AboutDialog {
    public File interMediumFile = new File("/Users/gereonkrummling/Documents/Sonstiges/jar2app code/jar2app - aPPlereD1408/font/Inter-Medium.ttf");
    public Font interSectionTitle = Font.createFont(Font.TRUETYPE_FONT, interMediumFile).deriveFont(15f);

    public AboutDialog() throws IOException, FontFormatException {
        JDialog aboutDialog = new JDialog();
        aboutDialog.setResizable(false);
        aboutDialog.setSize(400, 250);

        aboutDialog.getRootPane().putClientProperty("apple.awt.fullscreenable", true);
        aboutDialog.getRootPane().putClientProperty("apple.awt.fullWindowContent", true);
        aboutDialog.getRootPane().putClientProperty("apple.awt.transparentTitleBar", true);

        // Position the dialog box in the center of the screen
        Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = new Double((screensize.getWidth() - 350) / 2).intValue();
        int height = new Double((screensize.getHeight() - 150) / 2).intValue(); // Fixed the height calculation
        aboutDialog.setLocation(width, height);

        // Create a panel with GridBagLayout to center the labels vertically
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(bgColor);
        aboutDialog.getContentPane().setBackground(bgColor);

        // Create an ImageIcon and JLabel for the image
        ImageIcon imageIcon = new ImageIcon("/Users/gereonkrummling/Documents/Sonstiges/jar2app code/jar2app - aPPlereD1408/asset/DockIcon_About.png"); // Replace "your_image.png" with your image file path
        Image image = imageIcon.getImage();
        Image scaledImage = image.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(scaledImage);
        JLabel imageLabel = new JLabel(imageIcon);

        // Add a label with the application information
        JLabel appInfo = new JLabel("jar2app by aPPlereD1408 (c) 2023");
        appInfo.setForeground(Color.WHITE);
        appInfo.setFont(interSectionTitle);

        JLabel appVersion = new JLabel("Version: 1.0.0");
        appVersion.setForeground(Color.WHITE);
        appVersion.setFont(interSectionTitle);

        // Create GridBagConstraints for centering
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(5, 5, 5, 5);
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.anchor = GridBagConstraints.CENTER;

        // Add the image label to the panel
        panel.add(imageLabel, constraints);

        // Update constraints for the following labels
        constraints.gridy = 1;
        panel.add(appInfo, constraints);
        constraints.gridy = 2;
        panel.add(appVersion, constraints);

        // Add the panel to the dialog
        aboutDialog.getContentPane().add(panel);

        aboutDialog.setVisible(true);
    }
}
