package analizadores.sintaxis;

import errores.nodoError;
import errores.pilaError;
import tblSimbolos.tblSimbolo;
import tblSimbolos.simbolo;

public class erImprimir {
    
    String cadena;
    tblSimbolo TblSimbolo;
    erBooleana ErBooleana;
    pilaError PilaError;
    boolean estatus=false;
    int linea, beginIndex;
    consola consola;

    public erImprimir(tblSimbolo TblSimbolo, String cadena, pilaError PilaError, int linea, consola consola){
        this.TblSimbolo = TblSimbolo;
        this.cadena = cadena;
        this.PilaError = PilaError;
        this.linea = linea;
        this.consola = consola;
    }

    public void valiER(){
        String ultimo = cadena.substring(cadena.length()-1);
        //System.out.println("ERIMPR "+ultimo);
        if(ultimo.equals(";")){
            String[] datos = cadena.split(" ");
            String valor = "";
            try {
                System.out.println("-------- "+datos[1]);
                valor = TblSimbolo.getSimboloToken(datos[1]).getValor();
                if(valor != null){
                    System.out.println("Valor => "+valor);
                    consola.addConsola(valor);
                    //tblSimbolo.getSimbolo(posi);
                }else{
                    System.out.println("ERROR EN LA DECARACION DE VARIABLES");
                    PilaError.push(new nodoError(String.valueOf(linea),"ERROR EN LA DECARACION DE VARIABLES" , "0"));
                }
            } catch (Exception e) {
                PilaError.push(new nodoError(String.valueOf(linea),"ERROR EN LA DECARACION DE VARIABLES" , "0"));
            }
            
        }else{
            System.out.println("ERROR, FALTO AGREGAR EL DELIMITADOR");
            PilaError.push(new nodoError(String.valueOf(linea),"FALTO AGREGAR EL DELIMITADOR" , "0"));
        }
        
    }

}
