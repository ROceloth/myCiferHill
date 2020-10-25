
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
        
        testu(m);
        
        /* Ejercicio teorico
        double a[][] = {
            {1,2,-1},
            {1,1,1},
            {1,-1,0}
        };
        
        double e[][] ={
            {0,0,1},
            {0,1,0},
            {1,0,0}
        };
        
        Matriz A = new Matriz(a);
        Matriz E = new Matriz(e);
        
        System.out.println(A);
        System.out.println("---------");
        System.out.println(E);
        System.out.println("---------");
        System.out.println(E.productoM(A));
        */ 
        
        //(0,0)
        //2.0x0.44444444444444453 + 1.0x-0.22222222222222232 
        //+ -3.0x-0.1111111111111111  == 0?
        
        /*
        double a = (2.0)*(0.44444444444444453);
        double b = (1.0)*(-0.22222222222222232);
        double c = (-3.0)*(-0.1111111111111111);
        */
        
        //(2,2)
        //-1.0x0.5555555555555555 + -2.0x-0.7777777777777777 
        //+ 0.0x0.1111111111111111
        //double a = (-1.0)*(0.5555555555555555);
        //double b = (-2.0)*(-0.7777777777777777);
        //double c = (0.0)*(0.1111111111111111);        
        //testu2(a,b,c);
        
        //2.0x0.6666666666666665 + 1.0x-0.33333333333333304 
        //+ -3.0x0.3333333333333333
        //double a = (2.0)*(0.6666666666666665);
        //double b = (1.0)*(-0.33333333333333304);
        //double c = (-3.0)*(0.3333333333333333);
        //testu2(a,b,c);
        
        //1.0x0.6666666666666665 + 1.0x-0.33333333333333304 
        //+ 2.0x0.3333333333333333
        //double a = (1.0)*(0.6666666666666665);
        //double b = (1.0)*(-0.33333333333333304);
        //double c = (2.0)*(0.3333333333333333);
        //testu2(a,b,c);
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
    */
    public static void testu2(double a, double b, double c){
        System.out.println("a :" + a);
        System.out.println("b: " + b);
        System.out.println("c: "+ c);
        System.out.println("a+b+c: " + (a+b+c));
    }
}
