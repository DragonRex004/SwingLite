package de.dragonrex.test;

import com.formdev.flatlaf.FlatLightLaf;
import de.dragonrex.components.MButton;
import de.dragonrex.components.TextField;
import de.dragonrex.layout.Row;
import de.dragonrex.math.Size;
import de.dragonrex.UI;
import de.dragonrex.components.Text;
import de.dragonrex.layout.Column;
import de.dragonrex.style.Padding;

import java.awt.*;

public class Main {
    public static void main(String[] args) {
        GradientPaint gp = new GradientPaint(0, 0, new Color(66, 133, 244),
                100, 40, new Color(52, 168, 83));
        UI.build(
                "",
                Size.of(800, 600),
                new FlatLightLaf(),
                Column.of(
                        Padding.of(10, 50, 10, 10,
                                Text.of("Username")
                                        .fontSize(14).bold()
                                        .size(200, 30)
                        ),
                        Padding.of(0, 10, 10, 10,
                                TextField.of()
                                        .initialText("z.B. Max Mustermann")
                                        .columns(1)
                                        .size(300, 40)
                        ),
                        Row.of(
                                Padding.of(0, 10, 10, 5,
                                        MButton.of("Login")
                                                .onClick(() -> System.out.println("Login clicked"))
                                                .size(100, 40)
                                ),
                                Padding.of(0, 5, 10, 10,
                                        MButton.of("Cancel")
                                                .onClick(() -> System.out.println("Login canceled"))
                                                .size(100, 40)
                                )
                        )
                )
        );
    }
}

