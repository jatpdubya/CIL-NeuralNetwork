package org.cil.projectnn.network;

public class Point {
    private final float x;
    private final float y;
    private final int expected;

    public Point(float m, float b) {
        this.x = (float) Math.random() * 101;
        this.y = (float) Math.random() * 101;
        // Allow optional slope to be determined by inputs when initializing the Point
        if (m * this.x + b > y) {
            this.expected = 1;
        } else {
            this.expected = -1;
        }
    }

    public Point() {
        Point p = new Point(1, 0);
        this.x = p.x;
        this.y = p.y;
        this.expected = p.expected;
    }

    public float[] getInputs() {
        return new float[] {this.x, this.y};
    }

    public int getExpected() {
        return this.expected;
    }
}
