package de.dragonrex.style;

import de.dragonrex.UIComponent;

import javax.swing.*;
import java.awt.*;

public class Padding extends UIComponent {
    private final UIComponent child;
    private final Insets insets;

    public static Padding of(int top, int left, int bottom, int right, UIComponent child) {
        return new Padding(new Insets(top, left, bottom, right), child);
    }

    private Padding(Insets insets, UIComponent child) {
        this.insets = insets;
        this.child = child;
    }

    @Override
    public JComponent render() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setOpaque(false);
        panel.setBorder(BorderFactory.createEmptyBorder(
                insets.top, insets.left, insets.bottom, insets.right
        ));
        panel.add(child.render(), BorderLayout.CENTER);
        return panel;
    }
}

