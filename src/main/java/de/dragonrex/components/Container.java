package de.dragonrex.components;

import de.dragonrex.UIComponent;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;

public class Container extends UIComponent {
    private UIComponent[] childs;
    private int cornerRadius = 0;
    private Paint backgroundPaint = Color.WHITE;
    
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
    public JComponent render() {
        JPanel container = new JPanel(new MigLayout("insets 0, fill")) {
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

        for (UIComponent child : childs) {
            container.add(child.render(), "grow");
        }
        applyProperties(container);
        
        return container;
    }
}