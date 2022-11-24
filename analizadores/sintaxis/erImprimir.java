package analizadores.sintaxis;

import errores.nodoError;
import errores.pilaError;
import tblSimbolos.tblSimbolo;
import tblSimbolos.simbolo;

public class erImprimir {
    
    tblSimbolo TblSimbolo;
    String lineas;
    pilaError PilaError;
    int nlinea;
    consola consola;
    String[] datos, contenido, separado;
    erBooleana ErBooleana;
    boolean estatus=false;
    String cadena = "", token = "";

    public erImprimir(tblSimbolo TblSimbolo, String lineas, pilaError PilaError, int nlinea, consola consola){
        this.TblSimbolo = TblSimbolo;
        this.lineas = lineas;
        this.PilaError = PilaError;
        this.nlinea = nlinea;
        this.consola = consola;
    }

    public void valiER(){
        String ultimo = lineas.trim().substring(lineas.trim().length()-1);
        //System.out.println("ERIMPR "+ultimo);
        if(ultimo.equals(";")){
            try {
                q0();
            } catch (Exception e) {
                System.out.println("ERROR AL IMPRIMIR");
                PilaError.push(new nodoError(String.valueOf(nlinea),"ERROR AL IMPRIMIR" , "0"));
            }
            
        }else{
            System.out.println("ERROR, FALTO AGREGAR EL DELIMITADOR");
            PilaError.push(new nodoError(String.valueOf(nlinea),"FALTO AGREGAR EL DELIMITADOR" , "0"));
        }
    }

    void q0(){
        cadena = "";
        datos = lineas.trim().split("imprimir");
        for (int i = 1; i < datos.length; i++) {
            //System.out.println("Datos ["+i+"]: "+datos[i]);
            contenido = datos[i].trim().split(";");
            q1();
        }
        System.out.println("CADENA -> "+cadena);
        consola.addConsola(cadena);
        /*
        String comillas = "\""; // comillas dobles
        String dolar = "\\Q$\\E"; // Simbolo de dollar a String
        String[] datos = cadena.trim().split(comillas);
        if(datos.length > 1){ // 
            int cont = 0;
            for (String dato : datos) {
                int cont2 = 0;
                cont ++;
                if(!(cont == 1 || cont == datos.length)){
                    System.out.println("COMILLAS ["+cont+"] -> "+dato);
                    String[] variables = dato.trim().split(dolar);
                    if(variables.length > 1){
                        for (String variable : variables) {
                            if(!variable.equals("")){
                                String[] aux = variable.trim().split(" ");
                                System.out.println("    "+Arrays.toString(aux));
                                cont2 ++;
                                System.out.println("    Variable ["+cont2+"] -> "+variable);
                            }
                        }
                    } 
                }
            }try {
                String valor = "";
                System.out.println("-------- "+datos[1]);
                valor = TblSimbolo.getSimboloToken(datos[1]).getValor();
                if(valor != null){
                    System.out.println("Valor => "+valor);
                    consola.addConsola(valor); // Valido
                    //tblSimbolo.getSimbolo(posi);
                }else{
                    System.out.println("ERROR EN LA DECARACION DE VARIABLES");
                    PilaError.push(new nodoError(String.valueOf(nlinea),"ERROR EN LA DECARACION DE VARIABLES" , "0"));
                }
            } catch (Exception e) {
                PilaError.push(new nodoError(String.valueOf(nlinea),"ERROR EN LA DECARACION DE VARIABLES" , "0"));
            }
        }
        */
    }

    void q1(){
        for (int j = 0; j < contenido.length; j++) {
            //System.out.println("    Contenido ["+j+"] -> "+contenido[j]);
            separado = contenido[j].trim().split("\\Q+\\E"); // Simbolo +
            q2();
        }
    }

    void q2(){
        for (int k = 0; k < separado.length; k++) {
            //System.out.println("        Separado ["+k+"] -> "+separado[k]);
            token = separado[k];
            if(token.substring(0, 1).equals("\"")){
                q3();
            }else{
                q4();
            }
        }
    }

    void q3(){
        // Texto directo
        if(token.substring(token.length()-1).equals("\"")){
            cadena += token.substring(1, token.length()-1);
        }
    }

    void q4(){
        // Validar variable
        simbolo sim = TblSimbolo.getSimboloToken(token);
        if(sim != null){
            // Asignacion del valor a la cadena
            if(sim.getToken().equals("VARTEXTO")){
                String aux = sim.getValor().trim().substring(1, sim.getValor().length()-1);
                cadena += aux;
            }else{
                cadena += sim.getValor();
            }
        }
    }

}
