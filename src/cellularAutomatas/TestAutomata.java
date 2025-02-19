package cellularAutomatas;

import cellularAutomataCore.AbstractCellularAutomata;
import renderer.RendererGUI;

import java.awt.*;

public class TestAutomata extends AbstractCellularAutomata {
    private final RendererGUI rendererGUI;

    public TestAutomata(int width, int height) {
        super(width, height, new Color[]{Color.BLACK, Color.WHITE});
        rendererGUI = new RendererGUI(this);
        rendererGUI.init(width, height);
    }

    @Override
    protected void executeRules(int x, int y, int[][] matrixCopy) {
        int total =
                getValueFromMatrix(x - 1,y - 1) +
                        getValueFromMatrix(x, y - 1) +
                        getValueFromMatrix(x + 1, y - 1) +
                        getValueFromMatrix(x - 1, y) +
                        getValueFromMatrix(x + 1, y) +
                        getValueFromMatrix(x - 1, y + 1) +
                        getValueFromMatrix(x, y + 1) +
                        getValueFromMatrix(x + 1, y + 1);
        if (total >= 5) {
            matrixCopy[x][y] = 1;
        }
    }

    public void visualize() {
        rendererGUI.visualize(matrix, palette);
    }
}
