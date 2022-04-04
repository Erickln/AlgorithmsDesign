/**
 * @author 		Erick
 * @filename	Puzzle.java
 * @date 		Nov 21, 2020
 * {git_config}
 */
package Analysis;

import java.util.ArrayList;

public class Auxiliar {

	public Auxiliar(Board initial) {
		// find a solution to the initial board
	}

	public int moves() {
		// return min number of moves to solve the initial board;
		return 0;
	}

	// -1 if no such solution
	public String toString() {
		// return string representation of solution (as described above)
		return "";
	}

	// read puzzle instance from stdin and print solution to stdout (in format
	// above)
	public static void main(String[] args) {

	}

}

class Board2 {

	private int[][] board;
	private final int n;
	private int hamming; // reemplazar
	private int manhattan; // reemplazar
	private int blankX, blankY;

	public Board2(int[][] tiles) {
		// construct a board from an N-by-N array of tiles
		n = tiles.length;
		board = new int[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				board[i][j] = tiles[i][j];// copiar el tablero
				if (board[i][j] == 0) {
					blankX = i;
					blankY = j;
				} else {
					int x = board[i][j] - 1;
					int deltaX = Math.abs(i - x / n);
					int deltaY = Math.abs(j - x % n);
					if (deltaX + deltaY > 0) {
						hamming++;
						manhattan += deltaX + deltaY;
					}
				}
			}
		}
	}
//		   hamming = 0;
//		   manhattan = 0;

	public int hamming() {
		// return number of blocks out of place
		return hamming;
	}

	public int manhattan() {
		// return sum of Manhattan distances between blocks and goal
		return manhattan;
	}

	public int dimension() {
		// dimension of the board
		return n;
	}

	public boolean equals(Object y) {
		// does this board equal y?
		if (this == y)
			return true;
		if (y == null)
			return false;
		Board2 other = (Board2) y;
		if (dimension() != other.dimension())
			return false;

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (board[i][j] != ((Board2) y).board[i][j])
					return false;
			}
		}
		return true;
	}

	public Iterable<Board2> neighbors() {
		// return an Iterable of all neighboring board positions
		ArrayList<Board2> boards = new ArrayList<Board2>();

		return null;
	}

	public String toString() {
		StringBuilder boa = new StringBuilder();
		boa.append(n);
		for (int i = 0; i < n; i++) {
			boa.append("\n");
			String sep = "";
			for (int j = 0; j < n; j++) {
				boa.append(sep + board[i][j]);
				sep = " ";
			}
		}
		return boa.toString();
	}

	// test client
	public static void main(String[] args) {
		int[][] x = new int[][] { { 1, 2, 3 }, { 0, 4, 6 }, { 7, 5, 8 } };
		Board2 B = new Board2(x);

	}
}