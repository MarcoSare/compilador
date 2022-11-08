package analizadores.sintaxis;
import analizadores.lexico.token;

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
    String[] linea;
    token t;
    public erVariables(String[] datos, token tk){ // 4 o 5
        linea = datos;
        t = tk;
    }
    public void valiER(){
        System.out.println("");
        if(linea.length == 5){
        
            boolean b1 = false, b2 = false, b3 = false, b4 = false, b5 = false;
            // Tipo de dato
            String td = t.getToken(linea[0]);
            String[] desctd =  td.split(",");
            int numt = Integer.parseInt(desctd[1]);
            if((numt > 10) && (numt < 15) ){
                b1 = true;
            }else{
                System.out.println("ERROR, No se renonoce el Tipo de dato");
            }

            if(b1){
                // Nombre de la variable
                String nv = t.getToken(linea[1]);
                String[] descnv = nv.split(",");
                if(descnv[2].equals("Token no especificado")){ // No es un token existente
                    t.variables.add(linea[1]);
                    b2 = true;
                    //System.out.println(t.variables);
                }else{
                    System.out.println("ERROR, LA VARIABLE A DECLARAR YA ES UNA PALABRA RESERVADA");
                }
            }

            if(b1 && b2){
                // Signo de Igualacion
                String si = t.getToken(linea[2]);
                String[] descsi =  si.split(",");
                if(descsi[2].equals("Operador de asignación")){
                    b3 = true;
                }else{
                    System.out.println("ERROR, NO SE AGREGO EL SIMBOLO DE ASIGNACION");
                }
            }

            if(b1 && b2 && b3){
                // Contenido de la variable
                String cv = t.getToken(linea[3]);
                String[] desccv =  cv.split(",");
                // ANALISIS SEMANTICO
                if(desctd[0].equals("ENTERO")){
                    if(desccv[0].equals("NUMERO")){
                        b4 = true;
                    }
                }else if(desctd[0].equals("TEXTO")){
                    if(desccv[0].equals("CADENA")){
                        b4 = true;
                    }
                }
                if(!b4){
                    System.out.println("ERROR, EL TIPO DE DATO NO COINCIDE CON SU CONTENIDO");
                }
            }

            if(b1 && b2 && b3 && b4){
                // Delimitador ;
                String pc = t.getToken(linea[4]);
                String[] descpc =  pc.split(",");
                if(descpc[2].equals("Delimitador")){
                    b5 = true;
                }else{
                    System.out.println("ERROR, FALTO AGREGAR EL DELIMITADOR");
                }
            }

            System.out.println("------ ER Variable ------");
            System.out.println("Tipo de dato: "+linea[0]+" -> "+b1);
            System.out.println("Nombre      : "+linea[1]+" -> "+b2);
            System.out.println("Asignacion  : "+linea[2]+" -> "+b3);
            System.out.println("Contenido   : "+linea[3]+" -> "+b4);
            System.out.println("Delimitador : "+linea[4]+" -> "+b5); 

            if(b1 && b2 && b3 && b4 && b5){
                System.out.println("VARIABLE DECLARADA");
            }else{
                System.out.println("NO FUE POSIBLE DECLARAR LA VARIABLE");
            }
            System.out.println("Variables: "+t.variables);
        
        }/*else if(linea.length == 4){
            // Variables ya declaradas
        }*/else{
            System.out.println("ERROR, LA ER NO CUMPLE CON LOS COMPONENTES NECESARIOS");
        }
    }
}
