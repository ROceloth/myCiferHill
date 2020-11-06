package HillsCipher;

/**
 * Testamos mi implementacion del cifrado de HILL
 * @author Zaldivar Rico William Oceloth a.k.a ROcelth
 */
public class TestCifradoHill {
    public static void main(String [] args){
        CifradoHill ch = new CifradoHill(); //ALV
        //String clave = "weon"; //Ejemplo 1, XD
        //String msg = "Puto el que lo lea";
        
        //String clave = "jefh"; //Ejemplo 2, ejemplo de la clase pero en Z_27
        //String msg = "con diez canones";
            
        //ejemplo3 lon(c) = 9 lon(msg)=30
        String clave = "Impostore"; //nota no uses acentos
        String msg = "Rojo funa al blanco y yo funo al Negro";
        
        try{
            
            nota();
            System.out.println("La clave es: " + clave);
            System.out.println("El mensaje a encriptar es: " + msg);
            String s = ch.encriptar(clave, msg);
            System.out.println("La encriptacion es: " + s + "\n");
            
            //Desencriptado
            System.out.println("Proceso de desencriptado");
            System.out.println("La clave es: " + clave);
            System.out.println("(El mensaje se recupera todo en MAYUSCULAS "
                    + "y todo junto)");
            System.out.println("El criptograma a desencriptar es: " 
                    + s + "\n");
            String reMsg = ch.desEncriptar(clave, s);
            System.out.println("La desencriptacion es: " + reMsg);
            
            
            /*//Criptoanalisis existoso nota en Z_26
            String m = "cuales se derivan de estructuras algebraicas de codig";
            String m1 = "cu";
            String s = "CM WH MK EW DS FG RQ TJ KU IP DQ AJ SN KC WH YY PB QY OE" +
                            "CT GO AL QI";
            String a = "HCCD";
            
            System.out.println("Encriptado");
            System.out.println(m);
            System.out.println("\n Desencrptado");
            String r = ch.encriptar(a, m);
            System.out.println(r);
            
            System.out.println("Desencripta");
            System.out.println(s);
            String b = ch.desEncriptar(a, s);
            System.out.println(b);
            */
        }catch (Exception e){
            System.out.println(e);
        }
        
    }
    
    private static void nota() {
        System.out.println("Cifrado de Hill en Java");
        System.out.println("Implementacion para el proyecto 1 de criptografia");
        System.out.println("Ni la clave ni el mensaje pueden ser vacios");
        System.out.println("Su longuitud se cuenta por su numero de letras");
        System.out.println("La longuitud de la clave debe de tener una raiz "
                + "cuadrad exacta entera, n");
        System.out.println("La longitud del mensaje tiene que ser "
                + "multiplo de n");
        System.out.println("Asi por ejemplo si la longitud de la clave es 9 "
                + "su raiz cuadrada exacta entera es 3 y los mensajes que\n"
                + "puede encritar y desencriptar tienen que tener "
                + "longuitud algun multiplo de 3");
        System.out.println("Considere el alfabeto español con la Ñ "
                + "es decir encriptacion Z_27\n");
    }
}
