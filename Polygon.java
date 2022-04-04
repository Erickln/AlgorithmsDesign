package Analysis;

import java.awt.*;
import java.util.NoSuchElementException;
import java.util.Stack;

public class Polygon {
	private static final String NEWLINE = System.getProperty("line.separator");

	private final int V;
	private Bag<PolygonEdge> edges;

	/**
	 * Initializes an empty polygon with {@code V} vertices.
	 *
	 * @param V the number of vertices
	 * @throws IllegalArgumentException if {@code V < 0}
	 */
	public Polygon(int V) {
		if (V < 0)
			throw new IllegalArgumentException("Number of vertices must be nonnegative");
		this.V = V;
		edges = new Bag<>();
	}

	/**
	 * Initializes a polygon from an input stream. The format is the number of
	 * vertices <em>V</em>, followed by <em>E</em> pairs of points, with each entry
	 * separated by whitespace.
	 *
	 * @param in the input stream
	 * @throws IllegalArgumentException if {@code in} is {@code null}
	 * @throws IllegalArgumentException if the endpoints of any edge are not in
	 *                                  prescribed range
	 * @throws IllegalArgumentException if the number of vertices is negative
	 */
	public Polygon(In in) {
		if (in == null)
			throw new IllegalArgumentException("argument is null");

		try {
			V = in.readInt();
			edges = new Bag<>();

			if (V < 0)
				throw new IllegalArgumentException("Number of vertices must be nonnegative");
			int vx = in.readInt();
			int vy = in.readInt();
			Point v = new Point(vx, vy);
			Point tmp = v;
			for (int i = 1; i < V; i++) {
				int wx = in.readInt();
				int wy = in.readInt();
				Point w = new Point(wx, wy);
				PolygonEdge e = new PolygonEdge(tmp, w);
				addEdge(e);
				tmp = w;
			}
			addEdge(new PolygonEdge(tmp, v));
		} catch (NoSuchElementException e) {
			throw new IllegalArgumentException("invalid input format in EdgeWeightedGraph constructor", e);
		}

	}

	/**
	 * Initializes a new polygon that is a deep copy of {@code G}.
	 *
	 * @param G the polygon to copy
	 */
	public Polygon(Polygon G) {
		this(G.V());
		for (int v = 0; v < G.V(); v++) {
			// reverse so that adjacency list is in same order as original
			Stack<PolygonEdge> reverse = new Stack<>();
			for (PolygonEdge e : G.edges) {
				reverse.push(e);
			}
			for (PolygonEdge e : reverse) {
				edges.add(e);
			}
		}
	}

	public boolean isInside (Point q) {
        int contador = 0;
        for(PolygonEdge e : this.edges) {
            Point minimo = e.either().y < e.other(e.either()).y ? e.either() : e.other(e.either()); 
            Point maximo = e.either().y < e.other(e.either()).y ? e.other(e.either()) : e.either();
            if(minimo.y <= q.y && maximo.y > q.y) {
                contador += (maximo.x * q.y - q.x  * maximo.y) - (minimo.x * q.y - q.x * minimo.y) + (minimo.x * maximo.y - maximo.x * minimo.y) < 0 ? 1 : 0;
            }
        }
        return contador % 2==1;
    }

	/**
	 * Returns the number of vertices in this polygon.
	 *
	 * @return the number of vertices in this polygon
	 */
	public int V() {
		return V;
	}

	/**
	 * Adds the polygon edge {@code e} to this polygon.
	 *
	 * @param e the edge
	 */
	public void addEdge(PolygonEdge e) {
		edges.add(e);
	}

	/**
	 * Returns all edges in this polygon. To iterate over the edges in this polygon,
	 * use foreach notation:
	 *
	 * @return all edges in this polygon, as an iterable
	 */
	public Iterable<PolygonEdge> edges() {
		return edges;
	}
	
	

	public Iterable<Point> vertices() {
		Bag<Point> list = new Bag<>();
		for (PolygonEdge e : edges) {
			Point v = e.either();
			list.add(v);
		}
		return list;
	}

	/**
	 * Returns a string representation of the polygon.
	 *
	 * @return the number of vertices <em>V</em>, followed by the <em>V</em>
	 *         adjacency lists of edges
	 */
	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append(V + NEWLINE);
		for (PolygonEdge e : edges) {
			s.append(e + "  ");
			s.append(NEWLINE);
		}
		return s.toString();
	}

	/**
	 * Unit tests the {@code EdgeWeightedGraph} data type.
	 *
	 * @param args the command-line arguments
	 */
	public static void main(String[] args) {
		In in = new In("./PolygonTest.txt");
		Polygon G = new Polygon(in);
		StdOut.println(G);
		for (Point p : G.vertices()) {
			StdOut.println(String.format("(%d, %d)", p.x, p.y));
		}
	}

}