package ToolsTeoNum;

/**
 * En esta clase y paquete estaran los metodos
 * para operaciones auxiliares con el algebra modular
 * especificamente clases (mód m) 
 * 
 * RTNum -> Resultados (practicos) de la teoria de los numeros
 * enteros
 * @author ROceloth (osea yo)
 * @since 25/10/2020
 * @version 1.0
 */
public class RTNum {
    
    /**
     * Alguna vez lo hice en pytho y ahora lo
     * traslado aqui
     * 
     * El algoritmo de mi pana Euclides
     * #pura number theory 
     * 
     * @param x primer numero
     * @param y segundo numero a comparar
     * @return (x,y) = m.c.d de x e y
     */
    public static int EuclidesAlg(int x, int y){
        //No importara el signo
        int x1 = Math.abs(x);
        int y1 = Math.abs(y);
        
        if (x1 == 0 || y1 == 0) {
            if (x1 == 0) {
                return y1;
            }else{
                return x1;
            }
        }
        
        int a; //El mayor
        int b; //El menor
        
        if(x1 >= y1){
            a = x1;
            b = y1;
        }else{
            a = y1;
            b = x1;
        }
        
        int r = a % b;
        
        if (r == 0) {
            return b;
        }else{ //dale euclides alg
            while(r != 0){
                //int q = a/b; //operacion entera java donde esta la teoria
                r = a%b;
                a = b;
                b = r;
            }
        }
        /*
        Por el teorema del algoritmo de la division,
        eventualmente 'a' proviene de un residuo
        cuando el residuo es 0 acaba la ejecucion
        el ulimo residuo distinto de 0 es el m.c.d
        */
        return a;
    }
    
    /**
     * Por la definicion de las clases de equivalencia
     * 
     * Sean a,b∈Zm.  a=bsi y solo si  a≡b(mód m)
     * Es decir que, m|(a−b) que para fines practicos (a−b)%m == 0
     * @param a Primer representante de clase de equivalencia
     * @param b Segundo representante de clase de equivalencia
     * @param m Modulo referente a Zm
     * @return true si m%(a−b) == 0, false en otro caso
     */
    public static boolean isEquivalenteM(int a, int b, int m){
        return ((a-b) % m) == 0; //asi de barbaro
    }
    
    /**
     * Para Zm, el numero de sus representantes en su
     * clase de equivalencia va desde 0,1,...,m-1 es decir
     * que cuenta con m representantes, entre los los cuales sea
     * a*b = 1 esta que b es inverso multiplicativo de a y viceversa.
     * 
     * Generalmente para Zm la ecuacion a*x = b, 
     * da a la equivalencia de ax≡b(mód m) donde encontrar a x,
     * es resolver la ecuacion diofantina ax = b + my osea
     * ax - my = b, la cual tiene solucion si y solo si (a,m)|b.
     * 
     * Como 26 y 27 no son numeros primo, Z_26 y Z_27 no son anillos
     * por lo que no todos sus elementos tendran inverso multiplicativo
     * solamente para aquellos elemetos a tal que (a,m)|1, que solo tandran
     * (a,m) inversos, con lo cual, si es que lo hay este sera unico.
     * (De hecho si existe siempre es unico)
     * 
     * Por lo tanto en vez de resolver la ecuacion diofantina general
     * utilizare el poder de computo sobre (m-1) posiblididades (el 0
     * no lleva a ningun lado) que se probaran sobre quien puede ser
     * el inverso multiplicativo
     * 
     * @param a Burcar el inverso multiplicativo de a
     * @param m Modulo m, Zm
     * @return si m.c.d (a,m) = 1, regresa el inverso multiplicativo de a
     * @throws Exception (a,m) != 1, no existe tal inverso
     */
    public static int inversoMultiplicativoMod(int a, int m) throws Exception{
        int mcd = EuclidesAlg(a,m);
        if (mcd != 1) {
            throw new Exception("No existe el inverso multiplicativo de "
                    + a +" en Z"+m);
        }
        
        int x = 0;
        for (int i = 1; i <=m ; i++) {
            if (isEquivalenteM(a*i,1,m)) {
                
                x = i;
                break;
            }
        }
        return x; //nunca deberia ser 0 asi que wacha el pedo
        
    }
    
}
