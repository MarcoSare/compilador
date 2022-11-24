package analizadores.sintaxis.Evaluaciones;

public class evaluar {
    String expresion;
    public evaluar(String expresion){
        this.expresion = expresion;
    }

    public int getResultado(){
        System.out.println("Expresion -> "+expresion);
        infija inf = new infija(expresion);
        String expPostfija = inf.getPostfija();
        postfija postf = new postfija(expPostfija);
        int resultado = postf.getResultado();
        return resultado;
    }
    
}
