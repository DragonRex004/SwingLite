package de.dragonrex.components;

import de.dragonrex.UIComponent;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.function.Consumer;

public class Container extends UIComponent {
    private UIComponent[] childs;
    private int cornerRadius = 0;
    private Paint backgroundPaint = null;
    private Color backgroundColor = null;

    private Runnable onEnter = () -> {};
    private Runnable onExit = () -> {};
    private Runnable onClick = () -> {};
    private Consumer<KeyEvent> onKeyPress = event -> {};
    private Consumer<KeyEvent> onKeyRelease = event -> {};
    private Consumer<KeyEvent> onKeyTyped = event -> {};
    
    public static Container of(UIComponent... child) {
        return new Container(child);
    }
    
    private Container(UIComponent... child) {
        this.childs = child;
    }
    
    public Container radius(int radius) {
        this.cornerRadius = radius;
        return this;
    }
    
    public Container gradient(Color startColor, Color endColor) {
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

    public Container onEnter(Runnable action) {
        this.onEnter = action;
        return this;
    }

    public Container onExit(Runnable action) {
        this.onExit = action;
        return this;
    }

    public Container onClick(Runnable action) {
        this.onClick = action;
        return this;
    }

    public Container onKeyPress(Consumer<KeyEvent> action) {
        this.onKeyPress = action;
        return this;
    }

    public Container onKeyRelease(Consumer<KeyEvent> action) {
        this.onKeyRelease = action;
        return this;
    }

    public Container onKeyTyped(Consumer<KeyEvent> action) {
        this.onKeyTyped = action;
        return this;
    }

    @Override
    public JComponent render() {
        JPanel container = new JPanel(new MigLayout("insets 0, Fill")) {
            {
                setOpaque(false);
            }

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

                super.paintComponent(g);
            }
        };

        container.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {onClick.run();}
            @Override
            public void mouseEntered(MouseEvent e) {onEnter.run();}
            @Override
            public void mouseExited(MouseEvent e) {onExit.run();}
        });
        container.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {onKeyTyped.accept(e);}
            @Override
            public void keyPressed(KeyEvent e) {onKeyPress.accept(e);}
            @Override
            public void keyReleased(KeyEvent e) {onKeyRelease.accept(e);}
        });

        for (UIComponent child : childs) {
            container.add(child.render(), "grow");
        }
        applyProperties(container);
        return container;
    }
}