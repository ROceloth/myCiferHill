package TestModulos;

import HillsCipher.*;
import java.io.File;
/**
 * Dale
 * @author wozr
 */
public class CriptoAnalisisHillFileTest {
    
    public static void main(String [] arg){
        CifradoHill ch = new CifradoHill();
        ParserFileDesHill pfdh = new ParserFileDesHill(ch);
        
        String clave = "HCCD";
        File f = new File("enigmaHill");
        
        try{
            pfdh.makeFileDesHill(f, clave);
        } catch (Exception e){
            System.out.println(e); //sabhe
        }
        
    }
}
