package org.example;

import java.util.Objects;

public class CoordinateKey {
    private final int x;
    private final int y;

    public CoordinateKey(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CoordinateKey that = (CoordinateKey) o;
        return x == that.x && y == that.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
}
