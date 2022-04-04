package Analysis;
import java.awt.Point;

public class PolygonEdge {

    private final Point v;
    private final Point w;

    /**
     * Initializes a polygon edge between vertices {@code v} and {@code w} of
     * the given {@code weight}.
     *
     * @param  v one vertex
     * @param  w the other vertex
     * @throws IllegalArgumentException if either {@code v} or {@code w}
     *         are null
     */
    public PolygonEdge(Point v, Point w) {
        if (v == null) throw new IllegalArgumentException("point v should not be null");
        if (w == null) throw new IllegalArgumentException("point w should not be null");
        this.v = v;
        this.w = w;
    }

    /**
	 * @param point
	 * @param point2
	 */

	/**
     * Returns either endpoint of this edge.
     *
     * @return either endpoint of this edge
     */
    public Point either() {
        return v;
    }

    /**
     * Returns the endpoint of this edge that is different from the given vertex.
     *
     * @param  vertex one endpoint of this edge
     * @return the other endpoint of this edge
     * @throws IllegalArgumentException if the vertex is not one of the
     *         endpoints of this edge
     */
    public Point other(Point vertex) {
        if      (vertex.equals(v)) return w;
        else if (vertex.equals(w)) return v;
        else throw new IllegalArgumentException("Illegal endpoint");
    }

    /**
     * Returns a string representation of this edge.
     *
     * @return a string representation of this edge
     */
    public String toString() {
        return String.format("(%d , %d) - (%d , %d)", v.x, v.y, w.x, w.y);
    }

    /**
     * Unit tests the {@code Edge} data type.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        PolygonEdge e = new PolygonEdge(new Point(5, 5), new Point(7, 6));
        StdOut.println(e);
    }
}
