package de.dragonrex.components;

import de.dragonrex.UIComponent;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class Image extends UIComponent {
    private File file;
    private ImageIcon image;

    public static Image of(String path) {
        return new Image(path);
    }

    private Image(String path) {
        this.file = new File(path);
        try {
            this.image = new ImageIcon(ImageIO.read(file));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Image scale(int width, int height) {
        this.image.setImage(this.image.getImage().getScaledInstance(width, height, BufferedImage.SCALE_SMOOTH));
        return this;
    }

    @Override
    public JComponent render() {
        JLabel label = new JLabel(this.image);
        applyProperties(label);
        return label;
    }
}
