import cellularAutomatas.TestAutomata;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        //GameOfLife gameOfLife = new GameOfLife(100, 100);
        //gameOfLife.run();

        TestAutomata automata = new TestAutomata(200, 200);
        automata.run();
    }
}