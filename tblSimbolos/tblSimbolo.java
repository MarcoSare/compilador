package tblSimbolos;

import java.util.ArrayList;

public class tblSimbolo {
    ArrayList <simbolo> simbolos;

    public tblSimbolo(){
        simbolos = new ArrayList<>();
    }

    public void m_agreSimbolo(simbolo p_simbolo){
        simbolos.add(p_simbolo);
    }

    public boolean m_buscSimbolo(String p_nombre){
        int v_cont;
        for(v_cont = 0; v_cont < simbolos.size(); v_cont++){
            if (simbolos.get(v_cont).nombre.equals(p_nombre)){
                return true;
            }
        }
        return false;
    }

    public simbolo getSimbolo(int posicion){
        return simbolos.get(posicion);
    }

    public int tamanio (){
        return simbolos.size();
    }

    public void limpTabla(){
        simbolos = new ArrayList<>();
    }
}
