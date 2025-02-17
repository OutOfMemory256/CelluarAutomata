package game_of_life;

import cellularRenderer.AbstractCellularElement;
import cellularRenderer.RendererGUI;

import java.awt.*;

public class GameOfLife {
    private final int WIDTH = 100;
    private final int HEIGHT = 100;

    private volatile boolean isGameRunning;

    private int[][] matrix = new int[WIDTH][HEIGHT];
    private RendererGUI rendererGUI;

    public GameOfLife() {
        rendererGUI = new RendererGUI(this);
        matrix[4][4] = 1;
        matrix[5][4] = 1;
        matrix[6][4] = 1;
        rendererGUI.init(WIDTH, HEIGHT);
    }

    public void run() throws InterruptedException {
        isGameRunning = true;
        while(true) {
            if(isGameRunning)
                doMove();
            Thread.sleep(100);
        }
    }

    public void doMove() {
        int[][] matrixCopy = new int[matrix.length][matrix[0].length];
        for(int x = 0; x < matrix.length; x++) {
            for(int y = 0; y < matrix[0].length; y++) {
                matrixCopy[x][y] = matrix[x][y];
            }
        }

        visualize();

        for(int x = 0; x < matrix.length; x++) {
            for(int y = 0; y < matrix[0].length; y++) {

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
        }

        matrix = matrixCopy.clone();
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

    private int getValueFromMatrix(int x, int y) {
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
