package primmaze.render;

import primmaze.maze.Maze;

import java.util.Arrays;

public class ThickRenderer {

    public static char[][] build(Maze maze) {
        int n = maze.size();
        int h = 2 * n + 1;
        int w = 2 * n + 1;

        char[][] m = new char[h][w];
        for (int i = 0; i < h; i++) Arrays.fill(m[i], '#');

        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                int rr = 2 * r + 1;
                int cc = 2 * c + 1;
                m[rr][cc] = ' ';

                if (!maze.cell(r, c).walls[1]) m[rr][cc + 1] = ' ';
                if (!maze.cell(r, c).walls[2]) m[rr + 1][cc] = ' ';
            }
        }
        return m;
    }

    public static void addEntranceAndExit(char[][] m) {
        m[0][1] = ' ';
        m[m.length - 1][m[0].length - 2] = ' ';
    }

    public static String render(char[][] m) {
        StringBuilder sb = new StringBuilder();
        for (char[] row : m) sb.append(row).append("\n");
        return sb.toString();
    }
}
