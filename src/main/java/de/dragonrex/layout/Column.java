package de.dragonrex.layout;

import de.dragonrex.UIComponent;
import net.miginfocom.swing.MigLayout;
import javax.swing.*;

public class Column extends UIComponent {
    private final UIComponent[] children;

    public static Column of(UIComponent... components) {
        return new Column(components);
    }

    private Column(UIComponent[] components) {
        this.children = components;
    }

    @Override
    public JComponent render() {
        JPanel panel = new JPanel(new MigLayout("insets 0, gap 5, flowy"));
        panel.setOpaque(false);
        
        for (UIComponent child : children) {
            panel.add(child.render(), "");
        }
        
        applyProperties(panel);
        return panel;
    }
}