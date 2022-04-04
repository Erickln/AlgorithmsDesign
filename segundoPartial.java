package Analysis;

import java.util.ArrayList;
import java.util.Collections;
import java.util.NoSuchElementException;

public class segundoPartial {

	private double[] P;
	private double[][] T;
	private final int N;

	public segundoPartial(In p, In t) {
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

	public int solve() {
		return 0;
	}

	

	public static void main(String args[]) {
		In p = new In("./WellCost6.txt");
		In t = new In("./PipeCost6.txt");
		segundoPartial exam = new segundoPartial(p, t);
		exam.solve();
		for (int i = 0; i < exam.P.length; i++) {
			// System.out.println(exam.T[i]);
			for (int j = 0; j < exam.T[i].length; j++) {
				System.out.print(exam.T[i]);
			}
			System.out.println();
		}

	}

	class Tuberia {
		int[] parent;
		int[] rank;

		
		public Tuberia() {};
		public Tuberia(int n) {
			parent = new int[n + 1];
			for (int i = 0; i <= n; i++) {
				parent[i] = i;
			}
			rank = new int[n + 1];
		}

		public int find(int x) {
			return x == parent[x] ? x : find(parent[x]);
		}

		public void union(int x, int y) {
			int px = find(x);
			int py = find(y);
			if (px == py)
				return;
			if (rank[px] >= rank[py]) {
				parent[py] = px;
				rank[px] += rank[py];
			} else {
				parent[px] = py;
				rank[py] += rank[px];
			}
		}
	}

}
