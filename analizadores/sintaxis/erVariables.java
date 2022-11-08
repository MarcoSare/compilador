package analizadores.sintaxis;
import analizadores.lexico.*;
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
    boolean b1 = false, b2 = false, b3 = false, b4 = false, b5 = false;
    token t = new token();
    String[] linea;
    public erVariables(String[] datos){ // 4 o 5
        linea = datos;
    }
    public void valiER(){
        if((linea.length == 4) || (linea.length == 5)){
            if(linea.length == 5){
                // Tipo de dato
                String td = t.getToken(linea[0]);
                String[] desctd =  td.split(","); // id, numToken, Token, Descripcion, Valor
                int numt = Integer.parseInt(desctd[1]);
                if((numt > 10) && (numt < 15) ){
                    b1 = true;
                }

                // Nombre de la variable
                String nv = t.getToken(linea[1]);
                String[] descnv =  nv.split(","); // id, numToken, Token, Descripcion, Valor
                b2 = true;
                
                // Signo de Igualacion
                String si = t.getToken(linea[2]);
                String[] descsi =  si.split(","); // id, numToken, Token, Descripcion, Valor
                if(descsi[2].equals("Operador de asignación")){
                    b3 = true;
                }

                // Contenido de la variable
                String cv = t.getToken(linea[3]);
                String[] desccv =  cv.split(","); // id, numToken, Token, Descripcion, Valor
                b4 = true; // Analisis Semantico
                
                // Delimitador ;
                String pc = t.getToken(linea[4]);
                String[] descpc =  pc.split(","); // id, numToken, Token, Descripcion, Valor
                if(descpc[2].equals("Delimitador")){
                    b5 = true;
                }
                System.out.println("------ ER Variable ------");
                System.out.println("Tipo de dato: "+linea[0]+" -> "+b1);
                System.out.println("Nombre      : "+linea[1]+" -> "+b2);
                System.out.println("Asignacion  : "+linea[2]+" -> "+b3);
                System.out.println("Contenido   : "+linea[3]+" -> "+b4);
                System.out.println("Delimitador : "+linea[4]+" -> "+b5);
            }else {
                b1 = true; // ESTATICO [Borrar]
                // Validar que exista la variable
            }
        }else{
            System.out.println("ERROR, La ER no cumple el tamaño adecuado");
        }
    }
}
