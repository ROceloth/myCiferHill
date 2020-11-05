package HillsCipher;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Clase de parseo de archivos preparados para la desenciptacion
 * de textos en archivos
 * 
 * Los archivos estan enciptados con la tecnica de Hill
 * por cada palabra se su traduccion/desifrado en un archivo
 * de salida de acuerdo a la estructura del archivo de entrada.
 * 
 * Clase usada en las pruevas de los modulos y para la tarea
 * @author William Oceloth Zalfivar Rico a.k.a ROceloth el mas perron de los
 * examens chunin
 */
public class ParserFileDesHill {
    private CifradoHill desifrador; //caracteristica

    public ParserFileDesHill(CifradoHill desifrador) {
        this.desifrador = desifrador;
    } //estrategia
    
    
    /**
     * Crea y escribe un FILE con la desenciptaicion de la clave pasada
     * Para la clave es necesario un cripanalisis o tener la clave de
     * En Hill la palabra clave es una llave simetrica, por lo que 
     * el cifrado y desifrado es con la misma clave y se computan
     * las matrices
     * @param clave palabra que cifra y desifra
     * @param f archivo del que leera
     * @throws IOException
     */
    public void makeFileDesHill(File f, String clave) throws IOException {
        final String FILE = "Desifrado.txt";
        Scanner sc = new Scanner(f); //Scanner stream de un FILE
        File salida = new File(FILE);
        if (salida.createNewFile()) {
            System.out.println("Archivo Desifrado creado");
        }
        
        //Write-tele
        FileWriter fw = new FileWriter(FILE);
        
        //Read f and write salida
        while (sc.hasNextLine()) { //tipico iterator
        String data = sc.nextLine();
        //System.out.println(data);
        String palabras [] = data.split(" ");
        StringBuffer sb = new StringBuffer();//linea por linea
            for (int i = 0; i < palabras.length; i++) {
                try{
                String ri = desifrador.desEncriptar(clave, palabras[i]);
                sb.append(ri);
                    if (i != palabras.length -1 ) {//no la ultima
                        sb.append(" ");
                    }else if(sc.hasNextLine()){
                        sb.append("\n");
                    }
                }catch (Exception e){
                    System.out.println(e);
                    System.out.println("Problema con el archivo");
                }
            }// temina de llenar una linea de sb
            
            //usar sb para escribir con fw
            fw.write(sb.toString());
      }
        
        fw.close();
        sc.close();

    }
    
    
}
