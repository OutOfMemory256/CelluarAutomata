package cellularAutomatas;

import cellularAutomataCore.AbstractCellularAutomata;

import java.awt.*;

public class GameOfLife extends AbstractCellularAutomata {

    public GameOfLife(int width, int height) {
        super(width, height, new Color[]{Color.BLACK, Color.WHITE});
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
}
