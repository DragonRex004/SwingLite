package de.dragonrex.components;

import de.dragonrex.UIComponent;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.function.Consumer;

public class TextField extends UIComponent {
    private String initialText = "";
    private String placeholder = "";
    private String toolTipText = "";
    private int columns = 1;
    private Color placeholderColor = Color.GRAY;
    private Font font = new Font("SansSerif", Font.PLAIN, 11);
    private boolean enabled = true;
    private boolean editable = true;

    private Runnable onEnter = () -> {};
    private Consumer<String> onTextChange = text ->{};
    private Runnable onFocus = () -> {};
    private Runnable onFocusLost = () -> {};
    private Consumer<KeyEvent> onKeyPress = event -> {};
    private Consumer<KeyEvent> onKeyTyped = event -> {};
    private Consumer<KeyEvent> onKeyRelease = event -> {};


    public static TextField of() {
        return new TextField();
    }

    public TextField toolTip(String text) {
        this.toolTipText = text;
        return this;
    }

    public TextField placeholder(String text) {
        this.placeholder = text;
        return this;
    }

    public TextField enabled(boolean enabled) {
        this.enabled = enabled;
        return this;
    }

    public TextField editable(boolean editable) {
        this.editable = editable;
        return this;
    }

    public TextField placeholderColor(Color color) {
        this.placeholderColor = color;
        return this;
    }

    public TextField font(Font font) {
        this.font = font;
        return this;
    }

    public TextField initialText(String text) {
        this.initialText = text;
        return this;
    }

    public TextField columns(int cols) {
        this.columns = cols;
        return this;
    }

    public TextField onTextChange(Consumer<String> action) {
        this.onTextChange = action;
        return this;
    }

    public TextField onFocus(Runnable action) {
        this.onFocus = action;
        return this;
    }

    public TextField onFocusLost(Runnable action) {
        this.onFocusLost = action;
        return this;
    }

    public TextField onKeyPress(Consumer<KeyEvent> action) {
        this.onKeyPress = action;
        return this;
    }

    public TextField onKeyTyped(Consumer<KeyEvent> action) {
        this.onKeyTyped = action;
        return this;
    }

    public TextField onKeyRelease(Consumer<KeyEvent> action) {
        this.onKeyRelease = action;
        return this;
    }


    @Override
    public JComponent render() {
        JTextField field = new JTextField(initialText, columns) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                if (getText().isEmpty() && !placeholder.isEmpty() && !hasFocus()) {
                    Graphics2D g2 = (Graphics2D) g.create();
                    g2.setColor(placeholderColor);
                    g2.setFont(getFont());
                    FontMetrics fm = g2.getFontMetrics();
                    int padding = getInsets().left;
                    g2.drawString(placeholder, padding,
                            (getHeight() - fm.getHeight()) / 2 + fm.getAscent());
                    g2.dispose();
                }
            }
        };

        field.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) { updateText(); }
            public void insertUpdate(DocumentEvent e) { updateText(); }
            public void removeUpdate(DocumentEvent e) { updateText(); }

            private void updateText() {
                if (onTextChange != null) {
                    onTextChange.accept(field.getText());
                }
            }
        });

        field.addActionListener(e -> {
            if (onEnter != null) {
                onEnter.run();
            }
        });

        field.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (onFocus != null) {
                    onFocus.run();
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (onFocusLost != null) {
                    onFocusLost.run();
                }
            }
        });

        field.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {onKeyTyped.accept(e);}
            @Override
            public void keyPressed(KeyEvent e) {onKeyPress.accept(e);}
            @Override
            public void keyReleased(KeyEvent e) {onKeyRelease.accept(e);}
        });

        field.setFont(font);
        field.setToolTipText(toolTipText);
        applyProperties(field);
        field.setEnabled(enabled);
        field.setEditable(editable);
        return field;
    }
}

