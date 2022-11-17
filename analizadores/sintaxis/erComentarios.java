package analizadores.sintaxis;

import errores.nodoError;
import errores.pilaError;
import tblSimbolos.simbolo;
import tblSimbolos.tblSimbolo;

/*Sintaxis
    * // 
    * [CONTENIDO]
*/
public class erComentarios {

    String cadena;
    tblSimbolo TblSimbolo;
    erBooleana ErBooleana;
    pilaError PilaError;
    boolean estatus=false;
    int linea, beginIndex;

    public erComentarios(tblSimbolo TblSimbolo, String cadena, pilaError PilaError, int linea){
        this.TblSimbolo = TblSimbolo;
        this.cadena = cadena;
        this.PilaError = PilaError;
        this.linea = linea;
    }
    void valiER(){
        this.TblSimbolo.m_agreSimbolo(new simbolo(("COMENTARIO 80 // ["+cadena.length()+"]").split(" ")));       
    }
}
