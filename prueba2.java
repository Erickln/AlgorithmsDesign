/**
 * @author 		Erick
 * @filename	prueba.java
 * @date 		Aug 16, 2020
 * {git_config}
 */
package Analysis;

public class prueba2 {
	final static int Max = 100000;
	final static int Min = 0;

	// A function to implement bubble sort
	static void bubbleSort(int arr[], int n) {
		// Base case
		if (n == 1)
			return;

		// One pass of bubble sort. After
		// this pass, the largest element
		// is moved (or bubbled) to end.
		for (int i = 0; i < n - 1; i++)
			if (arr[i] > arr[i + 1]) {
				// swap arr[i], arr[i+1]
				int temp = arr[i];
				arr[i] = arr[i + 1];
				arr[i + 1] = temp;
			}

		// Largest element is fixed,
		// recur for remaining array
		bubbleSort(arr, n - 1);
	}

	static void bubbleSort(int arr[]) {
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
	void printArray(int arr[]) {
		int n = arr.length;
		for (int i = 0; i < n; ++i)
			System.out.print(arr[i] + " ");
		System.out.println();
	}

	public static void main(String[] args) {
		for (int i = 4000; i <= 128000; i += i) {
			System.out.print("i=" + i + "   ");
			int[] array = new int[i];
			for (int j = 0; j < i; j++) {
//				array[j] = (int) (Math.random() * (prueba.Max));
			}
			long x = System.currentTimeMillis();
//			prueba.bubbleSort(array);
			System.out.println("T(i)=" + (System.currentTimeMillis() - x));
			System.gc();

		}
	}

}
