package piano.keyboard.keyboardui;

import java.awt.Color;

public class Colors {
    private final Color[] colors;

    public Colors() {
        colors = new Color[] {
                new java.awt.Color(204, 0, 0),
                new java.awt.Color(255, 65, 0),
                new java.awt.Color(255, 170, 0),
                new java.awt.Color(255, 255, 0),
                new java.awt.Color(102, 255, 51),
                new java.awt.Color(0, 153, 51),
                new java.awt.Color(0, 204, 153),
                new java.awt.Color(0, 153, 204),
                new java.awt.Color(0, 0, 255),
                new java.awt.Color(153, 51, 255),
                new java.awt.Color(153, 51, 153),
                new java.awt.Color(204, 102, 153)
        };
    }

    public Color getColor(int position) {
        return colors[position % colors.length];
    }
}
