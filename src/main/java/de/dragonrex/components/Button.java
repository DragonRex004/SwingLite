package de.dragonrex.components;

import de.dragonrex.UIComponent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Button extends UIComponent {
    private String text;
    private int cornerRadius = 0;
    private Paint backgroundPaint = null;
    private Color backgroundColor = null;

    private Runnable onClick = () -> {};
    private Runnable onEnter = () -> {};
    private Runnable onExit = () -> {};

    public static Button of(String text) {
        return new Button(text);
    }

    public Button onEnter(Runnable action) {
        this.onEnter = action;
        return this;
    }

    public Button onExit(Runnable action) {
        this.onExit = action;
        return this;
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
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {onClick.run();}
            @Override
            public void mouseEntered(MouseEvent e) {onEnter.run();}
            @Override
            public void mouseExited(MouseEvent e) {onExit.run();}
        });
        applyProperties(button);
        return button;
    }
}
