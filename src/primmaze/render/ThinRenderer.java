package primmaze.render;

import primmaze.maze.Maze;

public class ThinRenderer {

    public static String render(Maze maze) {
        int n = maze.size();
        StringBuilder sb = new StringBuilder();

        sb.append("+");
        for (int c = 0; c < n; c++) sb.append("---+");
        sb.append("\n");

        for (int r = 0; r < n; r++) {
            sb.append("|");
            for (int c = 0; c < n; c++) {
                sb.append("   ");
                sb.append(maze.cell(r, c).walls[1] ? "|" : " ");
            }
            sb.append("\n");

            sb.append("+");
            for (int c = 0; c < n; c++) {
                sb.append(maze.cell(r, c).walls[2] ? "---" : "   ");
                sb.append("+");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
