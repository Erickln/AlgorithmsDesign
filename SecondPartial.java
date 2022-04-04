package Analysis;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

public class SecondPartial {

	private double[] P;
	private double[][] T;
	private final int N;

	public SecondPartial(In p, In t) {
		if (p == null || t == null)
			throw new IllegalArgumentException("argument is null");

		try {
			N = p.readInt();
			P = new double[N];
			T = new double[N][N];
			for (int i = 0; i < N; i++) {
				P[i] = p.readDouble();
				for (int j = 0; j < N; j++) {
					T[i][j] = t.readDouble();
				}
			}

		} catch (NoSuchElementException e) {
			throw new IllegalArgumentException("invalid input format in SecondPartial constructor", e);
		}

	}

	public int getN() {
		return N;
	}

	public double[] getP() {
		return P;
	}

	public double[][] getT() {
		return T;
	}

	public double solve() {
		String temp = "";
		String welltemp = "";
		List<Edge2> costos = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			for (int j = 1 + i; j < N; j++) {
				costos.add(new Edge2(i, j, T[j][i]));
			}
		}
		for (int i = 0; i < N; i++) {
			costos.add(new Edge2(i, N, P[i]));
		}
		Collections.sort(costos);
		double costosMins = 0;
		UF2 UnionFind = new UF2(N + 1);
		int aux = N;
		// for (Edge2 edge : costs) {
		for (int i = 0; i < costos.size(); i++) {
			Edge2 edge = costos.get(i);
			int a = UnionFind.find(edge.getV());
			int b = UnionFind.find(edge.getW());
			if (a ==  b)
				continue;

			costosMins += edge.weight();
			if (edge.getV() == N) {
				welltemp += "" + edge.getW() + ", ";
			} else if (edge.getW() == N) {
				welltemp += "" + edge.getV() + ", ";
			} else {
				temp += "" + edge.getV() + " - " + edge.getW() + ", ";

			}
			UnionFind.union(edge.getV(), edge.getW());
			// for each union, we connnect one node
			aux--;
			// if all nodes already connected, terminate early
			if (aux == 0) {
				welltemp = welltemp.substring(0, welltemp.length() - 2);
				temp = temp.substring(0, temp.length() - 2);

				System.out.println("Well: " + welltemp + "\n" + "Pipes: " + temp + "\nMinimum Cost: " + costosMins);
				System.out.println();
				return costosMins;
			}
		}
		return costosMins;
	}

	public static void main(String args[]) {
		In p = new In("./WellCost6.txt");
		In t = new In("./PipeCost6.txt");
		SecondPartial exam = new SecondPartial(p, t);
		exam.solve();

	}

	class Edge2 implements Comparable<Edge2> {
		private final int v;
		private final int w;
		private final double weight;

		public Edge2(int v, int w, double weight) {
			if (v < 0)
				throw new IllegalArgumentException("vertex index must be a nonnegative integer");
			if (w < 0)
				throw new IllegalArgumentException("vertex index must be a nonnegative integer");
			if (Double.isNaN(weight))
				throw new IllegalArgumentException("Weight is NaN");
			this.v = v;
			this.w = w;
			this.weight = weight;
		}

		public int getW() {
			return w;
		}

		public int getV() {
			return v;
		}

		public double weight() {
			return weight;
		}

		public int either() {
			return v;
		}

		public int other(int vertex) {
			if (vertex == v)
				return w;
			else if (vertex == w)
				return v;
			else
				throw new IllegalArgumentException("Illegal endpoint");
		}

		@Override
		public int compareTo(Edge2 that) {
			return Double.compare(this.weight, that.weight);
		}

		public String toString() {
			return String.format("%d-%d %.5f", v, w, weight);
		}
	}

	class UF2 {

		private int[] parent;
		private byte[] rank;
		private double count;

		public UF2(int n) {
			if (n < 0)
				throw new IllegalArgumentException();
			count = n;
			parent = new int[n];
			rank = new byte[n];
			for (int i = 0; i < n; i++) {
				parent[i] = i;
				rank[i] = 0;
			}
		}

		public int find(int p) {
			validate(p);
			while (p != parent[p]) {
				parent[p] = parent[parent[p]]; // path compression by halving
				p = parent[p];
			}
			return p;
		}

		public double count() {
			return count;
		}

		public boolean connected(int p, int q) {
			return find(p) == find(q);
		}

		public void union(int p, int q) {
			int rootP = find(p);
			int rootQ = find(q);
			if (rootP == rootQ)
				return;
			if (rank[rootP] < rank[rootQ])
				parent[rootP] = rootQ;
			else if (rank[rootP] > rank[rootQ])
				parent[rootQ] = rootP;
			else {
				parent[rootQ] = rootP;
				rank[rootP]++;
			}
			count--;
		}

		private void validate(int p) {
			int n = parent.length;
			if (p < 0 || p >= n) {
				throw new IllegalArgumentException("index " + p + " is not between 0 and " + (n - 1));
			}
		}
	}

}
