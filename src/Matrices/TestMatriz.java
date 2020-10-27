
package Matrices;

/**
 * Test
 * @author ROcelote
 */
public class TestMatriz {
    public static void main(String [] args) throws Exception{
        System.out.println("Nota recordad que empieza la numeracion en 0");
          
        System.out.println("Compovacion de ejemplo de la clase");
        //Comprovacion de operaciones
        double a [][] = {
            {9,4},
            {5,7}
        };
        
        double msg1 [][] = {
            {2},
            {14}
        };
        
        double msg2 [][] = {
            {13},
            {3}
        };
        
        double msg3 [][] = {
            {14},
            {13}
        };
        
        double a2 [][] ={
            {6,24,1},
            {13,16,10},
            {20,17,15}
        };
        
        double s2 [][] ={
            {0},
            {2},
            {19}
        };
        
        //finalTestu(a,msg3); //ejemplo profe
        finalTestu(a2,s2); //ejemplo wiki
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
    
    /**
     * Test de la logica del cifrado de hill
     * visto con matrices
     * @param a Matriz clave para encriptar y calculo para desencriptar
     * @param msg Matriz (n-grama)
     */
    public static void finalTestu(double[][] a, double[][] msg) {
        try {
            System.out.println("Compovacion de ejemplo de la clase");
            //Comprovacion de operaciones

            Matriz A = new Matriz(a);
            System.out.println("MAtriz de cifrado");
            System.out.println(A);

            Matriz Mt = new Matriz(msg);
            System.out.println("Matriz (DIagrama) de texto plano CO");
            System.out.println(Mt);

            System.out.println("Cifrado Multiplicacion Matriz por la derecha de A");
            System.out.println("Sin cleans");
            Matriz S = A.productoM(Mt);
            System.out.println(S);
            System.out.println("Reduccion a modulo 26 (Alfabeto sin Ñ)");
            S.reduccionModulo(26);
            System.out.println(S);

            System.out.println("La matriz anterior S es un DIagrama cifrado "
                    + "recuerda este dato");

            System.out.println("Desicrado baby");
            System.out.println("Busqueda de la Matriz inversa modulo 26");

            System.out.println("Determinante de A");
            double detA = A.determinate();
            System.out.println(detA);

            System.out.println("Matiz Inversa A1 de A sin cleans");
            Matriz A1 = A.inversa();
            System.out.println(A1);

            System.out.println("detA*A1 = A2");
            Matriz A2 = A1.productoEscalarMatriz(detA);
            System.out.println(A2);

            int detAaux = (int) Math.round(detA);
            int r = ToolsTeoNum.RTNum.modExceso(detAaux, 26);
            System.out.println("detA modulo por exceso 26: "
                    + detA + " %% " + 26 + " = " + r);

            int r1 = ToolsTeoNum.RTNum.inversoMultiplicativoMod(r, 26);
            System.out.println("Inverso multiplicativo de " + r + " "
                    + "en Z26 es: " + r1);

            System.out.println("A2*" + r1 + " = A3");
            Matriz A3 = A2.productoEscalarMatriz(r1);
            System.out.println(A3);

            System.out.println("A3 %% 26");
            A3.reduccionModulo(26);
            System.out.println(A3);
            System.out.println("Esta es la matriz es para decifrar los "
                    + "DIagramas en texto cifrado");

            System.out.println("Resolucion");
            System.out.println("S matriz con el DIagrama cifrado");
            System.out.println(S);
            System.out.println("La matriz A3 * S = Rmsg");
            Matriz Rmsg = A3.productoM(S);
            System.out.println(Rmsg);
            System.out.println("Reducion modulo por exceso 26");
            Rmsg.reduccionModulo(26);
            System.out.println(Rmsg);
        } catch (Exception e) {
            System.out.println(e);
        }

    }
    
}
