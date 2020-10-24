package Matrices;

/**
 * Clase de matrices para las
 * operaciones de transformaciones lineales
 * 
 * Determinate
 * Escalonada - check
 * Producto - check
 * Despliegue - check
 * operaciones elementales -check
 * metodo de Guss Jordan para inversa
 * 
 * Intercambio de renglones? YES
 * -adecuado a det y GJ
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
    
    
    /*
    Operaciones elementales de matrices
    */
    
    
    /**
     * Operacion elemental de producto por escalar en fila
     * Modifica la matriz
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
     * @param A Matriz se multiplicara por la matriz A
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
                System.out.print("val (" + i + "," + j + "): ");
                for (int k = 0; k < newM; k++) {
                    System.out.print(this.getValCoord(i, k) + "x"
                            + A.getValCoord(k, j));
                    System.out.print(" + ");
                    val += this.getValCoord(i, k)
                            * A.getValCoord(k, j);
                    
                }
                System.out.print("=" + val);
                System.out.println();
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
        //Es redundante pero es para ser mas claro
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
    
    
    /**
     * Intercambio de renglones en la matriz
     * Modifica this Matriz Ri <-> Rj, el renglon Ri sera
     * intercambiado por el renglon Rj
     * (Ri, Rj. Se nota el cambio de notacion despues de una buena leida
     * de algebra lineal)
     * @param Ri  Renglo/fila i-esima
     * @param Rj  j-esima fila
     * @throws Exception que se cumpla que 0 < Ri,Rj < m
     */
    public void intercambio(int Ri, int Rj) throws Exception{
        
        if (Ri < 0 || Ri >= m || Rj < 0 || Rj >= m ) {
            throw new Exception("Renglones fuera de rango");
        }
        double [] ri = this.getFilaVector(Ri);
        
        /*
        Ya en otra tarea me dijeron que no enrede tanto los indices
        ves tu que razon mas cuando son indices de matrices en la
        literatura
        */
        for (int l = 0; l < m; l++) {
            //copiar en el renglon i la fila j
            this.setValCoord(Ri, l, 
                    this.getValCoord(Rj, l));
        }
        
        //en el renglo j copiar la ri
        for (int l = 0; l < m; l++) {
            this.setValCoord(Rj, l, 
                    ri[l]);
        }
        
    }//fin del intercambio equivalente
    
    
    /*
    Yeah, no necesitare de momento un clon porque manipulare las GJ1
    */
    
    /**
     * Escalonara una matriz
     * Sera la primera parta del Gauss Jordan, pero aqui
     * aun no se convertiran a 1 la digonal principal, primero
     * se busca el determinante
     * 
     * Cuando se produce un inercambio de renglones
     * el escalonomiento cambia el signo a un renglon
     * 
     * Mas que nada se espera a las matices aumentadas
     * 
     * Escalona la matriz, modifica sus valores
     * @throws Exception la de multiplicar vectores por 0
     */
    public void escalonar() throws Exception{
        //Los debugers sabrosongos
        //System.out.println(this);
        for (int i = 0; i < n - 1; i++) {
            //System.out.println("i: " + i);
            
            for (int j = i; j < n - 1; j++) { //pero barbaro con fullmetal reference
                
                //System.out.println("j: " + j);
                
                //Ri primer valor de la matriz objetivo
                //Rs valor que apunta, donde empieza
                //x = Ri/Rs
                double ri = this.getValCoord((j + 1), i);
                double rs = this.getValCoord(i, i);
                /*rs, no puede continuar con 0, en caso de no encontrar
                intercambio de rs <-> rsn distinto para 0
                esto es dependencias lineales, su det ya es 0
                */
                //rs != 0
                
                //intentara hacer el cambio
                if (rs == 0) { //estamos en la iteracion i
                    //buscar e intercambiar por un nuevo rs
                    //en las filas i+1
                    //nueva rs
                    int rns = this.buscarRenglonMin(i,i);
                    if (rns != 0) {
                        this.intercambio(i, rns);
                        
                        /*
                        Las operaciones elementales no afectaran 
                        al escalonado y busqueda por la matriz
                        pero un intercambio de renglones cambia el signo
                        al determinante
                        */
                        this.setMultEskF(i, (-1));                        
                        rs = this.getValCoord(i, i); //nuevo valor de rs
                    }                    
                } //si ocurrio esto hay que hacer un registro de 
                //cuantas veces lo hizo i.e para adecuarla al 
                //determinante cuantos intercambios hubo
                
                //rapido sabe sabe, si rs se quedo en 0, entonce
                //hubo dependencia lineal
                if (ri != 0 && rs != 0) { //su objetivo no este escalonado

                    double x = ri / rs;
                    
                    /*
                    System.out.println("Ri: " + ri);
                    System.out.println("Rs: " + rs);
                    System.out.println("x:" + x + "\n");
                    */
                    //Ri <- Ri + (-1)Rs
                    double[] Rs = this.getFilaVector(i);

                    /*
                    System.out.println("Rs =:");
                    TestMatriz.printArr(Rs);
                    System.out.println();
                    */
                    
                    Matriz.multiVectK(Rs, (-1) * x);

                    /*
                    System.out.println("Rs*(-1)x");
                    TestMatriz.printArr(Rs);
                    System.out.println();
                    */
                    
                    this.sumaVectorM(Rs, j + 1);
                    
                    /*
                    System.out.println(this);
                    System.out.println();
                    */
                }
            }
        }
    }//end escalonar
    
    
    /**
     * Devuelve el derminate de la matriz
     * @return double que es el determinante
     * @throws Exception del producto por 0
     * se sus otras llamadas
     */
    public double determinate() throws Exception{
        Object [] L = determinateList();
        double det = (Double)L[0]; //desmpaquetado
        return det;
    }
    
    /**
     * Devuelve el derminate de la matriz
     * @return Un arreglo que contendra en la primera
     * posion en Double que es el determinate y en la segunda 
     * la matriz resultado de detGLpt1
     * L[0] -- Determinate
     * L[1] -- Matriz expEscal = detGLpt1();
     * Recuerda castearlo a su valores originales
     * que esto es por polimorfismo devolver dos cosas
     * en un metodo juntandolo en una estructura
     * @throws java.lang.Exception
     */
    private Object[] determinateList() throws Exception{
        //matriz expandida y escalonada
        Matriz expEscal = detGLpt1();
        
        int esN = expEscal.getN();
        int esM = expEscal.getM();
        
        double det = 1;
        for (int i = 0; i < esN; i++) {
            for (int j = 0; j < esM; j++) {
                if (i == j) {
                    det *= expEscal.getValCoord(i, j);
                }
            }
        }
        
        /*
        Aunque son operaciones con Reales, teoricamente
        Aqui se hacen sobre punto flotante
        */
        //Primer redondeo por rint a 4 decimales
        det = Math.rint(det*10000)/10000;
        
        Object [] L = new Object[2];
        L[0] = det;
        L[1] = expEscal;
        
        return L;
    }
            
    /**
     * Expande la matriz con la identidad
     * y la escalona sin reducir su diagonal a 1
     * @return Matriz expandida y escalonada sin su diagonal original
     * principal reducida a 1's
     */
    private Matriz detGLpt1() throws Exception{
        Matriz JL1 = this.expandMatIden();
        JL1.escalonar();
        
        return JL1;
    }
    
    
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

    /**
     * En otras palabras buscara en la columna j,
     * desde (l+1) hasta n su elemento mas pequeño
     * distinto de 0 en caso de no encontrarlo regresara el mismo 
     * indice que lo invoco 
     * 
     * Este metodo solo se llama para hacer un intercambio para la escalonada
     * cuando la coordenada (l,j) = 0 
     * 
     * Intercambio de l <-> (el indice que encuentre) buscando por la columna
     * 
     * @param l  fila de referencia
     * @param j  columna de referencia
     * (l,j) valor que es 0 (otra referencia)
     * @return index k de la fila donde se encontro en la columan el valor mas
     * pequeño distinto de 0
     */
    private int buscarRenglonMin(int l, int j) {
        
        //min == this.getValCoord(l, j) ==  0.0
        double min = Double.MAX_VALUE; //buscamos para bajar
        int index = l+1; //a la fila
        for (int i = l+1; i < n; i++) {
            
            double x = this.getValCoord(i, j);
            if (x < min && x != 0) {
                min = x;
                index = i;
            }
        }
        
        if (min == Double.MAX_VALUE) { //nunca cambio al final
            index = l; //esto hace un intercambio con sigo mismo
            //no se puede escalonar mas en esa columna
        }
        
        return index;
    }

    /**
     * El calculo del determinate se hace impicitamnte aqui
     * tambien, si este es distinto de 0 la matriz tiene inversa
     * @return
     * @throws Exception Matriz singular
     */
    
    public Matriz inversa() throws Exception{
        Object [] L = determinateList();
        double det = (Double)L[0];
        
        if (det == 0) {
            throw new Exception("Esta matriz es singular "
                    + "no tiene inversa");
        }
        
        Matriz pt1 = (Matriz)L[1];
        
        //continuemos con la pt2
        //escalonado y reducimiento hacia arriba
        pt1.escalonarArribaRedux(); //Esta es la parte2
        Matriz invert = recuperarExpnd(pt1);
        
        //invert.cleanM_Rint(); no conviene
        
        return invert;
        
    }
    
    /**
     * Utilizara el redoendeo por
     * rint para "limpiar" la matriz
     * det = Math.rint(det*10000)/10000;
     */
    private void cleanM_Rint(){
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                double val = this.getValCoord(i, j);
                double nuevoVal = Math.rint(val*10000)/10000;
                this.setValCoord(i, j, nuevoVal);
            }
        }
    }
    
    /**
     * desde la matriz que su determinate no es 0 resultado
     * de detGLpt1, es una matriz expandida,
     * desde su coordenaa (n,m/2), osea la (n,n) como referencia 
     * se reducira la diagonal a 1 y se escalonara hacia arriba
     * 
     * Del lado desde (i,j) con i = n+1, n+2, ... , m
     * se encontrara la matriz inversa
     */
    private void escalonarArribaRedux() throws Exception{
        //System.out.println("En el metodo escalonarArribaRedux");
        
        /*
        System.out.println(this);
        System.out.println();
        */
        //Reduce el primer renglon
        double redux1 = 1.0 / this.getValCoord(0, 0);
        this.setMultEskF(0, redux1);
        
        /*
        System.out.println(this);
        System.out.println();
        */
        
        //Los debugers sabrosongos
        for (int i = n-1; i > 0; i--) { //reduce el resto
            System.out.println("i: " + i);
            
            for (int j = i; j > 0; j--) { 
                
                //System.out.println("j: " + j);
                
                //Ri primer valor de la matriz objetivo
                //Rs valor que apunta, donde empieza
                //x = Ri/Rs
                
                double ri = this.getValCoord((j - 1), i);              
                double rsOld = this.getValCoord(i, i);
                
                //primera reduccion hacia arriba
                double redux = 1.0/rsOld;
                this.setMultEskF(i, redux);
                
                double rs = this.getValCoord(i, i); //que sera 1
                
                /*
                System.out.println("Reduccion de renglon");
                System.out.println(this);
                System.out.println();
                */
                
                //no hara intercambios de renglones
                
                if (ri != 0 ) { 

                    double x = ri / rs; //propiedades heredadas
                    
                    /*                    
                    System.out.println("Ri: " + ri);
                    System.out.println("Rs: " + rs);
                    System.out.println("x:" + x + "\n");
                    */
                    
                    //Ri <- Ri + (-1)Rs
                    double[] Rs = this.getFilaVector(i);

                    /*
                    System.out.println("Rs =:");
                    TestMatriz.printArr(Rs);
                    System.out.println();
                    */
                    
                    Matriz.multiVectK(Rs, (-1) * x);

                    /*
                    System.out.println("Rs*(-1)x");
                    TestMatriz.printArr(Rs);
                    System.out.println();
                    */
                    
                    this.sumaVectorM(Rs, j - 1);
                    
                    /*
                    System.out.println(this);
                    System.out.println();
                    */
                }
            }
        }
        
    }
    
    /**
     * Recuperar la matriz que esta en el lado de la expandida
     * @param pt1 Se espera una matriz expandida i.e n x 2n
     * @return Matiz n x n donde de la matriz pt1 sus colunas seran
     * desde n+1,n+2,...,m
     */
    private Matriz recuperarExpnd(Matriz pt1){
        int newN = pt1.getN();
        int newM = pt1.getM();
        
        
        Matriz re = new Matriz(newN,newN,0);
        
        
        for (int i = 0, r = 0;  i < newN; i++, r++) {
            
            for (int j = newN, s = 0; j < newM; j++, s++) {
                
                
                re.setValCoord(r, s,
                        pt1.getValCoord(i, j));
            }
        }
        
        return re;
    }
    
    
}
