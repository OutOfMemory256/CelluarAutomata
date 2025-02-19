package gameOfLife;

import cellularAutomataCore.AbstractCellularAutomata;
import renderer.RendererGUI;

import java.awt.*;

public class GameOfLife extends AbstractCellularAutomata {
    private final int WIDTH;
    private final int HEIGHT;

    private final RendererGUI rendererGUI;

    public GameOfLife(int width, int height) {
        super(new Color[]{Color.BLACK, Color.WHITE});
        WIDTH = width;
        HEIGHT = height;
        rendererGUI = new RendererGUI(this);
        rendererGUI.init(WIDTH, HEIGHT);
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
        if (total == 3) {
            matrixCopy[x][y] = 1;
        } else if (total < 2 || total > 3){
            matrixCopy[x][y] = 0;
        }
    }

    public void visualize() {
        rendererGUI.visualize(matrix, palette);
    }
}
