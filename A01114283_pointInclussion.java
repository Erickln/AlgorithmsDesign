/**
 * @author 		Erick
 * @filename	A01114283_PointAuxInclussion.java
 * @date 		Nov 6, 2020
 * {git_config}
 */
package Analysis;

import java.util.NoSuchElementException;
import java.util.Stack;
import java.util.Vector;

public class A01114283_pointInclussion {
	final static double max = 30.0;

	static PolygonAux PolygonAux(PointAux[] PointAuxs) {
		return PolygonAux(PointAuxs, PointAuxs.length);
	}

	static PolygonAux PolygonAux(PointAux[] PointAuxs, int n) {
		PolygonAux res = new PolygonAux(n);
		for (int i = 0; i < PointAuxs.length; i++) {
			if (i == PointAuxs.length - 1) {
				res.addEdge(new PolygonEdgeAux(new PointAux(PointAuxs[i]), PointAuxs[0]));
			} else {
				res.addEdge(new PolygonEdgeAux(new PointAux(PointAuxs[i]), PointAuxs[i + 1]));
			}
		}
		return res;
	}

	static void paint(PolygonAux P) {
		paint(P, A01114283_pointInclussion.max);
	}

	static void paint(PolygonAux p, double relativeMax) {
		int cont = 0;
		int aux = 0;
		double max = 0;
		for (PolygonEdgeAux e : p.edges()) {
			if (e.either().y > max) {
				max = e.either().y;
			} else if (e.either().x > max) {
				max = e.either().x;
			}
			cont++;
		}
		double[] x = new double[cont];
		double[] y = new double[cont];
		for (PointAux po : p.vertices()) {
			x[aux] = (po.x * 1.0) / (relativeMax * 1.1);
			y[aux] = (po.y * 1.0) / (relativeMax * 1.1);
			aux++;
		}
		StdDraw.filledPolygon(x, y);
	}

	public static double orientation(PointAux p, PointAux q, PointAux r) {
		double val = (q.y * 1.0 - p.y * 1.0) * (r.x * 1.0 - q.x * 1.0)
				- (q.x * 1.0 - p.x * 1.0) * (r.y * 1.0 - q.y * 1.0);

		if (val == 0) {
			return 0;
		}
		if (val > 0) {
			return 1;
		} else {
			return 2;
		}
	}

	@SuppressWarnings("unused")
	public static PolygonAux convexHull(PointAux PointAuxs[], int n) {
		int lowest = 0;
		for (int i = 1; i < n; i++) {
			if (PointAuxs[i].x < PointAuxs[lowest].x) {
				lowest = i;
			}
		}
		int a = lowest;
		int b;
		Vector<PointAux> hullRes = new Vector<PointAux>();
		do {
			hullRes.add(PointAuxs[a]);
			b = (a + 1) % n;
			for (int i = 0; i < n; i++) {
				if (orientation(PointAuxs[a], PointAuxs[i], PointAuxs[b]) == 2)
					b = i;
			}
			a = b;

		} while (a != lowest);
		int cont = 0;
		for (PointAux temp : hullRes) {
			cont++;
		}
		PointAuxs = new PointAux[0];
		for (PointAux PointAux : hullRes) {
			PointAux[] aux = new PointAux[PointAuxs.length + 1];
			System.arraycopy(PointAuxs, 0, aux, 0, PointAuxs.length);
			aux[aux.length - 1] = PointAux;
			PointAuxs = aux;
		}
		cont = 0;
		PolygonAux res = new PolygonAux(cont);
		for (int i = 0; i < PointAuxs.length; i++) {
			if (i == PointAuxs.length - 1) {
				res.addEdge(new PolygonEdgeAux(PointAuxs[i], PointAuxs[0]));
			} else {
				res.addEdge(new PolygonEdgeAux(PointAuxs[i], PointAuxs[i + 1]));
			}
		}

		for (PointAux temp : hullRes) {
		//	System.out.println(temp + "\n");
		}

		return res;
	}

