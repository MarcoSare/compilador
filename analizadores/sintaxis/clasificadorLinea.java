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
    pilaBloques PilaBloques;
    consola consola;

    public clasificadorLinea(String[] lineas, token tk, tblSimbolo TblSimbolo, pilaError PilaError, pilaBloques PilaBloques, consola consola){
        codigo = lineas;
        t = tk;
        this.TblSimbolo = TblSimbolo;
        this.PilaError = PilaError;
        this.PilaBloques = PilaBloques;
        this.consola = consola;
    }
    public void analisisSintactico(){
        boolean bloque = false;
        int i=1;
        for (String linea : codigo) {
            linea = linea.trim();
            if(!linea.equals("")){
                String ultimo = linea.substring(linea.length()-1);
                //System.out.println("Ultimo -> "+ultimo);
                //System.out.println("Linea: "+linea);
                String[] datos = valiString(linea);
                /*for (String dato : datos) {
                    System.out.println("    -> "+dato);
                }*/
                if(datos[0].equals("principal") && ultimo.equals("{")){
                    bloque = true;
                    PilaBloques.push(new nodoBloques("principal"));
                }else if((datos[0].equals("si") || datos[0].equals("sino")) && ultimo.equals("{")){
                    erSiSino ersi = new erSiSino(TblSimbolo, linea, this.PilaError,i);
                    ersi.start();
                    PilaBloques.push(new nodoBloques("si"));
                }else if(datos[0].equals("mientras") && ultimo.equals("{")){
                    erMientras erm = new erMientras();
                    PilaBloques.push(new nodoBloques("mientras"));
                }else if(datos[0].equals("hacer") && ultimo.equals("{")){
                    PilaBloques.push(new nodoBloques("hacer"));
                }else if((datos[0].equals("entero") || (datos[0].equals("texto")) || (datos[0].equals("booleano")) )){
                    erVariables erv = new erVariables(datos, t, this.TblSimbolo, this.PilaError, i);
                    erv.valiER();
                }else if(datos[0].equals("}")){
                    if(PilaBloques.estaVacia()){
                        System.out.println("ERROR en las llaves");
                        PilaError.push(new nodoError(String.valueOf(i),"Error falto cerrar un bloque de codigo" , "20"));
                    }else{
                        PilaBloques.pop();
                        if(bloque){
                            System.out.println("Bloque correcto");
                        }
                    }
                }else if(datos[0].equals("//")){ // Cometarios
                    // *NO HACE NADA* 
                }else if(datos[0].equals("imprimir")){ // Cometarios
                    erImprimir eri = new erImprimir(TblSimbolo, linea, this.PilaError,i,consola);
                    eri.valiER();
                }else{
                    PilaError.push(new nodoError(String.valueOf(i),"Error token no reconocido" , "10"));
                }
            }
            i++;
        }
        System.out.println("Pila Bloques -> "+PilaBloques.getLong());
        if(!bloque){
            PilaError.push(new nodoError(String.valueOf(i),"Error falto el bloque de codigo principal" , "21"));
        }
        if(!PilaBloques.estaVacia()){
            PilaError.push(new nodoError(String.valueOf(i),"Error falto cerrar un bloque de codigo" , "20"));
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
