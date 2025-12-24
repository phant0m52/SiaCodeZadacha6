package primmaze.gui;

import primmaze.maze.Maze;
import primmaze.render.ThickRenderer;

import javax.swing.*;
import java.awt.*;

public class MazePanel extends JPanel {

    private Maze maze;          // тонкие стены (исходный лабиринт)
    private char[][] thick;     // толстые стены (результат преобразования)
    private ViewMode mode = ViewMode.THIN;

    public void setMaze(Maze maze) {
        this.maze = maze;
        this.thick = null;
        this.mode = ViewMode.THIN;
        revalidate();
        repaint();
    }

    public void convertToThick() {
        if (maze == null) return;
        thick = ThickRenderer.build(maze);
        ThickRenderer.addEntranceAndExit(thick);
        mode = ViewMode.THICK;
        revalidate();
        repaint();
    }

    @Override
    public Dimension getPreferredSize() {
        if (mode == ViewMode.THICK && thick != null) {
            return new Dimension(thick[0].length * 18, thick.length * 18);
        }
        if (maze != null) {
            return new Dimension(maze.size() * 40, maze.size() * 40);
        }
        return new Dimension(600, 600);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (maze == null) return;

        if (mode == ViewMode.THIN) {
            drawThin(g);
        } else {
            drawThick(g);
        }
    }

    // ===== ОТРИСОВКА ТОНКИХ СТЕН =====
    private void drawThin(Graphics g) {
        int n = maze.size();
        int cellSize = Math.min(getWidth(), getHeight()) / n;

        g.setColor(Color.WHITE);
        g.fillRect(0, 0, getWidth(), getHeight());
        g.setColor(Color.BLACK);

        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                int x = c * cellSize;
                int y = r * cellSize;

                boolean[] w = maze.cell(r, c).walls;

                if (w[0]) g.drawLine(x, y, x + cellSize, y);                     // UP
                if (w[1]) g.drawLine(x + cellSize, y, x + cellSize, y + cellSize); // RIGHT
                if (w[2]) g.drawLine(x, y + cellSize, x + cellSize, y + cellSize); // DOWN
                if (w[3]) g.drawLine(x, y, x, y + cellSize);                     // LEFT
            }
        }
    }

    // ===== ОТРИСОВКА ТОЛСТЫХ СТЕН =====
    private void drawThick(Graphics g) {
        if (thick == null) return;

        int rows = thick.length;
        int cols = thick[0].length;
        int cellSize = Math.min(getWidth() / cols, getHeight() / rows);

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                g.setColor(thick[r][c] == '#' ? Color.BLACK : Color.WHITE);
                g.fillRect(c * cellSize, r * cellSize, cellSize, cellSize);
            }
        }
    }
}