	static PolygonAux convexHull(PolygonAux p) {
		int cont = 0;
		for (@SuppressWarnings("unused")
		PointAux q : p.vertices())
			cont++;
		PointAux[] res = new PointAux[cont];
		cont = 0;

		for (PointAux PointAux : p.vertices()) {
			res[cont] = PointAux;
			cont++;
		}
		return convexHull(res, cont);

	}

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		int maxRelative = 10;
		PolygonEdgeAux e1 = new PolygonEdgeAux(new PointAux(1, 2), new PointAux(2, 4));
		PolygonEdgeAux e2 = new PolygonEdgeAux(new PointAux(2, 4), new PointAux(4, 2));
		PolygonEdgeAux e3 = new PolygonEdgeAux(new PointAux(4, 2), new PointAux(5, 5));
		PolygonEdgeAux e4 = new PolygonEdgeAux(new PointAux(5, 5), new PointAux(1, 5));
		PolygonEdgeAux e5 = new PolygonEdgeAux(new PointAux(1, 5), new PointAux(1, 2));
		PolygonAux p = new PolygonAux(5);
		p.addEdge(e1);
		p.addEdge(e2);
		p.addEdge(e3);
		p.addEdge(e4);
		p.addEdge(e5);
		// A01114283_PointAuxInclussion.paint(p);
		StdDraw.setPenColor(StdDraw.BLUE);
		PolygonEdgeAux H1  = new PolygonEdgeAux(new PointAux(1, 10), new PointAux(2, 8));
		PolygonEdgeAux H2  = new PolygonEdgeAux(new PointAux(2, 8), new PointAux(2, 7));
		PolygonEdgeAux H3  = new PolygonEdgeAux(new PointAux(2, 7), new PointAux(3, 4));
		PolygonEdgeAux H4  = new PolygonEdgeAux(new PointAux(3, 4), new PointAux(1, 2));
		PolygonEdgeAux H6  = new PolygonEdgeAux(new PointAux(1, 2), new PointAux(4, 1));
		PolygonEdgeAux H7  = new PolygonEdgeAux(new PointAux(4, 1), new PointAux(5, 4));
		PolygonEdgeAux H8  = new PolygonEdgeAux(new PointAux(5, 4), new PointAux(8, 2));
		PolygonEdgeAux H9  = new PolygonEdgeAux(new PointAux(8, 2), new PointAux(6, 5));
		PolygonEdgeAux H10 = new PolygonEdgeAux(new PointAux(6, 5), new PointAux(7, 6));
		PolygonEdgeAux H11 = new PolygonEdgeAux(new PointAux(7, 6), new PointAux(9, 6));
		PolygonEdgeAux H12 = new PolygonEdgeAux(new PointAux(9, 6), new PointAux(7, 7));
		PolygonEdgeAux H13 = new PolygonEdgeAux(new PointAux(7, 7), new PointAux(8, 8));
		PolygonEdgeAux H14 = new PolygonEdgeAux(new PointAux(8, 8), new PointAux(4, 8));
		PolygonEdgeAux H15 = new PolygonEdgeAux(new PointAux(4, 8), new PointAux(4, 9));
		PolygonEdgeAux H16 = new PolygonEdgeAux(new PointAux(4, 9), new PointAux(1, 10));
		PointAux[] res = new PointAux[15];

		res[0] = new PointAux(1, 10);
		res[1] = new PointAux(2, 8);
		res[2] = new PointAux(2, 7);
		res[3] = new PointAux(3, 4);
		res[4] = new PointAux(1, 2);
		res[5] = new PointAux(4, 1);
		res[6] = new PointAux(5, 4);
		res[7] = new PointAux(8, 2);
		res[8] = new PointAux(6, 5);
		res[9] = new PointAux(7, 6);
		res[10] = new PointAux(9, 6);
		res[11] = new PointAux(7, 7);
		res[12] = new PointAux(8, 8);
		res[13] = new PointAux(4, 8);
		res[14] = new PointAux(4, 9);

		PointAux[] res2 = new PointAux[6];

		res2[0] = new PointAux(1, 17);
		res2[1] = new PointAux(3, 16);
		res2[2] = new PointAux(4, 15);
		res2[3] = new PointAux(6, 17);
		res2[4] = new PointAux(5, 20);
		res2[5] = new PointAux(3, 21);
		PointAux[] res3 = new PointAux[6];

		res3[0] = new PointAux(9, 22);
		res3[1] = new PointAux(11, 24);
		res3[2] = new PointAux(11, 22);
		res3[3] = new PointAux(14, 22);
		res3[4] = new PointAux(14, 26);
		res3[5] = new PointAux(10, 29);
		PointAux[] res4 = new PointAux[6];

		res4[0] = new PointAux(16, 20);
		res4[1] = new PointAux(18, 22);
		res4[2] = new PointAux(18, 20);
		res4[3] = new PointAux(22, 20);
		res4[4] = new PointAux(22, 24);
		res4[5] = new PointAux(17, 27);
		PointAux[] res5 = new PointAux[6];

