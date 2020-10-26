package ToolsTeoNum;

/**
 * Testeomos mis resultados de la teoria de num
 * @author ROceloth
 */
public class TestTeoNum {
    
    public static void main(String [] mames){
        int a = 17;
        int b = 23;
        int b2 = 17*2; //no necesariamente simetric
        int m = 26;
        int b3 = m+a;
        
        testuEuclides(-34131,1984);
        
        testuEquiv(1,a*b,m);
        
        testuInvr(4,6);
       
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
}
