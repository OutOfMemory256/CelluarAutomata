package cellularRenderer;

public abstract class AbstractCellularAutomata {
    private final int WIDTH = 100;
    private final int HEIGHT = 100;

    private volatile boolean isGameRunning;

    protected int[][] matrix = new int[WIDTH][HEIGHT];

    public AbstractCellularAutomata() {
        matrix[51][50] = 1;
        matrix[52][50] = 1;
        matrix[53][50] = 1;
    }

    public void run() {
        isGameRunning = true;
        visualize();
        while(true) {
            if(isGameRunning) {
                doMove();
                visualize();
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void doMove() {
        int[][] matrixCopy = cloneMatrix(matrix);

        for(int x = 0; x < matrix.length; x++) {
            for(int y = 0; y < matrix[0].length; y++) {
                try {
                    executeRules(x, y, matrixCopy);
                } catch (Exception e) {}
            }
        }
        matrix = cloneMatrix(matrixCopy);
    }

    protected abstract void executeRules(int x, int y, int[][] matrixCopy);

    protected int[][] cloneMatrix(int[][] matrix) {
        int[][] matrixCopy = new int[matrix.length][matrix[0].length];
        for(int x = 0; x < matrix.length; x++) {
            for(int y = 0; y < matrix[0].length; y++) {
                matrixCopy[x][y] = matrix[x][y];
            }
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
