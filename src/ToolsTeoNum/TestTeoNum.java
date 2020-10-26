package ToolsTeoNum;

/**
 * Testeomos mis resultados de la teoria de num
 * @author ROceloth
 */
public class TestTeoNum {
    
    public static void main(String [] mames) throws Exception{
        int a = 17;
        int b = 23;
        int b2 = 17*2; //no necesariamente simetric
        int m = 26;
        int b3 = m+a;
        
        testuEuclides(-34131,1984);
        
        testuEquiv(1,a*b,m);
        
        testuInvr(4,6);
       
        testModExceso(100,26);
    }
    
    public static void testuEuclides(int a, int b) {
        int mcd = RTNum.EuclidesAlg(a, b);
        System.out.println("m.c.d (" + a + "," + b + "):");
        System.out.println(mcd);
    }
    
    public static void testuEquiv(int a, int b, int m){
        System.out.println("¿ "+a+" ≡ "+b+" (mód "+m+")?");
        boolean isEq = RTNum.isEquivalenteM(a, b, m);
        System.out.println(isEq);
    }
    
    public static void testuInvr(int a, int m){
        System.out.println("El inveso multiplicativo de " +a+" modulo"
                + " " + m+" es:");
        try{
            int x = RTNum.inversoMultiplicativoMod(a,m);
            System.out.println(x);
        }catch(Exception e){
            System.out.println(e);
        }
    }
    
    public static void testModExceso(int a, int m) throws Exception{
        System.out.println("Provando el metodo de remainder por exceso");
        System.out.println("Regla 1");
        System.out.print("El resultado de "+a+" %% " +m+ " = ");
        int r = RTNum.modExceso(a, m);
        System.out.println(r);
        
        System.out.println("Regla 2");
        System.out.print("El resultado de "+((-1)*a)+" %% " +m+ " = ");
        r = RTNum.modExceso(a*(-1), m);
        System.out.println(r);
        
        System.out.println("Regla 3");
        System.out.print("El resultado de "+a+" %% " +(m*(-1))+ " = ");
        r = RTNum.modExceso(a, m*(-1));
        System.out.println(r);
        
        System.out.println("Regla 4");
        System.out.print("El resultado de "+(a*(-1))+" %% " +(m*(-1))+ " = ");
        r = RTNum.modExceso((a*(-1)), m*(-1));
        System.out.println(r);
        
    }
}
