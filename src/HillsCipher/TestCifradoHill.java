package HillsCipher;

/**
 * Testamos mi implementacion del cifrado de HILL
 * @author Zaldivar Rico William Oceloth a.k.a ROcelth
 */
public class TestCifradoHill {
    public static void main(String [] args){
        CifradoHill ch = new CifradoHill(); //ALV
        String clave = "jefh";
        String msg = "co";
        
        try{
            System.out.println("Considere el alfabeto español con la Ñ "
                    + "es decir encriptacion Z_27");
            String s = ch.encriptar(clave, msg);
            System.out.println("La clave es: " + clave);
            System.out.println("El mensaje a encriptar es: " + msg + "\n");
            System.out.println("La encriptacion es: " + s);
        }catch (Exception e){
            System.out.println(e);
        }
        
    }
}
