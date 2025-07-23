package de.dragonrex.components;

import de.dragonrex.UIComponent;

import javax.swing.*;

public class Button extends UIComponent {
    private String text;
    private Runnable onClick;

    public static Button of(String text) {
        return new Button(text);
    }

    private Button(String text) {
        this.text = text;
    }

    public Button onClick(Runnable action) {
        this.onClick = action;
        return this;
    }

    @Override
    public JComponent render() {
        JButton button = new JButton(text);
        if (onClick != null) {
            button.addActionListener(e -> onClick.run());
        }
        applyProperties(button);
        return button;
    }
}

