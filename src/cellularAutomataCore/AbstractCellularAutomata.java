package cellularAutomataCore;

import renderer.RendererGUI;

import java.awt.*;

public abstract class AbstractCellularAutomata {
    private final int WIDTH;
    private final int HEIGHT;

    protected volatile boolean isGameRunning;

    private int[][] matrix;

    protected final Color[] palette;

    private final RendererGUI rendererGUI;

    protected AbstractCellularAutomata(int width, int height, Color[] palette) {
        this.palette = palette;

        WIDTH = width;
        HEIGHT = height;

        matrix =  new int[WIDTH][HEIGHT];
        rendererGUI = new RendererGUI(this);
        rendererGUI.init(width, height);

        isGameRunning = true;
    }

    public void run() {
        visualize();

        while(true) {
            if(isGameRunning) {
                isGameRunning = !needToStop();
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

    public void visualize() {
        rendererGUI.visualize(matrix, palette);
    }

    protected int getValueFromMatrix(int x, int y) {
        try {
            return matrix[x][y];
        } catch (Exception e) {
            return 0;
        }
    }

    protected boolean needToStop() {
        return false;
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
