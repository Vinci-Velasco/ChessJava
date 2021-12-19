package misc;

import java.util.Objects;

/**
 * Represents coordinates in the chess board (2D array)
 */
public class Coordinate {
    public int x;
    public int y;

    public Coordinate(int y, int x) {
        this.x = x;
        this.y = y;
    }

    public void setCoordinates(int y, int x) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinate that = (Coordinate) o;
        return x == that.x && y == that.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
