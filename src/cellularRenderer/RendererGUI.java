package cellularRenderer;

import game_of_life.GameOfLife;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class RendererGUI extends JFrame {
    private JPanel mainPanel = new JPanel();
    private JPanel[][] matrixGUI;

    private GameOfLife gameOfLife;

    private JTextField seedField = new JTextField();
    private JButton generateButton = new JButton("Visualize");

    public RendererGUI(GameOfLife gameOfLife) {
        this.gameOfLife = gameOfLife;
        //setPreferredSize(new Dimension(800, 600));
        setLayout(new BorderLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setLocation(new Point(400, 100));

        setVisible(true);
    }

    public void init(int width, int height) {
        mainPanel.setLayout(new GridLayout(width, height));

        matrixGUI = new JPanel[width][height];

        add(mainPanel, BorderLayout.CENTER);

        generateButton.setPreferredSize(new Dimension(100, 15));

        JPanel southPanel = new JPanel();

        BoxLayout boxLayout = new BoxLayout(southPanel, BoxLayout.X_AXIS);
        southPanel.setLayout(boxLayout);

        southPanel.add(new JLabel("Seed: "));
        southPanel.add(seedField);

        southPanel.add(generateButton);
        generateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        add(southPanel, BorderLayout.SOUTH);

        for(int x = 0; x < matrixGUI.length; x++) {
            for(int y = 0; y < matrixGUI[0].length; y++) {
                JPanel panel = new JPanel();
                panel.setPreferredSize(new Dimension(2, 2));
                panel.setBackground(Color.BLACK);

                Integer panelX = x, panelY = y;
                panel.addMouseMotionListener(new MouseMotionAdapter() {
                    @Override
                    public void mouseMoved(MouseEvent e) {

                            gameOfLife.setValue(panelX, panelY, 1);
                    }
                });
                matrixGUI[x][y] = panel;
            }
        }

        for(JPanel[] panelRow: matrixGUI) {
            for(JPanel panel: panelRow) {
                mainPanel.add(panel);
            }
        }

        pack();
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