		res5[0] = new PointAux(13, 12);
		res5[1] = new PointAux(20, 15);
		res5[2] = new PointAux(14, 5);
		res5[3] = new PointAux(22, 7);
		res5[4] = new PointAux(22, 17);
		res5[5] = new PointAux(17, 20);

		PolygonAux p2 = new PolygonAux(15);
		p2.addEdge(H1);
		p2.addEdge(H2);
		p2.addEdge(H3);
		p2.addEdge(H4);
		p2.addEdge(H6);
		p2.addEdge(H7);
		p2.addEdge(H8);
		p2.addEdge(H9);
		p2.addEdge(H10);
		p2.addEdge(H11);
		p2.addEdge(H12);
		p2.addEdge(H13);
		p2.addEdge(H14);
		p2.addEdge(H15);
		p2.addEdge(H16);
		A01114283_pointInclussion.paint(PolygonAux(res));
		A01114283_pointInclussion.paint(PolygonAux(res2));
		A01114283_pointInclussion.paint(PolygonAux(res3));
		A01114283_pointInclussion.paint(PolygonAux(res4));
		A01114283_pointInclussion.paint(PolygonAux(res5));
		StdDraw.setPenColor(StdDraw.LIGHT_GRAY);
		PointAux x = new PointAux();
		// paint(PolygonAux(res));
		// paint(convexHull(p2));
		PolygonAux(res5).isInside(new PointAux(15.0,13.0));
		while (true) {
			if (StdDraw.isMousePressed()) {
				System.out.println("x: "+StdDraw.mouseX()* A01114283_pointInclussion.max);
				System.out.println("y: "+StdDraw.mouseY()* A01114283_pointInclussion.max);
				PointAux PointAuxo1 = new PointAux();
				PointAuxo1.setLocation(StdDraw.mouseX() * A01114283_pointInclussion.max,
						StdDraw.mouseY() * A01114283_pointInclussion.max);
				if (p2.isInside(PointAuxo1)) {
					paint(convexHull(p2));
				} else if (PolygonAux(res2).isInside(PointAuxo1)) {
					paint(convexHull(PolygonAux(res2)));
				} else if (PolygonAux(res3).isInside(PointAuxo1)) {
					paint(convexHull(PolygonAux(res3)));
				} else if (PolygonAux(res4).isInside(PointAuxo1)) {
					paint(convexHull(PolygonAux(res4)));
				} else if (PolygonAux(res5).isInside(PointAuxo1)) {
					paint(convexHull(PolygonAux(res5)));
				}
			}
		}
		// System.out.println("Hola");
	}

}

class PointAux {
	public double x, y;

