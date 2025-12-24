package primmaze.maze;

public enum Direction {
    UP(-1, 0),
    RIGHT(0, 1),
    DOWN(1, 0),
    LEFT(0, -1);

    public final int dr;
    public final int dc;

    Direction(int dr, int dc) {
        this.dr = dr;
        this.dc = dc;
    }

    public Direction opposite() {
        return values()[(ordinal() + 2) % 4];
    }
}
