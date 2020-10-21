package Matrices;

/**
 * Clase de matrices para las
 * operaciones de transformaciones lineales
 * 
 * Determinate
 * Escalonada
 * Producto - check
 * Despliegue - check
 * operaciones elementales -check
 * metodo de Guss Jordan para inversa
 * 
 * Intercambio de renglones?
 * 
 * @author ROcelote
 * @version 1.0
 * @since 20/10/2020
 */
public class Matriz {
    private int n; //filas
    private int m; //columnas
    
    //Arreglo de numeros reales
    private double [][] matrix; //arreglo bidimensional
    
    public Matriz(double[][] matrix){
        this.matrix = matrix;
        this.n = matrix.length; //abst filas
        this.m = matrix[0].length; //abst columnas
        
    }
    
    /**
     * Matriz identidad de n x m
     * @param n filas 
     * @param m columans
     */
    public Matriz(int n, int m){
        this.n = n;
        this.m = m;
        matrix = new double [n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (i == j) { //matiz identidad
                    matrix[i][j] = 1; 
                }else{
                    matrix[i][j] = 0; 
                }
                
            }
        }
    }
    
    public Matriz(){
        this(3,3);
    }
    
    
    /**
     * 
     * @param n
     * @param m
     * @param val valor que rellena toda la matriz
     */
    public Matriz(int n, int m, double val){
        this.n = n;
        this.m = m; //Recordad inicializar bien todos sus atributos
        matrix = new double[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                matrix[i][j] = val;
            }
        }
    }

    /* Metodos sabrosongos de acceso*/
    public int getN() {
        return n;
    }

    public int getM() {
        return m;
    }
    
    /**
     * Obtener el valor de la coordenda(i,j)
     * @param i numero de fila
     * @param j numero de columa
     * @return matriz[i][j]
     */
    public double getValCoord(int i, int j){
        return matrix[i][j];
    }

    
    /* Metodos de modificacion por coordenada*/
    
    /**
     * Modificacion a una posicion de la matriz
     * Valor a la cordenada (i,j)
     * @param i numero de fila
     * @param j numero de columna
     * @param val valor a la coordenada (i,j)
     */
    public void setValCoord(int i, int j, double val){
        matrix[i][j] = val;
    }
    
    
    /**
     * Operacion elemental de producto por escalar en fila
     * @param i fila i
     * @param k valor para multiplicar distinto de 0
     * @throws Exception simple de momento pura comprovacion
     * Exception de escalar igual a 0
     */
    public void setMultEskF(int i, double k) throws Exception{
        if (k == 0) {
            throw new Exception("Escalar igual a 0");
        }
        
        for (int l = 0; l < m; l++) {
            matrix[i][l] = matrix[i][l]*k;
        }
    }
    
    /**
     * metodo que suma la fila F2 a la fila F1
     * se modifica solo F1
     * 
     * F1 <- F1 + F2
     * @param F1 Fila primer sumando
     * @param F2 Fila suma y resultado
     * suma de vectores
     */
    public void setSumF(int F1, int F2){
        
        for (int i = 0; i < m; i++) {//sabhe
            matrix[F1][i] += matrix[F2][i];
        }
    }
    
    
    /**
     * Multiplicacion de matrices 
     * @param this Matriz se multiplicara por la matriz A
     * @return this * A = Matriz
     * @throws Exception this.m != A.n matiz no defina para multiplicar
     */
    public Matriz mutliplexM(Matriz A) throws Exception{
        if (this.m != A.getN()) {
            throw new Exception("Dimension de colunmas no igual a filas para "
                    + "multiplicar");
        }
        
        
        int newN = this.m;
        int newM = A.getN();
        Matriz C = new Matriz(newN, newM, 0);
        
        for (int i = 0; i < newN; i++) {
            for (int j = 0; j < newM; j++) {
                int val = 0;
                for (int k = 0; k < newM; k++) {
                    val += this.getValCoord(i, k)
                            * A.getValCoord(k, j);
                }
                //System.out.printf("i: %d, j: %d, val: %d\n",i,j,val);                
                C.setValCoord(i, j, val);
            }
        }
        
        return C;
    }
    
    
    /**
     * Expandamos la matriz a una de su identidad 
     * metodo auxiliar 
     * esperara una matriz cuadrada y creara su expansion
     * para la G-J
     * this matriz se expande
     * @return JL n x 2n
     */
    public Matriz expandMatIden(){
        int n = this.getN();
        int m = 2 * n;
        
        Matriz JL = new Matriz(n,m,0);
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                //verificado indices
                if (j >= n) { //copiar identidad
                    if ((i+n) == j) { //la diagonal de segunda parte
                        JL.setValCoord(i, j, 1);
                    }else{
                        JL.setValCoord(i, j, 0);
                    }
                }else{ //copiar original
                    JL.setValCoord(i, j,
                            this.getValCoord(i, j));//valor j<n ajustados
                }
            }
        }
        
        return JL;
    }
    
    
    /**
     * Manejemos a la fila como un vector
     * @param i la fila a recuperar
     * @return una arr de doubles que representa la fila i
     * no es referencia a la matriz
     */
    public double [] getFilaVector(int i){
        double [] fila = new double[this.m];
        
        for (int j = 0; j < m; j++) {
            fila[j] = this.getValCoord(i, j); //lol calculated
        }
        
        return fila;
    }
          
    /**
     * Metodo aun mas auxiliar para las matrices para multiplicar vectores
     * por un escalar distinto de 0
     * @param fila
     * @param k
     * @return modificara el arreglo de doubles por su producto con k
     * @throws java.lang.Exception No multiplicar por 0
     */
    public static double [] multiVectK(double [] fila, double k) 
            throws Exception{
        
        if (k == 0) {
            throw new Exception("Multiplicar por 0 un vector no lleva "
                    + "a ningun lado");
        }
        
        for (int i = 0; i < fila.length; i++) {
            fila[i] *= k;
        }
        
        return fila;
    }
            
    /**
     * Un metodo tambien axiliar que una fila de this 
     * matriz se sumara con un vector
     * @param vect arr de double a sumar con la fila
     * @param fila la fila que se sumara a vect
     * @throws Exception se espera que la longuitud del
     * vector sea igual que m (osea la longuitud de las filas)
     */
    public void sumaVectorM(double[] vect, int fila) throws Exception{
        if (vect.length != this.m) {
            throw new Exception("Suma invalida de longitud de vectores");
        }
        
        for (int i = 0; i < m; i++) {
            this.setValCoord(fila, i, 
                    this.getValCoord(fila, i) + vect[i]);
        }
    }
    
    /*
    Yeah, no necesitare de momento un clon porque manipulare las GJ1
    */
            
    @Override
    public String toString() {
        
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (j != m-1) {
                    sb.append(String.valueOf(matrix[i][j])).append(" ");
                }else{
                    sb.append(String.valueOf(matrix[i][j]));
                }
                
            }
            if (i != n-1) {
                sb.append("\n");
            }
            
        }
        
        return sb.toString();
    }
    
    
}
