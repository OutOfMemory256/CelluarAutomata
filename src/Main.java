import cellularAutomatas.CaveGenerator;
import cellularAutomatas.GameOfLife;
import cellularAutomatas.TestGenerator;

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
                //CaveGenerator automata = new CaveGenerator(100, 100);
                //automata.run();
            }
        }.start();

        TestGenerator generator = new TestGenerator(100, 100);
        generator.run();
    }
}