package game_of_life;

import cellularRenderer.AbstractCellularAutomata;
import cellularRenderer.AbstractCellularElement;
import cellularRenderer.RendererGUI;

import java.awt.*;

public class GameOfLife extends AbstractCellularAutomata {
    private final int WIDTH = 100;
    private final int HEIGHT = 100;

    private RendererGUI rendererGUI;

    public GameOfLife() {
        rendererGUI = new RendererGUI(this);
        rendererGUI.init(WIDTH, HEIGHT);
    }

    @Override
    protected void executeRules(int x, int y, int[][] matrixCopy) {
        int total =
                getValueFromMatrix(x - 1,y - 1) + getValueFromMatrix(x, y - 1) + getValueFromMatrix(x + 1, y - 1) +
                        getValueFromMatrix(x - 1, y) +                        getValueFromMatrix(x + 1, y) +
                        getValueFromMatrix(x - 1, y + 1) + getValueFromMatrix(x, y + 1) + getValueFromMatrix(x + 1, y + 1);

        if (total == 3) {
            matrixCopy[x][y] = 1;
        } else if (total < 2 || total > 3){
            matrixCopy[x][y] = 0;
        }
    }

    public void visualize() {
        AbstractCellularElement[][] matrixGUI = new AbstractCellularElement[WIDTH][HEIGHT];
        for(int x = 0; x < matrix.length; x++) {
            for(int y = 0; y < matrix[0].length; y++) {
                matrixGUI[x][y] = matrix[x][y] == 1 ? new AliveCell() : new DeadCell();
            }
        }
        rendererGUI.visualize(matrixGUI);
    }

    public class AliveCell extends AbstractCellularElement {
        private AliveCell() {
            super(Color.WHITE);
        }
    }

    public class DeadCell extends AbstractCellularElement {
        public DeadCell() {
            super(Color.BLACK);
        }
    }
}
