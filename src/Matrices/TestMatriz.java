
package Matrices;

/**
 * Test
 * @author ROcelote
 */
public class TestMatriz {
    public static void main(String [] args) throws Exception{
        System.out.println("Nota recordad que empieza la numeracion en 0");
        
        
        Matriz A = new Matriz();
        System.out.println("Matriz identidad 3x3");
        System.out.println(A);
        
        System.out.println("Producto escalar 8*F2");
        A.setMultEskF(1, 8);
        System.out.println(A);
        
        System.out.println("Suma de filas F1 <- F1+F2");
        A.setSumF(0, 1);
        System.out.println(A);
         
        
        /* Correcto
        System.out.println("Set Val a (1,1)");
        A.setValCoord(1, 1, 100);
        System.out.println(A);
        */
        
        
        System.out.println("Producto por ella misma");
        Matriz A2 = A.mutliplexM(A);
        System.out.println(A2);
        
        
    }
}
