/**
 * @Author
 * Mathew Gabriel Lopez Garcia
 * A01635001
 */
package Analysis;

import java.util.ArrayList;
public class Board {
    private int[][] board;
    private final int n;
    private int distanciaHamming;
    private int distanciaManhattan;
    private int blankX, blankY;

    public Board(int[][] tiles) {
        if(tiles == null){												//*1
            System.out.println("Board can't be null");
        }
        n = tiles.length;
        board = new int[n][n];
        distanciaHamming = 0;
        distanciaManhattan = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = tiles[i][j];								
                if (board[i][j] == 0) {									//*n2
                    blankX = i;
                    blankY = j;
                }
                else {
                    int x = board[i][j] -1; //-1 para evitar comenzar por el 0
                    int distanciaX = Math.abs(i - x / n);				//*n2
                    int distanciaY = Math.abs(j - x % n);				//*n2
                    int nuevaManhattan = distanciaX + distanciaY;
                    if (distanciaX + distanciaY > 0) {					//*n2
                        distanciaHamming++;
                        distanciaManhattan += nuevaManhattan;
                    }
                }
            }
        }
    }

    public String toString() {
        StringBuilder ret = new StringBuilder();
       // ret.append(n); //Dimension del tablero
        ret.append("\n");
        for (int i = 0; i < n; i++) {
            ret.append("\n");
            ret.append("|");
            String sep = " ";
            for (int j = 0; j < n; j++) {
                if(board[i][j] < 10){
                    sep = "  ";
                }
                else{
                    sep = " ";
                }
                if(board[i][j] == 0){
                    ret.append(sep + "X");
                }
                else{
                    ret.append(sep + board[i][j]);
                }
            }
            ret.append("|");
        }
        return ret.toString();
    }

    // numero de casillas fuera de lugar
    public int hamming() {

        return distanciaHamming;
    }

    // suma de las distancias entre su posicion y la meta
    public int manhattan() {

        return distanciaManhattan;
    }

    public boolean isGoal() {

        return distanciaHamming == 0;
    }

    public boolean equals(Board y) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] != y.board[i][j]) {						//*n2
                    return false;
                }
            }
        }
        return true;
    }

    // tableros vecinos
    public Iterable<Board> neighbors() {
        ArrayList<Board> boards = new ArrayList<Board>();
        int[] deltax = {-1, 0, 1,  0};
        int[] deltay = { 0, 1, 0, -1};
        //deltax y deltay representan posiciones de vecinos a la izquierda, arriba, a la derecha y abajo, respectivamente.
        for (int i = 0; i < 4; i++) {
            if ((blankX + deltax[i] >= 0 && blankX + deltax[i] < n && blankY + deltay[i] >= 0 && blankY + deltay[i] < n)) {
            															//*n
                // Si la pieza en blanco esta dentro de los limites del tablero
                int[][] tmp = board;
                Board nuevo = new Board(tmp);
                nuevo.swap(blankX, blankY, blankX + deltax[i], blankY + deltay[i]);
                boards.add(new Board(nuevo.board));						//*n * Board(n)
                nuevo.swap(blankX, blankY, blankX + deltax[i], blankY + deltay[i]);
            }
        }
        return boards;
    }

    public int tileAt(int column, int row){

        return this.board[column][row];
    }

    private void swap(int a, int b, int c, int d) {
            int temp = board[a][b];
            board[a][b] = board[c][d];
            board[c][d] = temp;
        }
}


