package analizadores.sintaxis;

import java.util.Stack;

public class pilaBloques {

    Stack pila;

    public pilaBloques(){
        pila = new Stack<>();
    }

    public void push(nodoBloques metodo){
        pila.push(metodo);
    }

    public Object pop(){
       return pila.pop();
    }

    public boolean estaVacia(){
        return pila.isEmpty();
    }

    int getLong(){
        return pila.size();
    }
    
}
