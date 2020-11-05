package TestModulos;

/**
 * Clase de experimentos
 * @author ROceloth
 */
public class CriptoAnalisisTest {
    public static final String  ALFABETO 
            = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"; //Sin Ñ
    
    public static final int N = ALFABETO.length();
    //Areglo por char -> letras
    public static final char [] indexABC;
            
    static{
        indexABC = new char[N];
        for (int i = 0; i < N; i++) {
            indexABC[i] = ALFABETO.charAt(i);
        }
    }
    
    public static void main(String [] args){
        String m1 = "cu al es se de ri va nd ee st ru ct "
                + "ur as al ge br ai ca sd ec od ig";
        String m2 = "CM WH MK EW DS FG RQ TJ KU IP DQ AJ SN "
                + "KC WH YY PB QY OE CT GO AL QI";
        
        String u_m1 = m1.toUpperCase();
        listaConversion(u_m1);
        System.out.println();
        listaConversion(m2);
        
        
    }
    
    public static void listaConversion(String str){
        //String u_str = toUpperCompact(str);
        String [] lstr = str.split(" ");
        StringBuffer sb = new StringBuffer();
        for (String m: lstr) {
            sb.append(m);
            sb.append(":");
            String aux = toIndex(m);
            sb.append(aux).append(" ");
        }
        System.out.println(sb.toString());
    }
    
    public static String toIndex(String str){
        StringBuffer sb = new StringBuffer();
        sb.append("(");
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            int x = findIndexABC(ch);
            sb.append(x);
            if (i != str.length() - 1) {
                sb.append(",");
            }            
        }
        sb.append(")");
        return sb.toString();
    }
    
    //Prestamen el metodo
    public static String toUpperCompact(String str){
        //Con java 11 esto lo hace trim()
        String str1 = str.replaceAll("\\s+","");
        String str2 = str1.toUpperCase();
        return str2;
    }
    
    public static int findIndexABC(char l){
        //int i = -1;
        for (int i = 0; i < N; i++) {
            Character x = indexABC[i];
            if (x.equals(l)) {
                return i;
            }
        }
        return -1; //no se encuentra tal char en el alfabeto
    }
}
