package gameOfLife;

import cellularAutomataCore.AbstractCellularAutomata;
import cellularAutomataCore.AbstractCellularElement;
import renderer.RendererGUI;

import java.awt.*;

public class GameOfLife extends AbstractCellularAutomata {
    private final int WIDTH;
    private final int HEIGHT;

    private final RendererGUI rendererGUI;

    public GameOfLife(int width, int height) {
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
        Color[][] matrixGUI = new Color[WIDTH][HEIGHT];
        for(int x = 0; x < matrix.length; x++) {
            for(int y = 0; y < matrix[0].length; y++) {
                matrixGUI[x][y] = matrix[x][y] == 1 ? Color.WHITE :Color.BLACK;
            }
        }
        rendererGUI.visualize(matrixGUI);
    }

    public static class AliveCell extends AbstractCellularElement {
        private AliveCell() {
            super(Color.WHITE);
        }
    }

    public static class DeadCell extends AbstractCellularElement {
        public DeadCell() {
            super(Color.BLACK);
        }
    }
}
