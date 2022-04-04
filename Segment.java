package Analysis;


public class Segment implements Comparable<Segment> {

    private final Point2D v;
    private final Point2D w;
    private final int id;

    /**
     * Initializes a segment between vertices {@code v} and {@code w}.
     *
     * @param  v one vertex
     * @param  w the other vertex
     * @throws IllegalArgumentException if either {@code v} or {@code w}
     *         are null
     */
    public Segment(int id, Point2D v, Point2D w) {
        if (id < -1) throw new IllegalArgumentException("id should be >= 0");
        if (v == null) throw new IllegalArgumentException("point v should not be null");
        if (w == null) throw new IllegalArgumentException("point w should not be null");
        this.v = v;
        this.w = w;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    /**
     * Returns the upper endpoint of this segment.
     *
     * @return upper endpoint of this segment
     */
    public Point2D upper() {
        if (v.y > w.y)
            return v;
        else if (w.y > v.y)
            return w;
        else
            return v.x > w.x ? v : w;
    }

    /**
     * Returns the lower endpoint of this segment.
     *
     * @return lower endpoint of this segment
     */
    public Point2D lower() {
        if (v.y < w.y)
            return v;
        else if (w.y < v.y)
            return w;
        else
            return v.x < w.x ? v : w;
    }

    public boolean isSinglePoint() {
        return v.x == w.x && v.y == w.y;
    }

    @Override
    public int compareTo(Segment o) {
        if (this.v.y != o.v.y) {
            return java.lang.Double.compare(o.v.y, this.v.y);
        } else {
            return java.lang.Double.compare(this.v.x, o.v.x);
        }
    }

    public double getCurrentX(double y) {
        double m = (v.y - w.y) / (v.x - w.x);
        return (y - w.y + m * w.x) / m;
    }

    /**
     * Returns a string representation of this segment.
     *
     * @return a string representation of this segment
     */
    public String toString() {
        return String.format("(%.2f , %.2f) - (%.2f , %.2f)", lower().x, lower().y, upper().x, upper().y);
    }

    /**
     * Unit tests the {@code Edge} data type.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        Segment e = new Segment(0, new Point2D( 7.0, 6.0), new Point2D(5.0, 7.0));
        Segment b = new Segment(1, new Point2D(5.0, 7.0), new Point2D(8.0, 2.0));
        StdOut.println(e.compareTo(b));

    }
}
