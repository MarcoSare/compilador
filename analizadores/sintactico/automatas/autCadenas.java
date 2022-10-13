
import java.util.Scanner;
public class autCadenas {
    String cadena;
    int position=0;
    boolean acept = false;
    Scanner scr = new Scanner(System.in);
    public static void main(String[] args) {
        autCadenas o = new autCadenas();
        o.cadena = o.scr.nextLine();
        o.start();
    }

    void start(){
        if(cadena.length()>0)
        q0(cadena.charAt(0));
        if(acept)
        System.out.println("CADENA");
        else
        System.out.println("ERROR");
    }

    void q0(char c){
        if(c == '"'){
            position++;
                if(cadena.length()>position)
                q1(cadena.charAt(position)); 
        }
        
    }

    void q1(char c){
        if((c >=32 && c<=125) || c ==164 || c==165){
            position++;
                if(cadena.length()>position)
                q1(cadena.charAt(position));
                else
                q3(cadena.charAt(position-1));

        }
    }

    void q3(char c){
        if(c=='"')
        acept = true;
    }
}
