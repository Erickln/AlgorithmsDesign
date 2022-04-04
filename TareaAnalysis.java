package Analysis;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class TareaAnalysis {
	public static void multix(int[][] a, int[][] b) {
		int c[][] = new int[a.length][a.length];
		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < a.length; j++) {
				c[i][j] = 0;
				for (int k = 0; k < a.length; k++) {
					c[i][j] += a[i][k] * b[k][j];
				}
			}
		}
	}
	//prom 	 	 = n^2
	//peor caso  = n^2
	//mejor caso = n^2
/*	public static double[][] multix(double[][] a, double[][] b) {
		for (int i = 0; i < b.length; i++) {
			for (int j = 0; j 	< b.length; j++) {
				a[i][j] = a[i][j] * b[i][j];
			}
		}
		return a;
	}
*/
	public static double[][] multiplicacionMatrices(double[][] a, double[][] b) {
		double[][] c = new double[a.length][b[0].length];

		for (int i = 0; i < c.length; i++)
			for (int j = 0; j < c[0].length; j++)
				c[i][j] = c[i][j] + a[i][j] * b[i][j];
		return c;
	}
	
	static void bubbleSort(int arr[]) {
		System.out.println("Hago bubble");
		int n = arr.length;
		for (int i = 0; i < n - 1; i++)
			for (int j = 0; j < n - i - 1; j++)
				if (arr[j] > arr[j + 1]) {
					// swap arr[j+1] and arr[i]
					int temp = arr[j];
					arr[j] = arr[j + 1];
					arr[j + 1] = temp;
				}
	}

	/* Prints the array */
	static void printArray(int arr[]) {
		int n = arr.length;
		for (int i = 0; i < n; ++i)
			System.out.print(arr[i] + " ");
		System.out.println();
	}

	// Driver method to test above

	public static void main(String[] args) {
		int arr[] = { 64, 34, 25, 12, 22, 11, 90 };
		bubbleSort(arr);
		System.out.println("Sorted array");
		printArray(arr);
		/*
		 * try { File myObj = new File("filename.csv"); if (myObj.createNewFile()) {
		 * System.out.println("File created: " + myObj.getName()); } else {
		 * System.out.println("File already exists."); } } catch (IOException e) {
		 * System.out.println("An error occurred."); e.printStackTrace(); }
		 * 
		 * int MaxRandom = 1000000; // LinkedList<Integer> res = new
		 * LinkedList<Integer>(); for (int i = 250; i <= 10000; i += 250) { int[][] b =
		 * new int[i][i]; int[][] a = new int[i][i]; for (int j = 0; j < i; j++) { for
		 * (int k = 0; k < a.length; k++) { a[j][k] = (int)(Math.random() *
		 * (MaxRandom)); b[j][k] = (int)(Math.random() * (MaxRandom)); } }
		 * 
		 * long x = System.currentTimeMillis(); ThreeSum.multix(a, b);
		 * System.out.println("T(" + i + ")" + ((System.currentTimeMillis() - x)));
		 * System.gc(); try { FileWriter myWriter = new
		 * FileWriter("filename1Ab.csv",true); myWriter.write(i + "," +
		 * (System.currentTimeMillis() - x) + "\n"); myWriter.close(); //
		 * System.out.println("Successfully wrote to the file."); } catch (IOException
		 * e) { System.out.println("An error occurred."); e.printStackTrace(); }
		 * 
		 * }
		 */
		
		
	}
}