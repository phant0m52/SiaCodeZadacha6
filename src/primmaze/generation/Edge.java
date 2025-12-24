package primmaze.generation;

public class Edge {
    public final int fr, fc;
    public final int tr, tc;
    public final int weight;

    public Edge(int fr, int fc, int tr, int tc, int weight) {
        this.fr = fr;
        this.fc = fc;
        this.tr = tr;
        this.tc = tc;
        this.weight = weight;
    }
}
