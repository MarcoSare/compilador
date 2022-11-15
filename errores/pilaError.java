package errores;

import java.util.Stack;

public class pilaError {

    Stack pila;
    public pilaError(){
        pila = new Stack<>();
    }
   
    public void push(nodoError nodo){
        pila.push(nodo);
    }

    public Object pop(){
       return pila.pop();
    }

    public boolean estaVacia(){
        return pila.isEmpty();
    }

}
