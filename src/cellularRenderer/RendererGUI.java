package cellularRenderer;

import game_of_life.GameOfLife;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class RendererGUI extends JFrame {
    private JPanel[][] matrixGUI;
    private JPanel matrixContainer;

    private static boolean isRightButtonPressed = false;

    private GameOfLife gameOfLife;


    private JButton stopButton = new JButton("Stop");
    private JButton startButton = new JButton("Start");

    public RendererGUI(GameOfLife gameOfLife) {
        this.gameOfLife = gameOfLife;
        setPreferredSize(new Dimension(800, 600));
        setLayout(new BorderLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setLocation(new Point(400, 100));

        setVisible(true);
    }

    public void init(int width, int height) {
        matrixContainer = new JPanel(new GridLayout(width, height));

        matrixGUI = new JPanel[width][height];

        add(matrixContainer, BorderLayout.CENTER);

        stopButton.setPreferredSize(new Dimension(100, 15));

        JPanel southPanel = new JPanel();

        BoxLayout boxLayout = new BoxLayout(southPanel, BoxLayout.X_AXIS);
        southPanel.setLayout(boxLayout);

        southPanel.add(stopButton);
        stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameOfLife.stop();
            }
        });

        southPanel.add(startButton);
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameOfLife.start();
            }
        });

        add(southPanel, BorderLayout.SOUTH);

        createMatrix();

        for(JPanel[] panelRow: matrixGUI) {
            for(JPanel panel: panelRow) {
                matrixContainer.add(panel);
            }
        }

        matrixContainer.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (SwingUtilities.isRightMouseButton(e)) {
                    isRightButtonPressed = true;
                    handlePanelHover(e.getComponent(), e.getPoint());
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                isRightButtonPressed = false;
            }
        });

        matrixContainer.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (isRightButtonPressed) {
                    handlePanelHover(e.getComponent(), e.getPoint());
                }
            }
        });
        pack();
    }

    private void handlePanelHover(Component source, Point mousePoint) {
        // Конвертируем координаты относительно контейнера
        Point containerPoint = SwingUtilities.convertPoint(source, mousePoint, source.getParent());

        // Находим панель под курсором
        Component target = SwingUtilities.getDeepestComponentAt(source.getParent(), containerPoint.x, containerPoint.y);

        if (target instanceof JPanel) {
            JPanel hoveredPanel = (JPanel) target;
            for(int x = 0; x < matrixGUI.length; x++) {
                for(int y = 0; y < matrixGUI[0].length; y++) {
                    if(matrixGUI[x][y].equals(hoveredPanel)) {
                        gameOfLife.setValue(x, y, 1);
                        gameOfLife.visualize();
                        return;
                    }
                }
            }
        }
    }

    private void createMatrix() {
        for(int x = 0; x < matrixGUI.length; x++) {
            for(int y = 0; y < matrixGUI[0].length; y++) {
                JPanel panel = new JPanel();
                panel.setPreferredSize(new Dimension(getPreferredSize().width / matrixGUI.length, getPreferredSize().height / matrixGUI[0].length));
                panel.setBackground(Color.BLACK);
                matrixGUI[x][y] = panel;
            }
        }
    }

    public void visualize(AbstractCellularElement[][] matrix) {
        for(int i = 0; i < matrix.length; i++) {
            for(int j = 0; j < matrix[0].length; j++) {
                matrixGUI[i][j].setBackground(matrix[i][j].getColor());
            }
        }
        repaint();
    }
}
