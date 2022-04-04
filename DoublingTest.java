package Analysis;

import clase.BigNumber;

public class DoublingTest {
	private static final int MAXIMUM_INTEGER = 1000000;

	// This class should not be instantiated.
	private DoublingTest() {
	}

	/**
	 * * Returns the amount of time to call {@code ThreeSum.count()} with <em>n</em>
	 * * random 6-digit integers. * @param n the number of integers * @return amount
	 * of time (in seconds) to call {@code ThreeSum.count()} * with <em>n</em>
	 * random 6-digit integers
	 */
	public static double timeTrial(int n) {
		int[] a = new int[n];
		for (int i = 0; i < n; i++) {
			a[i] = StdRandom.uniform(-MAXIMUM_INTEGER, MAXIMUM_INTEGER);
		}
		Stopwatch timer = new Stopwatch();
		ThreeSum.count(a);
		return timer.elapsedTime();
	}

	public static double timeTrialMultiplication(int n) {
		int[][] a = new int[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				a[i][j] = StdRandom.uniform(-MAXIMUM_INTEGER, MAXIMUM_INTEGER);
			}
		}

		int[][] b = new int[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				b[i][j] = StdRandom.uniform(-MAXIMUM_INTEGER, MAXIMUM_INTEGER);
			}
		}

		Stopwatch timer = new Stopwatch();
		ThreeSum.multix(a, b);
		return timer.elapsedTime();
	}

	public static double timeTrialBubble(int n) {
		int[] a = new int [n];
		for (int i = 0; i < a.length; i++) {
			a[i]=StdRandom.uniform(-MAXIMUM_INTEGER, MAXIMUM_INTEGER);
		}

		Stopwatch timer = new Stopwatch();
		TareaAnalysis.bubbleSort(a);
		return timer.elapsedTime();
	}
	public static double timeTrialKaratsuba(int n) {
		int[] a = new int [n];
		int[] b = new int [n];
		for (int i = 0; i < n; i++) {
			a[i]=StdRandom.uniform(1,10);
			if (a[i]<0) {
				a[i]*=-1;
			}
		}
		for (int i = 0; i < n; i++) {
			b[i]=StdRandom.uniform(1, 10);
			if (b[i]<0) {
				b[i]*=-1;
			}
		}

		Stopwatch timer = new Stopwatch();
		BigNumber.multiply(a, b);
		return timer.elapsedTime();
	}

	public static void main(String[] args) {
		for (int i = 0; i < 50; i++) {
//			System.out.println(StdRandom.uniform(1,10));
		}
		for (int n = 1; n <= 1073741824; n+=n) {
			double time = timeTrialKaratsuba(n);
			StdOut.printf("%7d %7.1f\n", n, time);
		}
	}
}