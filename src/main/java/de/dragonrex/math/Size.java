package de.dragonrex.math;

import java.awt.*;

public class Size extends Dimension {
    public Size(int width, int height) {
        super(width, height);
    }

    public static Size of(int width, int height) {
        return new Size(width, height);
    }
}
