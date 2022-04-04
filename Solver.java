/**
 * @Author
 * Mathew Gabriel Lopez Garcia A01635001
 */
package Analysis;

import java.util.Comparator;
import java.util.Deque;
import java.util.LinkedList;
public class Solver {
    private Node solutionNode = null;
    public Solver(Board initial) {
        if (initial == null) {											//*1
            System.out.println("El estado inicial no puede ser nulo ");
        }
        //Creo mi propio comparador para la MinPQ, usando los costos de manhattan  y los movimientos acumulados
        Comparator<Node> comp = new Comparator<Node>() {
            @Override
            public int compare(Node a, Node b) {
                int costoA = a.board.manhattan() + a.moves;
                int costoB = b.board.manhattan() + b.moves;
                if(costoA > costoB){
                    return 1;
                }
                else if(costoA == costoB){
                    return 0;
                }
                else{
                    return -1;
                }
            }
        };
        MinPQ<Node> queue = new MinPQ<Node>(comp);
        queue.insert(new Node(initial)); //Paso 1 de A*, agregar el nodo inicial a la queue
        while(!queue.isEmpty()) { //Paso 2 de A*, mientras la queue no es vacia, busco la respuesta
        															//*
            Node res = null; // Aqui voy a guardar la respuesta, si no la encontre se queda en null
            Node current = queue.delMin(); //Tomo el elemento con costo menor en la queue
            if(current.board.isGoal()){								//*
                res = current; //Si el actual es la respuesta, termino
            }
            for(Board neighbor : current.board.neighbors()){		//*n
                //Agrego todos los estados posibles adyacentes al actual (si no estan en la queue)

                //Se que el vecino que estoy revisando no puede estar en la queue simplemente al saber que no es el
                //Estado previo
                if(current.previous == null || !current.previous.board.equals(neighbor)){	//*2n
                    queue.insert(new Node(neighbor,current.moves +1, current));
                }
            }
            if (res != null) {										//*1
                solutionNode = res;
                break;
            }
        }
    }

    public boolean tieneSolucion() {
        return solutionNode != null;
    }

    // min number of moves to solve initial board
    public int moves() {
        if (solutionNode != null) {
            return solutionNode.moves;
        }
        else{
            return -1;
        }
    }

    // sequence of boards in a shortest solution
    public Iterable<Board> solution() {
        if (!tieneSolucion()) {					//1
            return null;
        }
        Deque<Board> solutionPath = new LinkedList<Board>();
        Node node = solutionNode;
        while (node != null) {
            solutionPath.addFirst(node.board);
            node = node.previous;
        }
        return solutionPath;
    }

    private class Node { //Cada tablero es un nodo en el arbol de estados
        public Board board = null;
        public Node previous = null;
        public int moves = 0;
        Node(Board board) {
            this.board = board;
        }
        Node(Board board, int moves, Node previous) {
            this.board = board;
            this.moves = moves;
            this.previous = previous;
        }
    }

    // resolver el puzzle
    public static void main(String[] args) {

        // Crear tablero a partir de un archivo
        In in = new In("puzzle4x4-42.txt");
        int N = in.readInt();
        int[][] tiles = new int[N][N];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                tiles[i][j] = in.readInt();
        Board board = new Board(tiles);

        // solve the puzzle
        

        Stopwatch timer = new Stopwatch();
        Solver solver = new Solver(board);
        System.out.println(timer.elapsedTime()); //
        if (!solver.tieneSolucion()) {
            StdOut.println("No hay solucion posible");
            System.out.println("No sol");
        }
        else {
            StdOut.println("Cantidad minima de movimientos = " + solver.moves());
            for (Board estados : solver.solution()){
                StdOut.print(estados);
                System.out.println();
                System.out.println("------------");
            }
        }
        System.out.println(timer.elapsedTime());
    }
}