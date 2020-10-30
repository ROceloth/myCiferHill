
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
    //Estrategia de comportamiento?
    /*
    public static final String  ALFABETO 
            = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";*/
    
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
        double n1 = Math.sqrt(u_Clave.length());
        int n = (int)Math.round(n1); //solo hacer 1 casteo
        //Respiracion de CC, 8° posutura myEstiloTemplate
        
        Matriz A = toMatrizClave(u_Clave,n);
        
        Matriz [] Pi = toMatricesData(u_Msg,n); //nombre mas descriptivo
        
        Matriz [] Si = encriptaMHill(A,Pi);
        
        String s = recuperaInfMatriz(Si);
        
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
        if (u_Clave.length() == 0 || u_Msg.length() == 0) {
            return false;
        }
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
    
    
    /**
     * Metodo que regresa el indice de donde se
     * encuentra el char en el areglo indexABC
     * @param l un charcter a buscar en indexABC
     * @return indice de la posicion en indexABC, -1 si no se encuentra
     */
    private int findIndexABC(char l){
        //int i = -1;
        for (int i = 0; i < N; i++) {
            Character x = indexABC[i];
            if (x.equals(l)) {
                return i;
            }
        }
        return -1; //no se encuentra tal char en el alfabeto
    }
 
    //Metodos inicializadores de matriz
    /**
     * Inicilizador de matriz de nxn
     * @param u_str Cadena compactada en mayusculas
     * @param n dimension de la matriz, espera ser int
     * @return Matriz representante de nxn de acuerdo con indexABC
     * @throws Simbolo no perteneciente al alfabeto
     * indexABC
     */
    private Matriz toMatrizClave(String u_str, int n) throws Exception{
        Matriz A = new Matriz(n, n);

        int k = 0;

        for (int i = 0; i < n; i++) { //dividido por renglones            
            for (int j = 0; j < n; j++) {
                char letra = u_str.charAt(k++); //incrementos por los 2 ciclos
                int coord = findIndexABC(letra);

                if (coord == -1) {
                    throw new Exception("El simbolo " + letra + "no se encuentra "
                            + "definido en el alfabeto");
                }
                A.setValCoord(i, j, coord);

            }
        }

        return A;
    }
    
    /**
     * Inicializador de Una matriz de nx1
     * @param u_str Cadena compactada en mayusculas
     * @param n dimansion de la matriz, espera ser int
     * @return Matriz representante de nx1 de acuerdo con
     * indexABC
     * @throws Exception Simbolo no perteneciente al alfabeto
     * indexABC
     */
    private Matriz toMatrizData(String u_str, int n) throws Exception{
        Matriz P = new Matriz(n,1,0);
        int k = 0;
        for (int i = 0; i < n; i++) {
            char letra = u_str.charAt(k++); //incrementos por los 2 ciclos
                int coord = findIndexABC(letra);

                if (coord == -1) {
                    throw new Exception("El simbolo " + letra + "no se encuentra "
                            + "definido en el alfabeto");
                }
            P.setValCoord(i, 0, coord);
        }
        return P;
    }
    
    /**
     * Metodo que devuelve un arreglo de matrices
     * que representan los n-gramas en texto plano
     * @param u_msg cadena procesada, sin espacios y en mayusculas
     * @param n intero aceptado multiplo de la dimension de orden de la
     * matriz de encriptado
     * @return Por cada separacion de n en n de u_msg es una matriz 
     * representada deacuerdo a indexABC
     * @throws Exception Simbolo no perteneciente al alfabeto
     * indexABC
     */
    private Matriz [] toMatricesData(String u_msg, int n) throws Exception{
        int k = u_msg.length() / n; //numero de n-gramas
        
        Matriz Pi [] = new Matriz[k];
        
        int x = 0; //estilo explicito
        for (int i = 0; i < u_msg.length(); i += n) {
            String aux = u_msg.substring(i, i+n);
            Matriz p = toMatrizData(aux,n);
            Pi[x++]=p;
        }
        return Pi;
    }
    
    /**
     * Logica del cifrado de Hill para encriptar 
     * vista desde las matrices nuemricas
     * 
     * precondiciones las matrices estan listas para multiplicarse
     * @param A Matriz para incriptar tiene que ser invertible modulo N
     * @param P Matriz para cifrar
     * @return Una matriz encriptada, un n-grama cifrado, i.e S = A x P
     * multiplicacion de matrices
     * @throws Exception la matriz A no tiene inversa modulo N, no se deberia
     * de encriptar con esta matriz
     */
    private Matriz encriptaMHill(Matriz A, Matriz P) throws Exception{
        if (!isHillKriptable(A)) {
            throw new Exception("La clave no es apta para cifrar");
        }
        
        Matriz S = A.productoM(P);//producto
        S.reduccionModulo(N);
        
        return S;
    }
    
    //Overcharzu pawar
    
    /**
     * 
     * @param A La matriz A para encriptar
     * @param Pi arreglo de n-gramas, matrices a encriptar
     * @return Si Arreglo de matrices encriptadas
     * @throws Exception Matriz A producto no permitido bajo la logica del
     * cifrado de Hill
     */
    private Matriz [] encriptaMHill(Matriz A, Matriz [] Pi) throws Exception{
        int k = Pi.length;
        Matriz [] Si = new Matriz [k];
        
        for (int i = 0; i < k; i++) {
            Matriz s = encriptaMHill(A, Pi[i]);
            Si[i] = s;
        }
        return Si;
    }
    
    /**
     * Para poder realizar el decifrado (desEncriptado)
     * y por consiguiente permitir el Encriptado
     * 
     * La matriz A debe de tener inversa y inversa modulo N
     * @param A Matriz a averiguar la inversa
     * @return True si A tiene inversa y 
     * det(A) %% N = a
     * a tenga inverso multiplicativo en Z_N
     */
    private boolean isHillKriptable(Matriz A) throws Exception{
        double det_A = A.determinate(); //Que sea 0 es que no tiene inversa
        //Que el metodo de invesa() de las matrices ya lo detecta
        //Pero hay una propiedad mas fuerte
        int detA = (int)Math.round(det_A);
        int r = ToolsTeoNum.RTNum.modExceso(detA, N);
        
        //El resumen de toda la teoria para las matrices inversas modulares
        return ToolsTeoNum.RTNum.EuclidesAlg(r, N) == 1;
        //Que exista el inverso multiplicativo
    }
    
    
    //tecnica para hacerlo para uno y despues para varios
    
    /**
     * De los valores de la matriz se hace la
     * corespondencia de acuerdo a indexABC
     * para formar una cadena que representaba en la matriz
     * 
     * @param S Matriz que guarda una representacion con indexABC
     * @return NOTA esta cadena es compacta sin espacios y en mayusculas
     */
    private String recuperaInfMatriz(Matriz S){
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < S.getN(); i++) {
            for (int j = 0; j < S.getM(); j++) {
                double val = S.getValCoord(i, j);
                int index = (int)Math.round(val);
                sb.append(indexABC[index]);
            }
        }
        
        return sb.toString();
    }
    
    /**
     * Metodo para recuperar la informacion de un
     * arreglo de matrices segun indexABC
     * @param Si arreglo de matrices
     * @return String cadena en representacion de las matrices
     */
    private String recuperaInfMatriz(Matriz [] Si){
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < Si.length; i++) {
            Matriz tmp = Si[i];
            String mi = recuperaInfMatriz(tmp);
            sb.append(mi);
        }
        return sb.toString();
    }
    
    /**
     * Metodo para desencriptar con la logica de Hill
     * @param clave String que tambien fue la clave que se uso
     * para encriptar generalmente, debe de representar una matriz con 
     * iniversa modular
     * @param criptograma String cadena cifrada, Tanto la clave como el 
     * criptograma se haran operaciones para tratarlas con matrices
     * @return String mensaje recuperado
     * @throws Exception longuitudes de valores que no se pueden operar
     */
    public String desEncriptar(String clave, String criptograma) 
            throws Exception{
        String u_Clave = toUpperCompact(clave);
        String u_crip = toUpperCompact(criptograma);
        
        if (!isLenghtMatch(u_Clave,u_crip)) {
            throw new Exception ("Longuitudes de valores invalidos");
        }//valor de n aceptado
        
        //Respiracion de CC, 8° posutura myEstiloTemplate
        double n1 = Math.sqrt(u_Clave.length());
        int n = (int)Math.round(n1); 
        
        //Valores a matrices
        Matriz A = toMatrizClave(u_Clave,n);
        Matriz [] Ci = toMatricesData(u_crip,n);//Datagramas Matriz en arreglo
        
        //Busqueda de la matriz inversa modulo N
        double detA = A.determinate();
        Matriz A1 = A.inversa();
        
        /* Se cumple que
        A^-1 = 1/detA(adj(A^t))
        con lo que
        detA*r1(A^-1)(mod N) es la matriz inversa modulo N donde
        r1 es el inverso multiplicativo de detA %% N
        (%% Modulo por exceso N, los otros metodos ya se encargan de 
        definir si todo esto es posible)
        */
        int detAaux = (int) Math.round(detA);
        int r = ToolsTeoNum.RTNum.modExceso(detAaux, N);
        int r1 = ToolsTeoNum.RTNum.inversoMultiplicativoMod(r, N);
        
        Matriz A3 = A1.productoEscalarMatriz(detA*r1); //De un solo golpe
        A3.reduccionModulo(N); //Matriz inversa modulo N
        
        //Empieza el decifrado
        
        //Misma secuencia, diferentes paramentros
        Matriz [] Mi = encriptaMHill(A3,Ci);//reutilizacion de codigo pawa
        
        String msg = recuperaInfMatriz(Mi);
        
        return msg;//ALV
    }
    
}
