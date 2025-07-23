package de.dragonrex.layout;

import de.dragonrex.UIComponent;
import net.miginfocom.swing.MigLayout;
import javax.swing.*;

public class Row extends UIComponent {
    private final UIComponent[] children;

    public static Row of(UIComponent... components) {
        return new Row(components);
    }

    private Row(UIComponent[] components) {
        this.children = components;
    }

    @Override
    public JComponent render() {
        JPanel panel = new JPanel(new MigLayout("insets 0, gap 5"));
        panel.setOpaque(false);

        for (UIComponent child : children) {
            panel.add(child.render(), "");
        }
        
        applyProperties(panel);
        return panel;
    }
}