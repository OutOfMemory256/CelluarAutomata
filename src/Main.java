import gameOfLife.GameOfLife;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        GameOfLife gameOfLife = new GameOfLife(100, 100);
        gameOfLife.run();
    }
}