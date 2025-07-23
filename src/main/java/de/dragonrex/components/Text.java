package de.dragonrex.components;

import de.dragonrex.UIComponent;

import javax.swing.*;
import java.awt.*;

public class Text extends UIComponent {
    private String text;
    private int fontSize = 16;
    private boolean bold = false;


    public static Text of(String text) {
        return new Text(text);
    }

    private Text(String text) {
        this.text = text;
    }

    public Text fontSize(int size) {
        this.fontSize = size;
        return this;
    }

    public Text bold() {
        this.bold = true;
        return this;
    }

    @Override
    public JComponent render() {
        JLabel label = new JLabel(text);
        label.setFont(new Font("SansSerif", bold ? Font.BOLD : Font.PLAIN, fontSize));
        applyProperties(label);
        return label;
    }
}
