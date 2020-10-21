
package Matrices;

/**
 * Test
 * @author ROcelote
 */
public class TestMatriz {
    public static void main(String [] args) throws Exception{
        System.out.println("Nota recordad que empieza la numeracion en 0");
        
        /* Correcto
        Matriz A = new Matriz();
        System.out.println("Matriz identidad 3x3");
        System.out.println(A);
        
        System.out.println("Producto escalar 8*F2");
        A.setMultEskF(1, 8);
        System.out.println(A);
        
        System.out.println("Suma de filas F1 <- F1+F2");
        A.setSumF(0, 1);
        System.out.println(A);
         
        System.out.println("Producto por ella misma");
        Matriz A2 = A.mutliplexM(A);
        System.out.println(A2);
        /*
        
        /* Correcto
        System.out.println("Set Val a (1,1)");
        A.setValCoord(1, 1, 100);
        System.out.println(A);
        */
        
        double m[][] = {{2, 1, -3},
        {1, 1, 2},
        {-1, -2, 0}
        }; //test
        
        Matriz M = new Matriz(m);
        System.out.println("La matriz M");
        System.out.println(M);
        
        System.out.println("La matriz M expandida");
        Matriz JL1 = M.expandMatIden();
        System.out.println(JL1);
        
        System.out.println("Provemos obtener vectores, fila 1");
        double [] fila1 = JL1.getFilaVector(0);
        System.out.println("Fila 0 (empezando en 0)");
        printArr(fila1);
        
        System.out.println("Multiplicando por escalar -1/2");
        Matriz.multiVectK(fila1, (-1.0/2.0));
        printArr(fila1);
        
        System.out.println("Sumando R1 a vect: R1 <- R1 + vect");
        JL1.sumaVectorM(fila1, 1);
        System.out.println(JL1);
        
        System.out.println("Mientras tanto la matriz original");
        System.out.println(M);
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