	public PointAux(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public PointAux() {

	}

	/**
	 * @param PointAux
	 */
	public PointAux(PointAux PointAux) {
		x=PointAux.x;
		y=PointAux.y;
	}

	public void setLocation(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public String toString() {
		return "x:"+x+" / y:"+y;
	}

}
class PolygonEdgeAux {

    private final PointAux v;
    private final PointAux w;

    /**
     * Initializes a PolygonAux edge between vertices {@code v} and {@code w} of
     * the given {@code weight}.
     *
     * @param  v one vertex
     * @param  w the other vertex
     * @throws IllegalArgumentException if either {@code v} or {@code w}
     *         are null
     */
    public PolygonEdgeAux(PointAux v, PointAux w) {
        if (v == null) throw new IllegalArgumentException("PointAux v should not be null");
        if (w == null) throw new IllegalArgumentException("PointAux w should not be null");
        this.v = v;
        this.w = w;
    }

    /**
	 * @param PointAux
	 * @param PointAux2
	 */

	/**
     * Returns either endPointAux of this edge.
     *
     * @return either endPointAux of this edge
     */
    public PointAux either() {
        return v;
    }

    /**
     * Returns the endPointAux of this edge that is different from the given vertex.
     *
     * @param  vertex one endPointAux of this edge
     * @return the other endPointAux of this edge
     * @throws IllegalArgumentException if the vertex is not one of the
     *         endPointAuxs of this edge
     */
    public PointAux other(PointAux vertex) {
        if      (vertex.equals(v)) return w;
        else if (vertex.equals(w)) return v;
        else throw new IllegalArgumentException("Illegal endPointAux");
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
}

class PolygonAux {
	private static final String NEWLINE = System.getProperty("line.separator");

	private final int V;
	private Bag<PolygonEdgeAux> edges;

	/**
	 * Initializes an empty PolygonAux with {@code V} vertices.
	 *
	 * @param V the number of vertices
	 * @throws IllegalArgumentException if {@code V < 0}
	 */
	public PolygonAux(int V) {
		if (V < 0)
			throw new IllegalArgumentException("Number of vertices must be nonnegative");
		this.V = V;
		edges = new Bag<>();
	}

	/**
	 * Initializes a PolygonAux from an input stream. The format is the number of
	 * vertices <em>V</em>, followed by <em>E</em> pairs of PointAuxs, with each entry
	 * separated by whitespace.
	 *
	 * @param in the input stream
	 * @throws IllegalArgumentException if {@code in} is {@code null}
	 * @throws IllegalArgumentException if the endPointAuxs of any edge are not in
	 *                                  prescribed range
	 * @throws IllegalArgumentException if the number of vertices is negative
	 */
	public PolygonAux(In in) {
		if (in == null)
			throw new IllegalArgumentException("argument is null");

		try {
			V = in.readInt();
			edges = new Bag<>();

			if (V < 0)
				throw new IllegalArgumentException("Number of vertices must be nonnegative");
			int vx = in.readInt();
			int vy = in.readInt();
			PointAux v = new PointAux(vx, vy);
			PointAux tmp = v;
			for (int i = 1; i < V; i++) {
				int wx = in.readInt();
				int wy = in.readInt();
				PointAux w = new PointAux(wx, wy);
				PolygonEdgeAux e = new PolygonEdgeAux(tmp, w);
				addEdge(e);
				tmp = w;
			}
			addEdge(new PolygonEdgeAux(tmp, v));
		} catch (NoSuchElementException e) {
			throw new IllegalArgumentException("invalid input format in EdgeWeightedGraph constructor", e);
		}

	}

	/**
	 * Initializes a new PolygonAux that is a deep copy of {@code G}.
	 *
	 * @param G the PolygonAux to copy
	 */
	public PolygonAux(PolygonAux G) {
		this(G.V());
		for (int v = 0; v < G.V(); v++) {
			// reverse so that adjacency list is in same order as original
			Stack<PolygonEdgeAux> reverse = new Stack<>();
			for (PolygonEdgeAux e : G.edges) {
				reverse.push(e);
			}
			for (PolygonEdgeAux e : reverse) {
				edges.add(e);
			}
		}
	}

	public boolean isInside (PointAux q) {
        int contador = 0;
        for(PolygonEdgeAux e : this.edges) {
            PointAux minimo = e.either().y < e.other(e.either()).y ? e.either() : e.other(e.either()); 
            PointAux maximo = e.either().y < e.other(e.either()).y ? e.other(e.either()) : e.either();
            if(minimo.y <= q.y && maximo.y > q.y) {
                contador += (maximo.x * q.y - q.x  * maximo.y) - (minimo.x * q.y - q.x * minimo.y) + (minimo.x * maximo.y - maximo.x * minimo.y) < 0 ? 1 : 0;
            }
        }
        return contador % 2==1;
    }

	/**
	 * Returns the number of vertices in this PolygonAux.
	 *
	 * @return the number of vertices in this PolygonAux
	 */
	public int V() {
		return V;
	}

	/**
	 * Adds the PolygonAux edge {@code e} to this PolygonAux.
	 *
	 * @param e the edge
	 */
	public void addEdge(PolygonEdgeAux e) {
		edges.add(e);
	}

	/**
	 * Returns all edges in this PolygonAux. To iterate over the edges in this PolygonAux,
	 * use foreach notation:
	 *
	 * @return all edges in this PolygonAux, as an iterable
	 */
	public Iterable<PolygonEdgeAux> edges() {
		return edges;
	}
	
	

	public Iterable<PointAux> vertices() {
		Bag<PointAux> list = new Bag<>();
		for (PolygonEdgeAux e : edges) {
			PointAux v = e.either();
			list.add(v);
		}
		return list;
	}

	/**
	 * Returns a string representation of the PolygonAux.
	 *
	 * @return the number of vertices <em>V</em>, followed by the <em>V</em>
	 *         adjacency lists of edges
	 */
	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append(V + NEWLINE);
		for (PolygonEdgeAux e : edges) {
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
		In in = new In("./PolygonAuxTest.txt");
		PolygonAux G = new PolygonAux(in);
		StdOut.println(G);
		for (PointAux p : G.vertices()) {
			StdOut.println(String.format("(%d, %d)", p.x, p.y));
		}
	}

}