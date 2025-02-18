package renderer;

import cellularAutomataCore.AbstractCellularElement;
import gameOfLife.GameOfLife;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class RendererGUI extends JFrame {
    private JPanel[][] matrixGUI;
    private JPanel matrixContainer;

    private static boolean isRightButtonPressed = false;
    private static boolean isLeftButtonPressed = false;

    private GameOfLife gameOfLife;

    private JButton stopButton = new JButton("Stop");
    private JButton startButton = new JButton("Start");
    private JButton doTurnButton = new JButton("Do turn");
    private JButton clearFieldButton = new JButton("Clear");

    public RendererGUI(GameOfLife gameOfLife) {
        this.gameOfLife = gameOfLife;

        setLayout(new BorderLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setLocation(new Point(400, 100));
    }

    public void init(int width, int height) {
        matrixContainer = new JPanel(new GridLayout(width, height));
        matrixContainer.setPreferredSize(new Dimension(800, 600));
        add(matrixContainer, BorderLayout.CENTER);

        JPanel southPanel = new JPanel();
        BoxLayout boxLayout = new BoxLayout(southPanel, BoxLayout.X_AXIS);
        southPanel.setLayout(boxLayout);

        initButtons();
        southPanel.add(stopButton);
        southPanel.add(startButton);
        southPanel.add(doTurnButton);
        southPanel.add(clearFieldButton);
        add(southPanel, BorderLayout.SOUTH);

        matrixGUI = new JPanel[width][height];
        initMatrix();
        addMatrixToContainer();

        addMouseControllers();

        pack();
        setVisible(true);
    }

    private void initButtons() {
        stopButton.setPreferredSize(new Dimension(100, 20));
        stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameOfLife.stop();
            }
        });

        startButton.setPreferredSize(new Dimension(100, 20));
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameOfLife.start();
            }
        });

        doTurnButton.setPreferredSize(new Dimension(100, 20));
        doTurnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameOfLife.doMove();
                gameOfLife.visualize();
            }
        });

        clearFieldButton.setPreferredSize(new Dimension(100, 20));
        clearFieldButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int x = 0; x < matrixGUI.length; x++) {
                    for (int y = 0; y < matrixGUI[0].length; y++) {
                        gameOfLife.setValue(x, y, 0);
                    }
                }
                gameOfLife.visualize();
            }
        });
    }

    private void addMouseControllers() {
        matrixContainer.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (SwingUtilities.isRightMouseButton(e)) {
                    isRightButtonPressed = true;
                    handlePanelHover(e.getComponent(), e.getPoint());
                } else if(SwingUtilities.isLeftMouseButton(e)) {
                    isLeftButtonPressed = true;
                    handlePanelHover(e.getComponent(), e.getPoint());
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                isRightButtonPressed = false;
                isLeftButtonPressed = false;
            }
        });

        matrixContainer.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (isRightButtonPressed || isLeftButtonPressed) {
                    handlePanelHover(e.getComponent(), e.getPoint());
                }
            }
        });
    }

    private void handlePanelHover(Component source, Point mousePoint) {
        // Конвертируем координаты относительно контейнера
        Point containerPoint = SwingUtilities.convertPoint(source, mousePoint, source.getParent());

        // Находим панель под курсором
        Component target = SwingUtilities.getDeepestComponentAt(source.getParent(), containerPoint.x, containerPoint.y);

        if (target instanceof JPanel hoveredPanel) {
            for(int x = 0; x < matrixGUI.length; x++) {
                for(int y = 0; y < matrixGUI[0].length; y++) {
                    if(matrixGUI[x][y].equals(hoveredPanel)) {
                        if(isLeftButtonPressed)
                            gameOfLife.setValue(x, y, 1);
                        if(isRightButtonPressed)
                            gameOfLife.setValue(x, y, 0);
                        gameOfLife.visualize();
                        return;
                    }
                }
            }
        }
    }

    private void initMatrix() {
        for(int x = 0; x < matrixGUI.length; x++) {
            for(int y = 0; y < matrixGUI[0].length; y++) {
                JPanel panel = new JPanel();
                panel.setPreferredSize(new Dimension(getPreferredSize().width / matrixGUI.length, getPreferredSize().height / matrixGUI[0].length));
                panel.setBackground(Color.BLACK);
                matrixGUI[x][y] = panel;
            }
        }
    }

    private void addMatrixToContainer() {
        for(JPanel[] panelRow: matrixGUI) {
            for(JPanel panel: panelRow) {
                matrixContainer.add(panel);
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
