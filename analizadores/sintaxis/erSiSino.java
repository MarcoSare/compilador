package analizadores.sintaxis;

import errores.nodoError;
import errores.pilaError;
import tblSimbolos.tblSimbolo;

/* Automata:
    * si
    * (
    *  DECISION (Expresion[es] Booleana[s])
    * )
    * { (Delimitador Apertura)
    *  CONTENIDO 
    * } Delimitador Cierre
        
    * sino
    * { (Delimitador Apertura)
    *  CONTENIDO
    * } (Delimitador Cierre)
*/
public class erSiSino {
    
    String cadena;
    tblSimbolo TblSimbolo;
    erBooleana ErBooleana;
    pilaError PilaError;
    boolean estatus=false;
    int linea, beginIndex;
    
    public erSiSino(tblSimbolo TblSimbolo, String cadena, pilaError PilaError, int linea){
        this.TblSimbolo = TblSimbolo;
        this.cadena = cadena;
        this.PilaError = PilaError;
        this.linea = linea;
    }

    public boolean start(){
        elimEspacios();
        q0();
        return estatus;
    }

    void q0(){
        if(cadena.substring(0, 3).equals("si(")){
            System.out.println("q1");
            q1(getExpBool());
        }else{
            PilaError.push(new nodoError(String.valueOf(linea),"Error se sintaxis" , "0"));
        }
    }
    void q1(String expBool){
        if(expBool== null)
        System.out.println("Error");
        else{
            ErBooleana = new erBooleana(this.TblSimbolo, expBool, this.PilaError, linea);
            if(ErBooleana.start())
                System.out.println("chido");
            else System.out.println("no chido");
        }

    }

    void q2(){
        if(cadena.charAt(beginIndex+=1)==')')
        q3();
        else
        PilaError.push(new nodoError(String.valueOf(linea),"Error se sintaxis falta el parentesis de cierre )" , "0"));
    }

    void q3(){
        estatus = true;
    }


    String getExpBool(){
        for(beginIndex=3;cadena.length()>beginIndex;beginIndex++)
        if(cadena.charAt(beginIndex)==')'){
            System.out.println("c: " +cadena.substring(3, beginIndex));
            return cadena.substring(3, beginIndex);
        }
        return null;        
    }

    void elimEspacios(){
        String[] data = cadena.split(" ");
        cadena="";
        for(String e : data){
            if(!e.equals(""))
            cadena+=e; 
        }
        System.out.println(cadena);
    }
    
}
