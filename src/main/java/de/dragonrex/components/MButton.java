package de.dragonrex.components;

import de.dragonrex.UIComponent;

import javax.swing.*;
import java.awt.*;

public class MButton extends UIComponent {
    private String text;
    private Runnable onClick;
    private int cornerRadius = 0;
    private Paint backgroundPaint = Color.WHITE;

    public static MButton of(String text) {
        return new MButton(text);
    }

    private MButton(String text) {
        this.text = text;
    }

    public MButton radius(int radius) {
        this.cornerRadius = radius;
        return this;
    }

    public MButton gradient(Color startColor, Color endColor) {
        this.backgroundPaint = new GradientPaint(
                0, 0, startColor,
                0, height > 0 ? height : 100, endColor
        );
        return this;
    }

    public MButton onClick(Runnable action) {
        this.onClick = action;
        return this;
    }

    @Override
    public JComponent render() {
        JButton button = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON);

                g2.setPaint(backgroundPaint);
                if (cornerRadius > 0) {
                    g2.fillRoundRect(0, 0, getWidth(), getHeight(),
                            cornerRadius, cornerRadius);
                } else {
                    g2.fillRect(0, 0, getWidth(), getHeight());
                }

                g2.dispose();

                if (getBackground() != null) {
                    super.paintComponent(g);
                }
            }

            @Override
            public boolean isOpaque() {
                return false;
            }
        };
        if (onClick != null) {
            button.addActionListener(e -> onClick.run());
        }
        applyProperties(button);
        return button;
    }
}
