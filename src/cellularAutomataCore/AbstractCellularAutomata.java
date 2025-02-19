package cellularAutomataCore;

import java.awt.*;

public abstract class AbstractCellularAutomata {
    private final int WIDTH = 100;
    private final int HEIGHT = 100;

    private volatile boolean isGameRunning;

    protected int[][] matrix = new int[WIDTH][HEIGHT];

    protected final Color[] palette;

    protected AbstractCellularAutomata(Color[] palette) {
        this.palette = palette;
    }

    public void run() {
        visualize();

        isGameRunning = true;
        while(true) {
            if(isGameRunning) {
                doMove();
                visualize();
            }
            try {
                //noinspection BusyWait
                Thread.sleep(100);
            } catch (InterruptedException _) {}
        }
    }

    public void doMove() {
        int[][] matrixCopy = cloneMatrix(matrix);

        for(int x = 0; x < matrix.length; x++) {
            for(int y = 0; y < matrix[0].length; y++) {
                try {
                    executeRules(x, y, matrixCopy);
                } catch (Exception _) {}
            }
        }
        matrix = cloneMatrix(matrixCopy);
    }

    protected abstract void executeRules(int x, int y, int[][] matrixCopy);

    protected int[][] cloneMatrix(int[][] matrix) {
        int[][] matrixCopy = new int[matrix.length][matrix[0].length];
        for(int x = 0; x < matrix.length; x++) {
            System.arraycopy(matrix[x], 0, matrixCopy[x], 0, matrix[0].length);
        }
        return matrixCopy;
    }

    public abstract void visualize();

    protected int getValueFromMatrix(int x, int y) {
        try {
            return matrix[x][y];
        } catch (Exception e) {
            return 0;
        }
    }

    public void setValue(int x, int y, int value) {
        matrix[x][y] = value;
    }

    public void stop() {
        isGameRunning = false;
    }

    public void start() {
        isGameRunning = true;
    }
}
