package cellularRenderer;

import java.awt.*;

public abstract class AbstractCellularElement {
    private final Color color;

    protected AbstractCellularElement(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }
}
