package backend.gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static gui.GUI.frame;

// Animationseffekt zum Entfernen der Elemente
public class Animationen {
    public static void removeAnimationLeft(JPanel panel) {
        Timer timer = new Timer(10, new ActionListener() {
            int x = 0;

            @Override
            public void actionPerformed(ActionEvent e) {
                if (x <= frame.getWidth()) {
                    panel.setLocation(x, 0);
                    panel.repaint();
                    x -= 30;
                } else {
                    ((Timer) e.getSource()).stop();
                    frame.getContentPane().remove(panel);
                    frame.revalidate();
                    frame.repaint();
                }
            }
        });
        timer.start();
    }
}
