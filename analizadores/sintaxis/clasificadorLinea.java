package analizadores.sintaxis;
import analizadores.lexico.token;

/*
 * Clasifica el codigo por lineas
*/
public class clasificadorLinea {
    String[] codigo;
    token t;
    public clasificadorLinea(String[] lineas, token tk){
        codigo = lineas;
        t = tk;
    }
    public void analisisSintactico(){
        for (String linea : codigo) {
            linea = linea.trim();
            if(!linea.equals("")){
                //System.out.println("Linea: "+linea);
                String[] datos = valiString(linea);
                /*for (String dato : datos) {
                    System.out.println("    -> "+dato);
                }*/
                if(datos[0].equals("principal")){

                }else if(datos[0].equals("si")){

                }else if(datos[0].equals("sino")){

                }else if(datos[0].equals("mientras")){

                }else if(datos[0].equals("hacer")){

                }else if((datos[0].equals("entero") || (datos[0].equals("texto")))){
                    erVariables erv = new erVariables(datos, t);
                    erv.valiER();
                }else if(datos[0].equals("}")){

                }else{
                    // Validar si es una variable declarada
                }
            }
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
