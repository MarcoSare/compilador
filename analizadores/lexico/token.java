package analizadores.lexico;
import analizadores.sintaxis.autCadenas;
import analizadores.sintaxis.autNumeros;
public class token {
    
    autCadenas AutCadenas;
    autNumeros AutNumeros;
    lexemas lex;

    String[] vocabulario= {"SI","SINO", "MIENTRAS", "HACER", "ENTERO","FLOTANTE","TEXTO",
                            "OPARITMETICO","OPLOGICO","OPCOMPARACION","OPASIGNACION","DELIMITADOR", "IDENTIFICADOR"};
                            String[] retorno;

    String getToken(String lexema){
        if(lexema.equals("si"))
            return "SI,1,Palabra reservada";
        if(lexema.equals("sino"))
        return "SINO,2,Palabra reservada";    
        if(lexema.equals("mientras"))
        return "MIENTRAS,3,Palabra reservada";    
        if(lexema.equals("hacer"))
        return "HACER,4,Palabra reservada";    
        if(lexema.equals("entero"))
        return "ENTERO,11,Palabra reservada";    
        if(lexema.equals("flotante"))
        return "FLOTANTE,12,Palabra reservada";    
        if(lexema.equals("texto"))
        return "TEXTO,13,Palabra reservada";    
        if(esOpArtimetico(lexema))
        return "OPARITMETICO,21,Operador aritmetico";    
        if(esOpLogico(lexema))
        return "OPLOGICO,31,Operador lógico";       
        if(esOpComparacion(lexema))
        return "OPCOMPARACION,41,Operador de comparación";    
        if(lexema.equals("="))
        return "OPASIGNACION,51,Operador de asignación";    
        if(lexema.equals(";"))
        return "DELIMITADOR,60,delimitador";    
        String buscToken = buscarToken(lexema);
        if(buscToken!=null)
        return buscToken;

    return "TOKEN NO ENCONTRADO,-1,Token no especificado";
    }
    

    boolean esOpArtimetico(String lexema){
        if(lexema.equals("+") || lexema.equals("*") || lexema.equals("/") || lexema.equals("-") || lexema.equals("%"))
        return true;
        return false;
    }

    boolean esOpLogico(String lexema){
        if(lexema.equals("&&") || lexema.equals("||") || lexema.equals("!"))
        return true;
        return false;
    }

    boolean esOpComparacion(String lexema){
        if(lexema.equals("==") || lexema.equals("!=") || lexema.equals("<")|| lexema.equals(">")|| lexema.equals(">=") || lexema.equals("<="))
        return true;
        return false;
    }

    String buscarToken(String lexema){
        AutCadenas = new autCadenas(lexema);
        if(AutCadenas.start())
        return "CADENA,70,Tipo de dato Cadena";

        AutNumeros = new autNumeros(lexema);
        if(AutNumeros.start())
        return "NUMERO,71,Tipo de dato Numero";
        return null;
    }


   public  String[] getListTokens(String linea){
        lex = new lexemas();
        String[] lexemas = lex.getLexemas(linea);
        String[] tokens = new String[lexemas.length];
        for(int i=0;lexemas.length>i;i++){
            tokens[i] = getToken(lexemas[i]);
            //System.out.println(tokens[i]);
        }
           
        return tokens;
    }
    
}
