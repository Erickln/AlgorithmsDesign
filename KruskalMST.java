package Analysis;

public class KruskalMST {
	private static final double FLOATING_POINT_EPSILON = 1E-12;

	private double weight; // weight of MST
	private Queue<Edge> mst = new Queue<Edge>(); // edges in MST

	/**
	 * Compute a minimum spanning tree (or forest) of an edge-weighted graph.
	 * 
	 * @param G the edge-weighted graph
	 */
	public KruskalMST(EdgeWeightedGraph G) {
		MinPQ<Edge> pq = new MinPQ<Edge>();
		for (Edge e : G.edges()) {
			pq.insert(e);
		}
		UF uf = new UF(G.V());
		while (!pq.isEmpty() && mst.size() < G.V() - 1/*-2?*/) {
            Edge edge = pq.delMin();
            int V = edge.either();
            int W = edge.other(V);
            if (uf.find(V) != uf.find(W)) {
                uf.union(V, W); 
                mst.enqueue(edge); 
                weight = weight+edge.weight();
            }
        }

	}

	/**
	 * Returns the edges in a minimum spanning tree (or forest).
	 * 
	 * @return the edges in a minimum spanning tree (or forest) as an iterable of
	 *         edges
	 */
	public Iterable<Edge> edges() {
		return mst;
	}

	/**
	 * Returns the sum of the edge weights in a minimum spanning tree (or forest).
	 * 
	 * @return the sum of the edge weights in a minimum spanning tree (or forest)
	 */
	public double weight() {
		return weight;
	}

	/**
	 * Unit tests the {@code KruskalMST} data type.
	 *
	 * @param args the command-line arguments
	 */
	public static void main(String[] args) {
		In in = new In("./EGGTest.txt");
		EdgeWeightedGraph G = new EdgeWeightedGraph(in);
		KruskalMST mst = new KruskalMST(G);
		for (Edge e : mst.edges()) {
			StdOut.println(e);
		}
		StdOut.printf("%.5f\n", mst.weight());
	}

}
