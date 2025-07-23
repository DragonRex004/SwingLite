package de.dragonrex;

import com.formdev.flatlaf.FlatLightLaf;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;

public class UI {
    private JFrame frame;
    private String title = "";
    private Dimension size = new Dimension(800, 600);
    private LookAndFeel theme = new FlatLightLaf();
    private UIComponent root;
    private boolean isResizable = true;
    private boolean isAlwaysOnTop = false;
    private Image icon = null;

    public UI(UIComponent root) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(theme);
            } catch (Exception e) {
                System.out.println("FlatLaf not found, using default look and feel");
            }

            frame = new JFrame(title);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(size);
            frame.setResizable(isResizable);
            frame.setAlwaysOnTop(isAlwaysOnTop);
            if (icon != null) {
                frame.setIconImage(icon);
            }

            JPanel mainPanel = new JPanel(new MigLayout("insets 0, fill"));
            mainPanel.add(root.render(), "grow");
            frame.setContentPane(mainPanel);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }

    public static UI build(UIComponent root) {
        return new UI(root);
    }

    public UI title(String title) {
        this.title = title;
        return this;
    }

    public UI size(Dimension size) {
        this.size = size;
        return this;
    }

    public UI theme(LookAndFeel theme) {
        this.theme = theme;
        return this;
    }

    public UI icon(String path) {
        this.icon = new ImageIcon(path).getImage();
        return this;
    }

    public UI resizable(boolean isResizable) {
        this.isResizable = isResizable;
        return this;
    }
}

