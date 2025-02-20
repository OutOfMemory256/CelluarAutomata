package cellularAutomatas;

import cellularAutomataCore.AbstractCellularAutomata;

import java.awt.*;
import java.util.Random;

public class Mycelium extends AbstractCellularAutomata {
    private  Random random = new Random();

    public Mycelium(int width, int height) {
        super(width, height, new Color[]{Color.PINK, Color.getHSBColor(126, 100, 58), Color.getHSBColor(126, 100, 58)});
        isGameRunning = true;
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



        if (total >= 8) {
            matrixCopy[x][y] = 1;
        } else if(total >= 6) {
            if(random.nextInt(0, 150) == 1)
                matrixCopy[x][y] = 1;
        } else if(total >= 4) {
            if (random.nextInt(0, 200) == 1)
                matrixCopy[x][y] = 1;
        } else if(total == 3) {
            if (random.nextInt(0, 100) == 1)
                matrixCopy[x][y] = 1;
        } else if(total >= 2) {
            if (random.nextInt(0, 20) == 1)
                matrixCopy[x][y] = 2;
        } else if(total >= 1) {
            if (random.nextInt(0, 50) == 1)
                matrixCopy[x][y] = 2;
        }
    }
}
