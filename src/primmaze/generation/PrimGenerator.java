package primmaze.generation;

import primmaze.maze.*;

import java.util.*;

public class PrimGenerator {

    public static Maze generate(int n, long seed) {
        Random rnd = new Random(seed);
        Maze maze = new Maze(n);

        boolean[][] used = new boolean[n][n];
        PriorityQueue<Edge> pq =
                new PriorityQueue<>(Comparator.comparingInt(e -> e.weight));

        int sr = rnd.nextInt(n);
        int sc = rnd.nextInt(n);
        used[sr][sc] = true;
        addEdges(sr, sc, used, pq, n, rnd);

        int visited = 1;
        while (visited < n * n && !pq.isEmpty()) {
            Edge e = pq.poll();
            if (used[e.tr][e.tc]) continue;

            Direction d = direction(e.fr, e.fc, e.tr, e.tc);
            maze.carve(e.fr, e.fc, e.tr, e.tc, d);

            used[e.tr][e.tc] = true;
            visited++;
            addEdges(e.tr, e.tc, used, pq, n, rnd);
        }

        return maze;
    }

    private static void addEdges(int r, int c, boolean[][] used,
                                 PriorityQueue<Edge> pq, int n, Random rnd) {
        for (Direction d : Direction.values()) {
            int nr = r + d.dr;
            int nc = c + d.dc;
            if (nr < 0 || nr >= n || nc < 0 || nc >= n) continue;
            if (used[nr][nc]) continue;
            pq.add(new Edge(r, c, nr, nc, rnd.nextInt(1_000_000)));
        }
    }

    private static Direction direction(int r1, int c1, int r2, int c2) {
        for (Direction d : Direction.values()) {
            if (r1 + d.dr == r2 && c1 + d.dc == c2) return d;
        }
        throw new IllegalArgumentException("Cells are not neighbors");
    }
}
