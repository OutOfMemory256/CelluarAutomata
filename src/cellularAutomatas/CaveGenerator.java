package cellularAutomatas;

import cellularAutomataCore.AbstractCellularAutomata;

import java.awt.*;

public class CaveGenerator extends AbstractCellularAutomata {
    private int timer = 10;

    public CaveGenerator(int width, int height) {
        super(width, height, new Color[]{Color.BLACK, Color.WHITE});
        isGameRunning = false;
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

    @Override
    protected boolean needToStop() {
        return timer-- > 0;
    }
}
