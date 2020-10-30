package HillsCipher;

/**
 * Testamos mi implementacion del cifrado de HILL
 * @author Zaldivar Rico William Oceloth a.k.a ROcelth
 */
public class TestCifradoHill {
    public static void main(String [] args){
        CifradoHill ch = new CifradoHill(); //ALV
        String clave = "jefh";
        String msg = "con diez canones";
        
        try{
            System.out.println("Considere el alfabeto español con la Ñ "
                    + "es decir encriptacion Z_27");            
            System.out.println("La clave es: " + clave);
            System.out.println("El mensaje a encriptar es: " + msg + "\n");
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
            
        }catch (Exception e){
            System.out.println(e);
        }
        
    }
}
