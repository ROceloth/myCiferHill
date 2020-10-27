
package HillsCipher;

import Matrices.Matriz;

/**
 * Clase que maneja el comportamiento del
 * Cifrado de Hill
 * 
 * En resumen: 
 * Conversion de clave y texto (plano y cifrado) a
 * matrices de incriptacion/desencriptacion y n-gramas de texto plano o cifrado
 * respectivamente
 * 
 * @author ROceloth
 * @since 27/10/2020
 * @see Matriz
 * @version 1.0
 */
public class CifradoHill {
    
    //Alfabeto con ñ -> mod 27 pues
    public static final String  ALFABETO 
            = "ABCDEFGHIJKLMNÑOPQRSTUVWXYZ";
    public static final int N = ALFABETO.length();
    //Areglo por char -> letras
    public static final char [] indexABC;
            
    static{
        indexABC = new char[N];
        for (int i = 0; i < N; i++) {
            indexABC[i] = ALFABETO.charAt(i);
        }
    }
    
           
    
    /**
     * Cifrado de Hill para encriptar
     * Metodo interfaz para completar la logica del
     * cifrado
     * 
     * @param clave Palabra que obtendra la matriz de
     * incriptacion y matiz invesa modulo N
     * @param msg Mensaje de texto plano a enciptar
     * @return String texto cifrado
     * @throws Exception las cadenas no pueden formar matrices
     * validas para el cifrado de Hill
     */
    
    public String encriptar(String clave, String msg) throws Exception{
        //wacha que ninguna length sea 0
        String u_Clave = toUpperCompact(clave);
        String u_Msg = toUpperCompact(msg);
        
        if (!isLenghtMatch(u_Clave,u_Msg)) {
            throw new Exception ("Longuitudes de valores invalidos");
        }
        
        //raiz exacta
        double n = Math.sqrt(u_Clave.length());
        //Respiracion de CC, 8° posutura myEstiloTemplate
        
        Matriz A = toMatrizClave(u_Clave,n);
        Matriz P = toMatrizData(u_Msg,n);
        
        Matriz S = encriptaMHill(A,P);
        
        String s = recuperaInfMatriz(S);
        
        return s;
    }
    
    /**
     * Precondicion del proyecto, la clave debe de generar 
     * una matriz cuadrada, por lo que su longuitud tiene que se
     * n² para alguna n
     * 
     * Y la longuitud de msg tiene que ser multiplo de aquella n
     * @param u_Clave String sin espacios y todo en mayusculas
     * @param u_Msg String sin espacios y todo en mayusculas
     * @return true solamente si u_Clave.length() tiene raiz cuadrada
     * exacta n y u_Msg.lenght() es multiplo de n
     */
    public boolean isLenghtMatch(String u_Clave, String u_Msg){
        double n = Math.sqrt( u_Clave.length() );
        int l = u_Msg.length();        
        //la primera busca que n sea raiz exacta
        //la segunda que l sea multiplo de n
        return (n % 1 == 0) && (l % n == 0);            
    }
    
    /**
     * Metodo que devuelve una cadena sin espacios
     * y todas sus letras en MAYUSCULAS
     * @param str String a compactar y volver mayusculas
     * @return 
     */
    public String toUpperCompact(String str){
        //Con java 11 esto lo hace trim()
        String str1 = str.replaceAll("\\s+","");
        String str2 = str1.toUpperCase();
        return str2;
    }
    
 
    //Metodos inicializadores de matriz
    /**
     * Inicilizador de matriz de nxn
     * @param u_str Cadena compactada en mayusculas
     * @param n dimension de la matriz, espera ser int
     * @return Matriz representante de nxn de acuerdo con
     * indexABC
     */
    private Matriz toMatrizClave(String u_str, double n){
        
    }
    
    /**
     * Inicializador de matriz de nx1
     * @param u_str Cadena compactada en mayusculas
     * @param n dimansion de la matriz, espera ser int
     * @return Matriz representante de nx1 de acuerdo con
     * indexABC
     */
    private Matriz toMatrizData(String u_str, double n){
        
    }
    
    /**
     * 
     * @param A
     * @param P
     * @return 
     */
    private Matriz encriptaMHill(Matriz A, Matriz P){
        
    }
    
    /**
     * De los valores de la matriz se hace la
     * corespondencia de acuerdo a indexABC
     * para formar una cadena que representaba en la matriz
     * 
     * @param S Matriz que guarda una representacion con indexABC
     * @return NOTA esta cadena es compacta sin espacios y en mayusculas
     */
    private String recuperaInfMatriz(Matriz S){
        
    }
}
