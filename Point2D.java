package Analysis;

public class Point2D implements Comparable<Point2D>{
    public double x;
    public double y;

    public Point2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public int compareTo(Point2D o) {
        if (this.y != o.y) {
            return java.lang.Double.compare(o.y, this.y);
        } else {
            return java.lang.Double.compare(this.x, o.x);
        }
    }

    public boolean equals(Point2D p) {
        return this.x == p.x && this.y == p.y;
    }

    public String toString() {
        return String.format("(%.2f, %.2f)", x, y);
    }
}
