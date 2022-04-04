/**
 * @author 		Erick
 * @filename	TareaAbizuki.java
 * @date 		Aug 18, 2020
 * {git_config}
 */
package Analysis;
import java.util.*;

public class TareaAbizuki {
    public static int[][] multiplicacionMatrices ( int[][] a, int[][] b) {
      int[][] c = new int[a.length][b[0].length];
      
      for (int i= 0; i<c.length; i++)
          for (int j=0; j<c[0].length; j++)
                  c[i][j] = c[i][j] + a[i][j] * b[i][j];
      return c;
   }

    
    public static void main(String[] args) throws Exception {
        int[][] a = { { 6,5,7,4,3,2}, { 5,7,4,3,2,9}};
        int[][] b = { { 6,5,4,3,6,5}, { 3,2,9,6,7,5}};
        
        long TInicio, TFin, tiempo; //Variables para determinar el tiempo de ejecución
        TInicio = System.currentTimeMillis();
        int[][] c = multiplicacionMatrices(a, b);
        System.out.println(Arrays.deepToString(c));
        TFin = System.currentTimeMillis(); //Tomamos la hora en que finalizó el algoritmo y la almacenamos en la variable T
        tiempo = TFin - TInicio; //Calculamos los milisegundos de diferencia
        System.out.println("Tiempo de ejecución en milisegundos: " + tiempo);
        

    }
}