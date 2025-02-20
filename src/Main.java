import cellularAutomatas.Mycelium;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        new Thread() {
            @Override
            public void run() {
                //GameOfLife gameOfLife = new GameOfLife(100, 100);
                //gameOfLife.run();
            }
        }.start();

        new Thread() {
            @Override
            public void run() {
                Mycelium grassIncreasing = new Mycelium(100, 100);
                grassIncreasing.run();
            }
        }.start();

        //TestGenerator generator = new TestGenerator(100, 100);
        //generator.run();
    }
}