package primmaze.gui;

import primmaze.generation.PrimGenerator;
import primmaze.maze.Maze;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.Random;

public class MainFrame extends JFrame {

    private final MazePanel mazePanel = new MazePanel();
    private JTextField sizeField;
    private long seed = System.nanoTime();

    public MainFrame() {
        super("Генерация лабиринта — алгоритм Прима (MST)");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        add(createControlPanel(), BorderLayout.NORTH);
        add(new JScrollPane(mazePanel), BorderLayout.CENTER);

        setSize(900, 700);
        setLocationRelativeTo(null);

        generate(); // первичная генерация
    }

    private JPanel createControlPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 8));
        panel.setBorder(new EmptyBorder(5, 10, 5, 10));

        panel.add(new JLabel("Размер N:"));

        sizeField = new JTextField("10", 4);
        panel.add(sizeField);

        JButton genBtn = new JButton("Сгенерировать");
        genBtn.addActionListener(e -> generate());
        panel.add(genBtn);

        JButton seedBtn = new JButton("Новый seed");
        seedBtn.addActionListener(e -> {
            seed = new Random().nextLong();
            generate();
        });
        panel.add(seedBtn);

        JButton thickBtn = new JButton("Преобразовать в толстые стены");
        thickBtn.addActionListener(e -> mazePanel.convertToThick());
        panel.add(thickBtn);

        JLabel info = new JLabel("Алгоритм Прима (минимальное остовное дерево)");
        info.setForeground(Color.DARK_GRAY);
        panel.add(info);

        return panel;
    }

    private void generate() {
        int n;
        try {
            n = Integer.parseInt(sizeField.getText().trim());
            if (n < 2 || n > 100) throw new NumberFormatException();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(
                    this,
                    "Размер должен быть числом от 2 до 100",
                    "Ошибка",
                    JOptionPane.ERROR_MESSAGE
            );
            return;
        }

        Maze maze = PrimGenerator.generate(n, seed);
        mazePanel.setMaze(maze);
    }
}
