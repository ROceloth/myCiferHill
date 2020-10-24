
package Matrices;

/**
 * Test
 * @author ROcelote
 */
public class TestMatriz {
    public static void main(String [] args) throws Exception{
        System.out.println("Nota recordad que empieza la numeracion en 0");
         
        double m[][] = {
            {2, 1, -3},
            {1, 1, 2},
            {-1, -2, 0}
        }; 
        
        double m2 [][] = {
            {1,-2,-1,3},
            {-1,3,-2,-2},
            {2,0,1,1},
            {1,-2,2,3}
        };
         
        
        double m3 [][] = {
            {1,-2,-1,3},
            {1,-2,2,3},
            {2,0,1,1},
            {-1,3,-2,-2}
        };
        
         
        double[][] m4 = {
            {1, 0, 1},
            {1, 0, 1},
            {0, 0, 0}
        }; //claramente no invertible
        
        double [][] m5 = {
            {1,2,3,5},
            {4,1,2,3},
            {2,3,1,4},
            {2,2,3,4}                                    
        };
        
        
        testu(m5);
        
    }
    
    /**
     * Cleans
     * Automaticemos los test
     * @param m
     */
    public static void testu(double [][] m) throws Exception{
        Matriz M = new Matriz(m);
        System.out.println("La matriz M");
        System.out.println(M);
        
        System.out.println("Determinante de M:");
        double det = M.determinate();
        System.out.println("es igual a:");
        System.out.println(det); 
        
    }
    
    /*Rapido ver vector*/
    public static void printArr(double [] arr){
        for (int i = 0; i < arr.length; i++) {
            if (i != arr.length -1) {
                System.out.print(arr[i] + " ");
            }else{
                System.out.println(arr[i]);
            }
            
        }
    }
}
