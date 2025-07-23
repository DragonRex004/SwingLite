package de.dragonrex;

import de.dragonrex.math.Size;

import javax.swing.*;
import java.awt.*;

public abstract class UIComponent {
    protected int width = -1;
    protected int height = -1;
    protected int x = 0;
    protected int y = 0;
    protected boolean visible = true;
    protected Color backgroundColor;
    protected Color foregroundColor;
    protected Dimension minimumSize;
    protected Dimension maximumSize;
    protected Dimension preferredSize;
    protected int marginTop = 0;
    protected int marginRight = 0;
    protected int marginBottom = 0;
    protected int marginLeft = 0;

    public abstract JComponent render();

    public UIComponent size(int width, int height) {
        this.width = width;
        this.height = height;
        return this;
    }

    public UIComponent position(int x, int y) {
        this.x = x;
        this.y = y;
        return this;
    }

    public UIComponent visible(boolean visible) {
        this.visible = visible;
        return this;
    }

    public UIComponent backgroundColor(Color color) {
        this.backgroundColor = color;
        return this;
    }

    public UIComponent foregroundColor(Color color) {
        this.foregroundColor = color;
        return this;
    }

    public UIComponent minSize(int width, int height) {
        this.minimumSize = new Dimension(width, height);
        return this;
    }

    public UIComponent maxSize(int width, int height) {
        this.maximumSize = new Dimension(width, height);
        return this;
    }

    public UIComponent preferredSize(int width, int height) {
        this.preferredSize = new Dimension(width, height);
        return this;
    }

    public UIComponent margin(int margin) {
        return margin(margin, margin, margin, margin);
    }

    public UIComponent margin(int top, int right, int bottom, int left) {
        this.marginTop = top;
        this.marginRight = right;
        this.marginBottom = bottom;
        this.marginLeft = left;
        return this;
    }

    protected void applyProperties(JComponent component) {
        if (width > 0 && height > 0) {
            Size size = Size.of(width, height);
            component.setPreferredSize(size);
            component.setMaximumSize(size);
            component.setMinimumSize(size);
            component.setSize(width, height);
        }
        
        component.setLocation(x, y);
        component.setVisible(visible);
        
        if (backgroundColor != null) {
            component.setOpaque(true);
            component.setBackground(backgroundColor);
        }
        
        if (foregroundColor != null) {
            component.setForeground(foregroundColor);
        }
        
        if (minimumSize != null) {
            component.setMinimumSize(minimumSize);
        }
        
        if (maximumSize != null) {
            component.setMaximumSize(maximumSize);
        }
        
        if (preferredSize != null) {
            component.setPreferredSize(preferredSize);
        }

        // Margin über Border
        if (marginTop != 0 || marginRight != 0 || marginBottom != 0 || marginLeft != 0) {
            component.setBorder(BorderFactory.createEmptyBorder(
                marginTop, marginLeft, marginBottom, marginRight
            ));
        }
    }
}