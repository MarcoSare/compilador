

import java.util.Scanner;

public class autNumeros{
    String cadena;
    int position=0;
    boolean acept = false;
    Scanner scr = new Scanner(System.in);
    public static void main(String[] args) {
        autNumeros o = new autNumeros();
        o.cadena = o.scr.nextLine();
        o.start();
    }

    void start(){
        if(cadena.length()>0)
        q0(cadena.charAt(0));
        if(acept)
        System.out.println("ENTERO");
        else
        System.out.println("ERROR");
    }

    void q0(char c){
        if(c >=49 && c<=57){
            position++;
                if(cadena.length()>position)
                q1(cadena.charAt(position));
                else 
                acept = true;
        }
        
    }

    void q1(char c){
        if(c >=48 && c<=57){
            position++;
                if(cadena.length()>position)
                q1(cadena.charAt(position));
                else 
                acept = true;
        }
    
    }
}