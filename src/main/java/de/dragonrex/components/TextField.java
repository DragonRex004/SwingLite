package de.dragonrex.components;

import de.dragonrex.UIComponent;

import javax.swing.*;

public class TextField extends UIComponent {
    private String initialText = "";
    private int columns = 15;

    public static TextField of() {
        return new TextField();
    }

    public TextField initialText(String text) {
        this.initialText = text;
        return this;
    }

    public TextField columns(int cols) {
        this.columns = cols;
        return this;
    }

    @Override
    public JComponent render() {
        JTextField field = new JTextField(initialText, columns);
        applyProperties(field);
        return field;
    }
}

