package primmaze.maze;

public class Maze {
    private final int n;
    private final Cell[][] cells;

    public Maze(int n) {
        this.n = n;
        this.cells = new Cell[n][n];
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                cells[r][c] = new Cell();
            }
        }
    }

    public int size() {
        return n;
    }

    public boolean inBounds(int r, int c) {
        return r >= 0 && r < n && c >= 0 && c < n;
    }

    public Cell cell(int r, int c) {
        return cells[r][c];
    }

    // Убрать стену между двумя соседними клетками
    public void carve(int r1, int c1, int r2, int c2, Direction d) {
        cells[r1][c1].walls[d.ordinal()] = false;
        cells[r2][c2].walls[d.opposite().ordinal()] = false;
    }
}
