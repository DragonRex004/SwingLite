package de.dragonrex.test;

import com.formdev.flatlaf.FlatLightLaf;
import de.dragonrex.components.Button;
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
        UI.build(
                "",
                Size.of(800, 600),
                new FlatLightLaf(),
                Column.of(
                        Padding.of(100, 350, 10, 10,
                                Text.of("Login Form")
                                        .fontSize(14).bold()
                                        .size(200, 30)
                        ),
                        Padding.of(0, 300, 10, 10,
                                TextField.of()
                                        .placeholder("Enter your username")
                                        .columns(1)
                                        .size(200, 40)
                        ),
                        Padding.of(0, 300, 10, 10,
                                TextField.of()
                                        .placeholder("Enter your password")
                                        .columns(1)
                                        .size(200, 40)
                        ),
                        Row.of(
                                Padding.of(0, 295, 10, 5,
                                        Button.of("Login")
                                                .onClick(() -> System.out.println("Login clicked"))
                                                .size(100, 40)
                                                .background(Color.RED)
                                ),
                                Padding.of(0, 5, 10, 10,
                                        Button.of("Cancel")
                                                .onClick(() -> System.out.println("Login canceled"))
                                                .gradient(Color.GREEN, Color.BLUE)
                                                .size(100, 40)
                                )
                        )
                )
        );
    }
}

