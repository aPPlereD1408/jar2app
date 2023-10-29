package menubar.popup;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import static gui.GUI.bgColor;

public class AboutDeveloper {
    public AboutDeveloper() {
        // Setup
        JFrame frame = new JFrame();
        frame.setTitle(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setSize(350, 350);

        frame.getRootPane().putClientProperty("apple.awt.fullscreenable", true);
        frame.getRootPane().putClientProperty("apple.awt.fullWindowContent", true);
        frame.getRootPane().putClientProperty("apple.awt.transparentTitleBar", true);

        JPanel panel = new JPanel(new BorderLayout());

        // Haupt-Text
        JLabel label = new JLabel();
        String htmlText = "<html><head><style>" +
                "@font-face {" +
                "  font-family: 'Inter';" +
                "  src: url('/Users/gereonkrummling/Documents/Sonstiges/jar2appProgramm/font/Inter-Regular.ttf');" +
                "}" +
                "body { font-family: 'Inter'; font-size: 14px; color: 'white'; background-color: rgb(41, 49, 56); }" +
                "a { color: #2b7fed; text-decoration: underline; }" +
                "</style></head>" +
                "<body style='margin: 30px 20px 0px 20px;'> " + // Erhöhen Sie die obere Marge hier
                "aPPlereD1408, real name Gereon Felix Krümmling, " +
                "is a student of business informatics with " +
                "a focus on cyber security. " +
                "He is also a trainee at CGI Deutschland B.V. & Co. KG. " +
                "<br><br>" +
                "This app was developed " +
                "in Q4 of 2023. And is freely available." +
                "</body></html>";

        label.setText(htmlText);
        label.setBackground(bgColor);
        label.setOpaque(true);

        // Links
        JPanel linksPanel = new JPanel();
        BoxLayout layout = new BoxLayout(linksPanel, BoxLayout.Y_AXIS); // Use BoxLayout with Y_AXIS orientation
        linksPanel.setLayout(layout);

        JLabel linkedIn = new JLabel();
        JLabel gitHub = new JLabel();

        String linkedInText = "<html><head><style>" +
                "@font-face {" +
                "  font-family: 'Inter';" +
                "  src: url('/Users/gereonkrummling/Documents/Sonstiges/jar2appProgramm/font/Inter-Regular.ttf');" +
                "}" +
                "body { font-family: 'Inter'; font-size: 14px; color: 'white'; background-color: rgb(41, 49, 56); }" +
                "</style></head>" +
                "<body style='margin-left: 20px;'> " +
                "LinkedIn: <span style='color: #2b7fed; text-decoration: underline; '>Gereon Felix Krümmling</span>" +
                "</body></html>";

        String gitHubText = "<html><head><style>" +
                "@font-face {" +
                "  font-family: 'Inter';" +
                "  src: url('/Users/gereonkrummling/Documents/Sonstiges/jar2appProgramm/font/Inter-Regular.ttf');" +
                "}" +
                "body { font-family: 'Inter'; font-size: 14px; color: 'white'; background-color: rgb(41, 49, 56); }" +
                "</style></head>" +
                "<body style='margin-left: 20px;'> " +
                "GitHub: <span style='color: #2b7fed; text-decoration: underline;'>aPPlereD1408</span>" +
                "</body></html>";

        linkedIn.setText(linkedInText);
        gitHub.setText(gitHubText);

        linkedIn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        gitHub.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        linksPanel.setBackground(bgColor);
        linksPanel.setOpaque(true);

        // Öffnen den Link in einem Webbrowser, wenn er angeklickt wird
        linkedIn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (Desktop.isDesktopSupported()) {
                    String link = "https://www.linkedin.com/in/gereon-felix-kruemmling/";
                    try {
                        Desktop.getDesktop().browse(new URI(link));
                    } catch (IOException | URISyntaxException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });

        gitHub.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (Desktop.isDesktopSupported()) {
                    String link = "https://github.com/aPPlereD1408";
                    try {
                        Desktop.getDesktop().browse(new URI(link));
                    } catch (IOException | URISyntaxException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });

        // Add some vertical spacing between the labels
        linksPanel.add(Box.createVerticalStrut(10)); // You can adjust the spacing by changing the argument value

        // Add the labels to the panel
        linksPanel.add(linkedIn);
        linksPanel.add(gitHub);

        panel.add(label, BorderLayout.NORTH);
        panel.add(linksPanel, BorderLayout.CENTER);

        frame.add(panel, BorderLayout.CENTER);

        frame.setVisible(true);
    }
}
