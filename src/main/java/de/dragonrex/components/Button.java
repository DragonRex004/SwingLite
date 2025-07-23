package de.dragonrex.components;

import de.dragonrex.UIComponent;

import javax.swing.*;
import java.awt.*;

public class Button extends UIComponent {
    private String text;
    private Runnable onClick;
    private int cornerRadius = 0;
    private Paint backgroundPaint = null;
    private Color backgroundColor = null;

    public static Button of(String text) {
        return new Button(text);
    }

    private Button(String text) {
        this.text = text;
    }

    public Button radius(int radius) {
        this.cornerRadius = radius;
        return this;
    }

    public Button gradient(Color startColor, Color endColor) {
        this.backgroundPaint = new GradientPaint(
                0, 0, startColor,
                0, height > 0 ? height : 100, endColor
        );
        return this;
    }

    @Override
    public UIComponent background(Color color) {
        this.backgroundColor = color;
        this.backgroundPaint = null;
        return this;
    }

    public Button onClick(Runnable action) {
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

                if (backgroundPaint != null) {
                    g2.setPaint(backgroundPaint);
                } else if (backgroundColor != null) {
                    g2.setPaint(backgroundColor);
                } else {
                    g2.setPaint(Color.WHITE);
                }
                if (cornerRadius > 0) {
                    g2.fillRoundRect(0, 0, getWidth(), getHeight(),
                            cornerRadius, cornerRadius);
                } else {
                    g2.fillRect(0, 0, getWidth(), getHeight());
                }

                g2.dispose();

                setContentAreaFilled(false);
                super.paintComponent(g);
            }
        };
        if (onClick != null) {
            button.addActionListener(e -> onClick.run());
        }
        applyProperties(button);
        return button;
    }
}
