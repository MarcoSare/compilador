package analizadores.sintaxis;
import analizadores.lexico.token;
import errores.nodoError;
import errores.pilaError;
import tblSimbolos.tblSimbolo;

/*
 * Clasifica el codigo por lineas
*/

public class clasificadorLinea {

    String[] codigo;
    token t;
    tblSimbolo TblSimbolo; 
    pilaError PilaError;

    public clasificadorLinea(String[] lineas, token tk, tblSimbolo TblSimbolo, pilaError PilaError){
        codigo = lineas;
        t = tk;
        this.TblSimbolo = TblSimbolo;
        this.PilaError = PilaError;
    }
    public void analisisSintactico(){
        boolean bloque = false, otro = false;
        int i=1;
        for (String linea : codigo) {
            linea = linea.trim();
            if(!linea.equals("")){
                //System.out.println("Linea: "+linea);
                String[] datos = valiString(linea);
                /*for (String dato : datos) {
                    System.out.println("    -> "+dato);
                }*/
                if(datos[0].equals("principal{")){
                    bloque = true;
                }else if(datos[0].equals("si") || datos[0].equals("sino")){
                    erSiSino e = new erSiSino(TblSimbolo, linea, this.PilaError,i);
                    e.start();
                }else if(datos[0].equals("mientras")){

                }else if(datos[0].equals("hacer")){

                }else if((datos[0].equals("entero") || (datos[0].equals("texto")) || (datos[0].equals("booleano")) )){
                    System.out.println("xD");
                    erVariables erv = new erVariables(datos, t, this.TblSimbolo, this.PilaError, i);
                    erv.valiER();
                }else if(datos[0].equals("}")){
                    if(bloque){
                        if(!otro){
                            System.out.println("Bloque principal correcto");
                        }
                    }
                }else if(datos[0].equals("//")){ // Cometarios
                    // *NO HACE NADA* 
                }else{
                    PilaError.push(new nodoError(String.valueOf(i),"Error token no reconocido" , "10"));
                }
            }
            i++;
        }
    } 
    // Toma de parametro una cadena y la devuelve como un arrelo String separado por espacios
    String[] valiString(String p_dato) {
        String[] v_datos = null;
        int v_con, v_con2;
        try {
            int v_cont = 0, v_cont2 = 0;
            String[] v_aux = p_dato.split(" ");
            for (v_con = 0; v_con < v_aux.length; v_con++)
                if (!v_aux[v_con].equals(""))
                    v_cont++;
            v_datos = new String[v_cont];
            for (v_con2 = 0; v_con2 < v_aux.length; v_con2++)
                if (!v_aux[v_con2].equals("")) {
                    v_datos[v_cont2] = v_aux[v_con2];
                    v_cont2++;
                }
            return v_datos;
        } catch (Exception e) {
            return v_datos;
        }
    }
}
