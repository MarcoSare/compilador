package analizadores.sintaxis;

import analizadores.lexico.token;
import errores.nodoError;
import errores.pilaError;
import tblSimbolos.simbolo;
import tblSimbolos.tblSimbolo;

/*
     * De 3 o 5 de longitud el arrreglo
     * [TipoDato, NombreVariable, Delimitador]
     * [TipoDato, NombreVariable, SignoIgual, Contenido, Delimitador]
*/
/* Sintaxis
     
     // DECLARANDO INICIALIZADA
     * TIPODEDATO (entero, texto)
     * v_var (nombre de la variable [UNICO])
     * = (Signo de igual [Asignacion])
     *  CONTENIDO
     * ; DELIMITADOR * [Inicializada] *
     
     // ASIGNACION DE CONTENIDO (VARIABLE YA DECLARADA)
     * v_var (nombre de la variable [UNICO])
     * = (Signo de igual [Asignacion])
     *  CONTENIDO
     * ; DELIMITADOR * [Inicializada] *
 */

public class erVariables {

    boolean q0 = false, q1 = false, q2 = false, q3 = false, q4 = false;
    tblSimbolo TblSimbolo;
    pilaError PilaError;
    String[] desctd;
    String[] linea;
    int clinea;
    token t;

    public erVariables(String[] datos, token tk, tblSimbolo TblSimbolo, pilaError PilaError, int clinea){ // 4 o 5
        linea = datos;
        t = tk;
        this.TblSimbolo = TblSimbolo;
        this.PilaError = PilaError;
        this.clinea = clinea;
    }

    public void valiER(){
        System.out.println("");
        if(linea.length == 5){
            // Tipo de dato
            String td = t.getToken(linea[0]);
            desctd =  td.split(",");
            int numt = Integer.parseInt(desctd[1]);
            if((numt > 10) && (numt < 15) ){
                q0 = true;
                q0();
            }else{
                System.out.println("ERROR, NO SE RECONOCE EL TIPO DE DATO");
                PilaError.push(new nodoError(String.valueOf(clinea),"NO SE RECONOCE EL TIPO DE DATO" , "0"));
            }

        }/*else if(linea.length == 4){
            // Variables ya declaradas
        }*/else{
            System.out.println("ERROR, LA ER NO CUMPLE CON LOS COMPONENTES MINIMOS NECESARIOS");
            PilaError.push(new nodoError(String.valueOf(clinea),"LA ER NO CUMPLE CON LOS COMPONENTES MINIMOS NECESARIOS" , "0"));
        }
    }

    void q0(){
        if(q0){
            // Nombre de la variable
            String nv = t.getToken(linea[1]);
            String[] descnv = nv.split(",");
            if(descnv[2].equals("Token no especificado")){ // No es un token existente
                q1 = true;
                q1();
                //System.out.println(t.variables);
            }else{
                System.out.println("ERROR, LA VARIABLE A DECLARAR YA ES UNA PALABRA RESERVADA");
                PilaError.push(new nodoError(String.valueOf(clinea),"LA VARIABLE A DECLARAR YA ES UNA PALABRA RESERVADA" , "0"));
            }
        }
    }

    void q1(){
        if(q0 && q1){
            // Signo de Igualacion
            String si = t.getToken(linea[2]);
            String[] descsi =  si.split(",");
            if(descsi[2].equals("Operador de asignación")){
                q2 = true;
                q2();
            }else{
                System.out.println("ERROR, NO SE AGREGO EL SIMBOLO DE ASIGNACION");
                PilaError.push(new nodoError(String.valueOf(clinea),"NO SE AGREGO EL SIMBOLO DE ASIGNACION" , "0"));
            }
        }
    }

    void q2(){
        if(q0 && q1 && q2){
            // Contenido de la variable
            String cv = t.getToken(linea[3]);
            String[] desccv =  cv.split(",");
            // ANALISIS SEMANTICO
            if(desctd[0].equals("ENTERO")){
                if(desccv[0].equals("NUMERO"))
                    q3 = true;
            }else if(desctd[0].equals("TEXTO")){
                if(desccv[0].equals("CADENA"))
                    q3 = true;
            }else if(desctd[0].equals("BOOLEANO")){
                if(desccv[0].equals("TDBOOLEANO"))
                    q3 = true;
            }
            if(!q3){
                System.out.println("ERROR, EL TIPO DE DATO NO COINCIDE CON SU CONTENIDO");
                PilaError.push(new nodoError(String.valueOf(clinea),"EL TIPO DE DATO NO COINCIDE CON SU CONTENIDO" , "0"));
            }else
                q3();
        } 
    }

    void q3(){
        if(q0 && q1 && q2 && q3){
            // Delimitador ;
            String pc = t.getToken(linea[4]);
            String[] descpc =  pc.split(",");
            if(descpc[2].equals("Delimitador")){
                q4 = true;
                q4();
            }else{
                System.out.println("ERROR, FALTO AGREGAR EL DELIMITADOR");
                PilaError.push(new nodoError(String.valueOf(clinea),"FALTO AGREGAR EL DELIMITADOR" , "0"));
            }
        }
        System.out.println("------ ER Variable ------");
        System.out.println("Tipo de dato : "+linea[0]+" -> "+q0);
        System.out.println("Nombre       : "+linea[1]+" -> "+q1);
        System.out.println("Asignacion   : "+linea[2]+" -> "+q2);
        System.out.println("Contenido    : "+linea[3]+" -> "+q3);
        System.out.println("Delimitador  : "+linea[4]+" -> "+q4); 
    }

    void q4(){
        if(q0 && q1 && q2 && q3 && q4){
            t.variables.add(linea[1]);
            int i = this.TblSimbolo.tamanio();
            System.out.println("hola");
            if(linea[0].equals("entero"))
                this.TblSimbolo.m_agreSimbolo(new simbolo(("VARENTERO 75 "+linea[1]+" "+linea[3]).split(" ")));
            if(linea[0].equals("texto"))
                this.TblSimbolo.m_agreSimbolo(new simbolo(("VARTEXTO 76 "+linea[1]+" "+linea[3]).split(" ")));
            if(linea[0].equals("booleano"))
                this.TblSimbolo.m_agreSimbolo(new simbolo(("VARBOOLEANO 77 "+linea[1]+" "+linea[3]).split(" ")));
            System.out.println("VARIABLE DECLARADA");
        }else{
            System.out.println("NO FUE POSIBLE DECLARAR LA VARIABLE");
            PilaError.push(new nodoError(String.valueOf(clinea),"NO FUE POSIBLE DECLARAR LA VARIABLE" , "0"));
        }
        System.out.println("Variables: "+t.variables);
    }

}
