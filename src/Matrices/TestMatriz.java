
package Matrices;

/**
 * Test
 * @author ROcelote
 */
public class TestMatriz {
    public static void main(String [] args) throws Exception{
        System.out.println("Nota recordad que empieza la numeracion en 0");
           
        /*
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
        */
        double m[][] = {
            {2, 1, -3},
            {1, 1, 2},
            {-1, -2, 0}
        }; 
        
        //testu(m);
        
        /*
        Matriz M = new Matriz(m);
        System.out.println("La matriz M");
        System.out.println(M);
        System.out.println("La matriz M2 = M * 15");
        Matriz M2 = M.productoEscalarMatriz(15);
        System.out.println(M2);
        System.out.println("Mientras tanto M");
        System.out.println(M);
        */
        double a[][] = {
            {161,-92},
            {-115,207}
        };
        
        Matriz A = new Matriz(a);
        System.out.println(A);
        System.out.println("Aplicando modulo 26");
        A.reduccionModulo(26);
        System.out.println(A);
        
        
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
        
        System.out.println("La part2 con el determinate");
        Matriz M1 = M.inversa();
        System.out.println("Su matriz inversa es:");
        System.out.println(M1);
        
        System.out.println("El produto de M por su inversa");
        System.out.println("La matriz M");
        System.out.println(M);
        System.out.println("Su inversa");
        System.out.println(M1);
        System.out.println("Su producto M*M1");
        Matriz M3 = M.productoM(M1,true);
        System.out.println(M3);
        System.out.println("Su producto M1*M");
        System.out.println(M1.productoM(M));
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
    
    /*
    Aritmetica de punto flotante pls
    cleans
    */
    public static void testu2(double a, double b, double c){
        System.out.println("a :" + a);
        System.out.println("b: " + b);
        System.out.println("c: "+ c);
        System.out.println("a+b+c: " + (a+b+c));
    }
}
