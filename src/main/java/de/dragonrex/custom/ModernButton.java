package de.dragonrex.custom;

import javax.swing.*;
import java.awt.*;

public class ModernButton extends JButton {
    private int radius = 20;
    private Paint paint = Color.WHITE;

    public ModernButton(String text) {
        super(text);
        setContentAreaFilled(false);
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public int getRadius() {
        return radius;
    }

    public void setPaint(Paint paint) {
        this.paint = paint;
    }

    public Paint getPaint() {
        return paint;
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setPaint(this.paint);

        // Rounded Rectangle
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), this.radius, this.radius);

        // Superclass für Text etc.
        super.paintComponent(g2);

        g2.dispose();
    }

    @Override
    public boolean isOpaque() {
        return false;
    }
}

