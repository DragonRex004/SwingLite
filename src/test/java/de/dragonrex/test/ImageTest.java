package de.dragonrex.test;

import com.formdev.flatlaf.FlatLightLaf;
import de.dragonrex.UI;
import de.dragonrex.components.Image;
import de.dragonrex.math.Size;

public class ImageTest {
    public ImageTest() {
        UI.build(
                Image.of("assets/MacroLang.png")
                        .scale(100, 100)
                ).title("Image Test")
                .size(Size.of(800, 600))
                .theme(new FlatLightLaf())
                .resizable(false)
                .icon("assets/MacroLang.png");
    }
}
