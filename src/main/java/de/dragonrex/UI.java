package de.dragonrex;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;

public class UI {
    public static void build(String title, Dimension size, LookAndFeel theme, UIComponent root) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(theme);
            } catch (Exception e) {
                System.out.println("FlatLaf not found, using default look and feel");
            }

            JFrame frame = new JFrame(title);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(size);

            JPanel mainPanel = new JPanel(new MigLayout("insets 0, fill"));
            mainPanel.add(root.render(), "grow");
            frame.setContentPane(mainPanel);

            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}

